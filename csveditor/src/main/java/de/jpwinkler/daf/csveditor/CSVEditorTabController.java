package de.jpwinkler.daf.csveditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import de.jpwinkler.daf.csveditor.commands.AbstractCommand;
import de.jpwinkler.daf.csveditor.commands.CompositeCommand;
import de.jpwinkler.daf.csveditor.commands.CopyObjectsAfterCommand;
import de.jpwinkler.daf.csveditor.commands.CopyObjectsBelowCommand;
import de.jpwinkler.daf.csveditor.commands.DeleteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.DemoteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.EditObjectAttributeCommand;
import de.jpwinkler.daf.csveditor.commands.EditObjectHeadingTextCommand;
import de.jpwinkler.daf.csveditor.commands.NewObjectAfterCommand;
import de.jpwinkler.daf.csveditor.commands.NewObjectBelowCommand;
import de.jpwinkler.daf.csveditor.commands.PromoteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.ReduceToSelectionCommand;
import de.jpwinkler.daf.csveditor.commands.RunJavascriptOnModuleCommand;
import de.jpwinkler.daf.csveditor.commands.RunJavascriptOnObjectsCommand;
import de.jpwinkler.daf.csveditor.commands.RunJavascriptOnSelectionCommand;
import de.jpwinkler.daf.csveditor.commands.SwapObjectHeadingAndTextCommand;
import de.jpwinkler.daf.csveditor.commands.UnwrapChildrenCommand;
import de.jpwinkler.daf.csveditor.commands.UpdateAction;
import de.jpwinkler.daf.csveditor.filter.AttributeMissingFilter;
import de.jpwinkler.daf.csveditor.filter.CascadingFilter;
import de.jpwinkler.daf.csveditor.filter.ObjectTextAndHeadingFilter;
import de.jpwinkler.daf.csveditor.filter.PredicateFilter;
import de.jpwinkler.daf.csveditor.filter.ReverseCascadingFilter;
import de.jpwinkler.daf.csveditor.util.ColumnDefinition;
import de.jpwinkler.daf.csveditor.util.ColumnType;
import de.jpwinkler.daf.csveditor.util.CommandStack;
import de.jpwinkler.daf.csveditor.util.ViewModel;
import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVWriter;
import de.jpwinkler.daf.dafcore.model.csv.AttributeDefinition;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CSVEditorTabController {

    private static final Logger LOGGER = Logger.getLogger(CSVEditorTabController.class.getName());

    private static final String MAIN_COLUMN = "Object Heading & Object Text";
    private static final List<String> WANTED_ATTRIBUTES = Arrays.asList("Object Identifier", "SourceID", "FO_Object_Type", MAIN_COLUMN, "Object Type", "pod_tag", "pod_tags", "ASIL", "Maturity", "Edit Type", "Relevance", "Potential Verification Method");

    private CSVEditorApplication csvEditorApplication;
    private Stage primaryStage;

    private Tab tab;
    private File file;
    private DoorsModule module;

    private final CommandStack<AbstractCommand> commandStack = new CommandStack<>();
    private final List<DoorsObject> clipboard = new ArrayList<>();
    private final ViewModel viewModel = new ViewModel();

    @FXML
    private TreeView<OutlineTreeItem> outlineTreeView;

    @FXML
    private TableView<DoorsObject> contentTableView;

    @FXML
    private TextArea javascriptTextArea;

    @FXML
    public void initialize() {
        contentTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void setMainApp(final CSVEditorApplication csvEditorApplication) {
        this.csvEditorApplication = csvEditorApplication;
    }

    public void setStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void populateContentTableView() {
        final TablePosition<?, ?> focusedCell = contentTableView.getFocusModel().getFocusedCell();
        contentTableView.getItems().clear();
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                if (viewModel.getFilter() == null || viewModel.getFilter().checkObject(object)) {
                    contentTableView.getItems().add(object);
                }
                return true;
            }

        });
        if (focusedCell != null) {
            contentTableView.getFocusModel().focus(focusedCell);
        }
    }

    private TreeItem<OutlineTreeItem> wrapModule(final DoorsTreeNode doorsTreeNode) {
        final TreeItem<OutlineTreeItem> treeItem = new TreeItem<OutlineTreeItem>(new OutlineTreeItem(doorsTreeNode));

        for (final DoorsTreeNode childNode : doorsTreeNode.getChildren()) {
            treeItem.getChildren().add(wrapModule(childNode));
        }
        return treeItem;
    }

    public boolean save() {
        try (ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(file))) {
            writer.writeModule(module);
            commandStack.setSavePoint();
            updateTabTitle();
            return true;
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    private void updateTabTitle() {
        tab.setText((file != null ? file.getName() : "New Document") + (commandStack.isDirty() ? " *" : ""));
    }

    public void saveAs() {
        final FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(file.getParentFile());
        chooser.setInitialFileName(file.getName());
        final File newFile = chooser.showSaveDialog(primaryStage);
        if (newFile != null) {
            file = newFile;
            save();
        }
    }

    public void setFile(final File file) throws IOException, CSVParseException {
        if (file != null) {
            module = new ModuleCSVParser().parseCSV(file);
            this.file = file;
        } else {
            module = CSVFactory.eINSTANCE.createDoorsModule();
            this.file = null;
        }

        populateOutlineTreeView();

        outlineTreeView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<TreeItem<OutlineTreeItem>>) (observable, oldValue, newValue) -> {
            final DoorsTreeNode treeNode = newValue.getValue().getTreeNode();
            if (treeNode instanceof DoorsObject) {
                contentTableView.getSelectionModel().select((DoorsObject) treeNode);
                contentTableView.scrollTo((DoorsObject) treeNode);
            }
        });

        contentTableView.getColumns().clear();

        for (final String attribute : WANTED_ATTRIBUTES) {
            if (attribute.equals(MAIN_COLUMN)) {
                final ColumnDefinition columnDefinition = new ColumnDefinition();
                columnDefinition.setColumnType(ColumnType.OBJECT_TEXT_HEADING_COLUMN);
                columnDefinition.setColumnTitle(module.getName());
                columnDefinition.setWidth(700);
                viewModel.getDisplayedColumns().add(columnDefinition);
            } else if (module.findAttributeDefinition(attribute) != null) {
                final ColumnDefinition columnDefinition = new ColumnDefinition();
                columnDefinition.setColumnType(ColumnType.ATTRIBUTE_COLUMN);
                columnDefinition.setAttributeName(attribute);
                columnDefinition.setColumnTitle(attribute);
                columnDefinition.setWidth(100);
                viewModel.getDisplayedColumns().add(columnDefinition);
            }
        }

        updateView();

        populateContentTableView();
    }

    private void updateView() {
        contentTableView.getColumns().clear();
        for (final ColumnDefinition columnDefinition : viewModel.getDisplayedColumns()) {
            final TableColumn<DoorsObject, String> c = new TableColumn<>(columnDefinition.getColumnTitle());
            c.setSortable(false);
            c.setPrefWidth(columnDefinition.getWidth());

            if (columnDefinition.getColumnType() == ColumnType.OBJECT_TEXT_HEADING_COLUMN) {
                c.setCellFactory(param -> new ObjectHeadingAndObjectTextTableCell());
                c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getText()));
                c.setOnEditCommit(event -> {
                    executeCommand(new EditObjectHeadingTextCommand(module, event.getRowValue(), event.getNewValue()));
                    contentTableView.requestFocus();
                    contentTableView.getFocusModel().focusNext();
                    contentTableView.edit(contentTableView.getFocusModel().getFocusedIndex(), contentTableView.getFocusModel().getFocusedCell().getTableColumn());
                });
            } else {
                c.setCellFactory(TextFieldTableCell.forTableColumn());
                c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAttributes().get(columnDefinition.getAttributeName())));
                c.setOnEditCommit(event -> {
                    executeCommand(new EditObjectAttributeCommand(module, event.getRowValue(), columnDefinition.getAttributeName(), event.getNewValue()));
                    contentTableView.requestFocus();
                    contentTableView.getFocusModel().focusNext();
                });

            }

            contentTableView.getColumns().add(c);
        }
    }

    private void populateOutlineTreeView() {
        final Map<DoorsTreeNode, Boolean> expanded = new HashMap<>();
        if (outlineTreeView.getRoot() != null) {
            traverseTreeItem(outlineTreeView.getRoot(), ti -> expanded.put(ti.getValue().getTreeNode(), ti.isExpanded()));
        }

        final TreeItem<OutlineTreeItem> wrappedModule = wrapModule(module);
        traverseTreeItem(wrappedModule, ti -> ti.setExpanded(expanded.containsKey(ti.getValue().getTreeNode()) && expanded.get(ti.getValue().getTreeNode())));
        outlineTreeView.setRoot(wrappedModule);
    }

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }

    public void setTab(final Tab tab) {
        this.tab = tab;
    }

    public void reduceToSelection() {
        executeCommand(new ReduceToSelectionCommand(module, getCurrentObject()));
    }

    private void fixObjectLevel(final DoorsObject object, final int level) {
        object.setObjectLevel(level);
        for (final DoorsTreeNode child : object.getChildren()) {
            if (child instanceof DoorsObject) {
                fixObjectLevel((DoorsObject) child, level + 1);
            }
        }
    }

    public void addColumn() {
        final TextInputDialog textInputDialog = new TextInputDialog();
        final Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            if (module.findAttributeDefinition(result.get()) != null) {
                new Alert(AlertType.ERROR, "Attribute already exists.", ButtonType.OK).show();
            } else {
                final AttributeDefinition ad = CSVFactory.eINSTANCE.createAttributeDefinition();
                ad.setName(result.get());
                module.getAttributeDefinitions().add(ad);
                updateView();
            }
        }
    }

    public void swapObjectHeadingAndText() {
        executeCommand(new SwapObjectHeadingAndTextCommand(module, getCurrentObject()));
    }

    private void executeCommand(final AbstractCommand command) {
        if (command.isApplicable()) {
            command.apply();
            commandStack.addCommand(command);
            updateGui(command.getUpdateActions());
        } else {
            new Alert(AlertType.ERROR, "Command is not applicable for current selection", ButtonType.OK).show();
        }
    }

    private void fixObjectNumbers(final DoorsTreeNode object, final String parentObjectNumber) {
        int headingCount = 0;
        int objectCount = 0;
        for (final DoorsTreeNode child : object.getChildren()) {
            if (child instanceof DoorsObject) {
                final DoorsObject childObject = (DoorsObject) child;
                String objectNumber = "";
                if (childObject.isHeading()) {
                    headingCount++;
                    objectCount = 0;
                    objectNumber = (!"".equals(parentObjectNumber) ? parentObjectNumber + "." : "") + String.valueOf(headingCount);
                    childObject.setObjectNumber(objectNumber);
                } else {
                    objectCount++;
                    objectNumber = (!"".equals(parentObjectNumber) ? (parentObjectNumber + ".") : "") + String.valueOf(headingCount) + "-" + String.valueOf(objectCount);
                    childObject.setObjectNumber("");
                }
                fixObjectNumbers(childObject, objectNumber);
            }

        }
    }

    private DoorsObject getCurrentObject() {
        return contentTableView.getSelectionModel().getSelectedItem();
    }

    public void deleteObject() {
        executeCommand(new DeleteObjectCommand(module, getCurrentObject()));
    }

    public void unwrapChildren() {
        executeCommand(new UnwrapChildrenCommand(module, getCurrentObject()));
    }

    private void fixObjectLevels() {
        for (final DoorsTreeNode tn : module.getChildren()) {
            if (tn instanceof DoorsObject) {
                fixObjectLevel((DoorsObject) tn, 1);
            }
        }
    }

    public void demoteObject() {
        executeCommand(new DemoteObjectCommand(module, getCurrentObject()));
    }

    public void promoteObject() {
        executeCommand(new PromoteObjectCommand(module, getCurrentObject()));
    }

    public void newObjectBelow() {
        executeCommand(new NewObjectBelowCommand(module, getCurrentObject()));
    }

    public void newObjectAfter() {
        executeCommand(new NewObjectAfterCommand(module, getCurrentObject()));
    }

    public void redo() {
        if (commandStack.getRedoCommand() != null) {
            final AbstractCommand commandToRedo = commandStack.redo();
            commandToRedo.redo();
            updateGui(commandToRedo.getUpdateActions());
        }
    }

    private void updateGui(final UpdateAction... updateActions) {
        for (final UpdateAction action : updateActions) {
            switch (action) {
            case FIX_OBJECT_LEVELS:
                fixObjectLevels();
                break;
            case FIX_OBJECT_NUMBERS:
                fixObjectNumbers(module, "");
                break;
            case UPDATE_CONTENT_VIEW:
                populateContentTableView();
                break;
            case UPDATE_OUTLINE_VIEW:
                populateOutlineTreeView();
                break;
            case UPDATE_COLUMNS:
                break;
            default:
                break;
            }
        }
        updateTabTitle();
    }

    public void undo() {
        if (commandStack.getUndoCommand() != null) {
            final AbstractCommand commandToUndo = commandStack.undo();
            if (!commandToUndo.canUndo()) {
                new Alert(AlertType.ERROR, "Last command cannot be undone, sorry :/").show();
            } else {
                commandToUndo.undo();
                updateGui(commandToUndo.getUpdateActions());
            }
        }
    }

    public boolean isDirty() {
        return commandStack.isDirty();
    }

    public void updateFilter(final String text, final boolean includeChildren) {
        if (includeChildren) {
            viewModel.setFilter(new CascadingFilter(new ObjectTextAndHeadingFilter(text, false, true)));
        } else {
            viewModel.setFilter(new ObjectTextAndHeadingFilter(text, false, true));
        }
        populateContentTableView();
    }

    public void setupColumns() {
        // TODO Implement this method.
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void setupFilter() {
        // TODO Implement this method.
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void showUntagged() {
        viewModel.setFilter(new ReverseCascadingFilter(new AttributeMissingFilter("pod_tag")));
        populateContentTableView();
    }

    @FXML
    public void runJavaScriptModuleClicked() {
        executeCommand(new RunJavascriptOnModuleCommand(module, javascriptTextArea.getText()));
    }

    @FXML
    public void runJavaScriptObjectsClicked() {
        executeCommand(new RunJavascriptOnObjectsCommand(module, javascriptTextArea.getText()));
    }

    @FXML
    public void runJavaScriptSelectionClicked() {
        executeCommand(new RunJavascriptOnSelectionCommand(module, javascriptTextArea.getText(), contentTableView.getSelectionModel().getSelectedItems()));
    }

    public void pasteBelow() {
        executeCommand(new CopyObjectsBelowCommand(module, contentTableView.getSelectionModel().getSelectedItem(), clipboard));
    }

    public void pasteAfter() {
        executeCommand(new CopyObjectsAfterCommand(module, contentTableView.getSelectionModel().getSelectedItem(), clipboard));
    }

    public void copy() {
        clipboard.clear();
        clipboard.addAll(contentTableView.getSelectionModel().getSelectedItems());
    }

    public void cut() {
        copy();
        final List<DeleteObjectCommand> commands = contentTableView.getSelectionModel().getSelectedItems().stream().map(o -> new DeleteObjectCommand(module, o)).collect(Collectors.toList());
        executeCommand(new CompositeCommand(module, commands));
    }

    public void showOutline() {
        viewModel.setFilter(new PredicateFilter(p -> p.isHeading()));
        populateContentTableView();
    }

}
