package de.jpwinkler.daf.csveditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import de.jpwinkler.daf.csveditor.commands.AbstractCommand;
import de.jpwinkler.daf.csveditor.commands.AddColumnCommand;
import de.jpwinkler.daf.csveditor.commands.CopyObjectsAfterCommand;
import de.jpwinkler.daf.csveditor.commands.CopyObjectsBelowCommand;
import de.jpwinkler.daf.csveditor.commands.DeleteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.DemoteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.EditObjectAttributeCommand;
import de.jpwinkler.daf.csveditor.commands.EditObjectHeadingTextCommand;
import de.jpwinkler.daf.csveditor.commands.FlattenCommand;
import de.jpwinkler.daf.csveditor.commands.NewObjectAfterCommand;
import de.jpwinkler.daf.csveditor.commands.NewObjectBelowCommand;
import de.jpwinkler.daf.csveditor.commands.PromoteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.ReduceToSelectionCommand;
import de.jpwinkler.daf.csveditor.commands.SplitLinesCommand;
import de.jpwinkler.daf.csveditor.commands.SwapObjectHeadingAndTextCommand;
import de.jpwinkler.daf.csveditor.commands.UnwrapChildrenCommand;
import de.jpwinkler.daf.csveditor.commands.UpdateAction;
import de.jpwinkler.daf.csveditor.filter.CascadingFilter;
import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilter;
import de.jpwinkler.daf.csveditor.filter.ObjectTextAndHeadingFilter;
import de.jpwinkler.daf.csveditor.filter.ReverseCascadingFilter;
import de.jpwinkler.daf.csveditor.util.ColumnDefinition;
import de.jpwinkler.daf.csveditor.util.ColumnType;
import de.jpwinkler.daf.csveditor.util.CommandStack;
import de.jpwinkler.daf.csveditor.util.ViewModel;
import de.jpwinkler.daf.doorscsv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.doorscsv.ModuleCSVParser;
import de.jpwinkler.daf.doorscsv.ModuleCSVWriter;
import de.jpwinkler.daf.doorscsv.model.AttributeDefinition;
import de.jpwinkler.daf.doorscsv.model.DoorsCSVFactory;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;
import de.jpwinkler.daf.doorscsv.model.DoorsTreeNode;
import de.jpwinkler.daf.doorscsv.util.DoorsModuleUtil;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FilePaneController implements FileStateController {

    private static final String MAIN_COLUMN = "Object Heading & Object Text";
    private static final String WARNING_COLUMN = "Warnings";

    private static final List<String> WANTED_ATTRIBUTES = Arrays.asList("SourceID", MAIN_COLUMN, "Object Type", WARNING_COLUMN);

    private ApplicationStateController applicationStateController;

    private File file;
    private DoorsModule module;

    private final CommandStack<AbstractCommand> commandStack = new CommandStack<>();
    private final List<DoorsObject> clipboard = new ArrayList<>();
    private final ViewModel viewModel = new ViewModel();
    private List<Menu> menus;

    @FXML
    private TreeView<OutlineTreeItem> outlineTreeView;

    @FXML
    private TableView<DoorsObject> contentTableView;
    
    @FXML
    private ToggleButton filterExpressionToggleButton;

    @FXML
    private TextField filterTextField;

    @FXML
    private CheckBox includeChildrenCheckbox;

    @FXML
    private CheckBox includeParentsCheckbox;

    @FXML
    public void initialize() {
        contentTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        contentTableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<DoorsObject>) (observable, oldValue, newValue) -> {
            this.objectSelected(newValue);
        });
        
        filterTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            this.updateFilter(newValue, includeParentsCheckbox.isSelected(), includeChildrenCheckbox.isSelected(), filterExpressionToggleButton.isSelected());
        });
        includeChildrenCheckbox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            this.updateFilter(filterTextField.getText(), includeParentsCheckbox.isSelected(), newValue, filterExpressionToggleButton.isSelected());
        });
        includeParentsCheckbox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            this.updateFilter(filterTextField.getText(), newValue, includeChildrenCheckbox.isSelected(), filterExpressionToggleButton.isSelected());
        });

    }

    @Override
    public void initialize(ApplicationStateController applicationStateController, File file) throws IOException {
        this.applicationStateController = applicationStateController;
        if (file != null) {
            module = new ModuleCSVParser().parseCSV(file);
            this.file = file;
        } else {
            module = DoorsCSVFactory.eINSTANCE.createDoorsModule();
            this.file = null;
        }

        contentTableView.getColumns().clear();

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnType(ColumnType.OBJECT_TEXT_HEADING_COLUMN);
        columnDefinition.setColumnTitle(MAIN_COLUMN);
        columnDefinition.setWidth(700);
        columnDefinition.setVisible(true);
        viewModel.getDisplayedColumns().add(columnDefinition);

        columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnType(ColumnType.WARNING_COLUMN);
        columnDefinition.setColumnTitle(WARNING_COLUMN);
        columnDefinition.setWidth(100);
        columnDefinition.setVisible(true);
        viewModel.getDisplayedColumns().add(columnDefinition);

        for (final AttributeDefinition attributeDefinition : module.getAttributeDefinitions()) {
            columnDefinition = new ColumnDefinition();
            columnDefinition.setColumnType(ColumnType.ATTRIBUTE_COLUMN);
            columnDefinition.setAttributeName(attributeDefinition.getName());
            columnDefinition.setColumnTitle(attributeDefinition.getName());
            columnDefinition.setWidth(100);
            columnDefinition.setVisible(WANTED_ATTRIBUTES.contains(attributeDefinition.getName()));
            viewModel.getDisplayedColumns().add(columnDefinition);
        }

        Collections.sort(viewModel.getDisplayedColumns(), new PredefinedOrderComparator(WANTED_ATTRIBUTES));

        updateView();

        populateContentTableView();
        
        try {
            final FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("FilePaneMenu.fxml"));
            loader.setController(this);
            final Menu rootMenu = loader.load();
            this.menus = rootMenu.getItems().stream()
                    .filter(m -> m instanceof Menu)
                    .map(m -> (Menu) m)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private final Map<DoorsTreeNode, Boolean> expanded = new WeakHashMap<>();

    private void populateOutlineTreeView(final DoorsModule module) {
        if (outlineTreeView.getRoot() != null) {
            traverseTreeItem(outlineTreeView.getRoot(), ti -> expanded.put(ti.getValue().getTreeNode(), ti.isExpanded()));
        }

        if (module != null) {

            final TreeItem<OutlineTreeItem> wrappedModule = wrapModule(module);
            traverseTreeItem(wrappedModule, ti -> ti.setExpanded(expanded.containsKey(ti.getValue().getTreeNode()) && expanded.get(ti.getValue().getTreeNode())));
            outlineTreeView.setRoot(wrappedModule);
        } else {
            outlineTreeView.setRoot(null);
        }
    }

    private TreeItem<OutlineTreeItem> wrapModule(final DoorsTreeNode doorsTreeNode) {
        final TreeItem<OutlineTreeItem> treeItem = new TreeItem<>(new OutlineTreeItem(doorsTreeNode));

        for (final DoorsTreeNode childNode : doorsTreeNode.getChildren()) {
            treeItem.getChildren().add(wrapModule(childNode));
        }
        return treeItem;
    }

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }

    private void populateContentTableView() {
        final TablePosition<?, ?> focusedCell = contentTableView.getFocusModel().getFocusedCell();
        contentTableView.getItems().clear();
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                if (!viewModel.getFilteredObjects().contains(object)) {
                    contentTableView.getItems().add(object);
                }
                return true;
            }

        });
        if (focusedCell != null) {
            contentTableView.getFocusModel().focus(focusedCell);
        }
    }

    @Override
    public Collection<Menu> getMenus() {
        return menus;
    }

    @Override
    public boolean save() {
        if (file == null) {
            return saveAs();
        }

        try ( ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(file))) {
            writer.writeModule(module);
            commandStack.setSavePoint();
            return true;
        } catch (final IOException ex) {
            applicationStateController.setStatus("Open: Failed to open file; " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean saveAs() {
        final FileChooser chooser = new FileChooser();
        if (file != null) {
            chooser.setInitialDirectory(file.getParentFile());
            chooser.setInitialFileName(file.getName());
        }

        final File newFile = chooser.showSaveDialog(outlineTreeView.getScene().getWindow());
        if (newFile != null) {
            file = newFile;
            return save();
        } else {
            return false;
        }
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public boolean isDirty() {
        return commandStack.isDirty();
    }

    private void updateView() {
        contentTableView.getColumns().clear();
        for (final ColumnDefinition columnDefinition : viewModel.getDisplayedColumns()) {
            if (!columnDefinition.isVisible()) {
                continue;
            }

            final TableColumn<DoorsObject, String> c = new TableColumn<>(columnDefinition.getColumnTitle());
            c.setSortable(false);
            c.setPrefWidth(columnDefinition.getWidth());

            switch (columnDefinition.getColumnType()) {
                case ATTRIBUTE_COLUMN:
                    c.setCellFactory(TextFieldTableCell.forTableColumn());
                    c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAttributes().get(columnDefinition.getAttributeName())));
                    c.setOnEditCommit(event -> {
                        executeCommand(new EditObjectAttributeCommand(module, event.getRowValue(), columnDefinition.getAttributeName(), event.getNewValue()));
                        contentTableView.requestFocus();
                        contentTableView.getFocusModel().focusNext();
                    });
                    break;
                case OBJECT_TEXT_HEADING_COLUMN:
                    c.setCellFactory(param -> new ObjectHeadingAndObjectTextTableCell());
                    c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getText()));
                    c.setOnEditCommit(event -> {
                        executeCommand(new EditObjectHeadingTextCommand(module, event.getRowValue(), event.getNewValue()));
                        contentTableView.requestFocus();
                        contentTableView.getFocusModel().focusNext();
                    });
                    break;
                case WARNING_COLUMN:
                    c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getText()));
                    break;
            }
            contentTableView.getColumns().add(c);

        }
    }

    @FXML
    public void reduceToSelectionClicked() {
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

    @FXML
    public void addColumnClicked() {
        final TextInputDialog textInputDialog = new TextInputDialog();
        final Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            if (module.findAttributeDefinition(result.get()) != null) {
                new Alert(AlertType.ERROR, "Attribute does already exist.", ButtonType.OK).show();
            } else {
                executeCommand(new AddColumnCommand(module, viewModel, result.get()));
            }
        }
    }

    @FXML
    public void swapObjectHeadingAndTextClicked() {
        executeCommand(new SwapObjectHeadingAndTextCommand(module, getCurrentObject()));
    }

    private void executeCommand(final AbstractCommand command) {
        if (!command.isApplicable()) {
            applicationStateController.setStatus(command.getName() + ": Command is not appicable for this selection.");
        }

        command.apply();
        commandStack.addCommand(command);
        updateGui(command.getUpdateActions());
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

    private List<DoorsObject> getCurrentObjects() {
        return contentTableView.getSelectionModel().getSelectedItems();
    }

    @FXML
    public void deleteObjectClicked() {
        executeCommand(new DeleteObjectCommand(module, getCurrentObjects()));
    }

    @FXML
    public void unwrapChildrenClicked() {
        executeCommand(new UnwrapChildrenCommand(module, getCurrentObject()));
    }

    private void fixObjectLevels() {
        for (final DoorsTreeNode tn : module.getChildren()) {
            if (tn instanceof DoorsObject) {
                fixObjectLevel((DoorsObject) tn, 1);
            }
        }
    }

    @FXML
    public void demoteObjectClicked() {
        executeCommand(new DemoteObjectCommand(module, getCurrentObject()));
    }

    @FXML
    public void promoteObjectClicked() {
        executeCommand(new PromoteObjectCommand(module, getCurrentObject()));
    }

    @FXML
    public void newObjectBelowClicked() {
        executeCommand(new NewObjectBelowCommand(module, getCurrentObject()));
    }

    @FXML
    public void newObjectAfterClicked() {
        executeCommand(new NewObjectAfterCommand(module, getCurrentObject()));
    }

    @FXML
    public void redoClicked() {
        if (commandStack.getRedoCommand() != null) {
            final AbstractCommand commandToRedo = commandStack.redo();
            commandToRedo.redo();
            updateGui(commandToRedo.getUpdateActions());
        }
    }

    public void updateGui(final UpdateAction... updateActions) {
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
                    populateOutlineTreeView(module);
                    break;
                case UPDATE_COLUMNS:
                    updateView();
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    public void undoClicked() {
        if (commandStack.getUndoCommand() != null) {
            final AbstractCommand commandToUndo = commandStack.undo();
            if (!commandToUndo.canUndo()) {
                applicationStateController.setStatus("Undo: Last command cannot be undone, sorry.");
            } else {
                commandToUndo.undo();
                updateGui(commandToUndo.getUpdateActions());
            }
        }
    }

    private void updateFilter(final String text, final boolean includeParents, final boolean includeChildren, final boolean isExpression) {

        DoorsObjectFilter filter = isExpression ? DoorsObjectFilter.compile(text) : new ObjectTextAndHeadingFilter(text, false, false);
        if (includeChildren) {
            filter = new CascadingFilter(filter);
        }

        if (includeParents) {
            filter = new ReverseCascadingFilter(filter);
        }

        final DoorsObjectFilter filterFinal = filter;

        viewModel.getFilteredObjects().clear();
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                if (!filterFinal.checkObject(object)) {
                    viewModel.getFilteredObjects().add(object);
                }
                return true;
            }
        });

        populateContentTableView();

        final int totalObjects = DoorsModuleUtil.countObjects(module);
        final int visibleObjects = totalObjects - viewModel.getFilteredObjects().size();

        applicationStateController.setStatus(visibleObjects < totalObjects ? String.format("Showing %d out of %d objects.", visibleObjects, totalObjects) : "");
    }

    @FXML
    public void columnsClicked() {
        try {
            final Stage dialogStage = new Stage();
            final FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("selectcolumns.fxml"));
            final Parent root = loader.load();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(outlineTreeView.getScene().getWindow());

            dialogStage.setScene(new Scene(root));

            final SelectColumnsController selectColumnsController = loader.getController();
            selectColumnsController.setTabController(this);
            selectColumnsController.setDialogStage(dialogStage);

            dialogStage.showAndWait();
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void pasteBelowClicked() {
        executeCommand(new CopyObjectsBelowCommand(module, contentTableView.getSelectionModel().getSelectedItem(), clipboard));
    }

    @FXML
    public void pasteAfterClicked() {
        executeCommand(new CopyObjectsAfterCommand(module, contentTableView.getSelectionModel().getSelectedItem(), clipboard));
    }

    @FXML
    public void copyClicked() {
        clipboard.clear();
        clipboard.addAll(contentTableView.getSelectionModel().getSelectedItems());
    }

    @FXML
    public void cutClicked() {
        copyClicked();
        executeCommand(new DeleteObjectCommand(module, getCurrentObjects()));
    }

    @FXML
    public void flattenClicked() {
        executeCommand(new FlattenCommand(module));
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    @FXML
    public void splitLinesClicked() {
        executeCommand(new SplitLinesCommand(module));
    }

    @FXML
    public void analyzeObjectTypeClicked() {
        contentTableView.refresh();
    }

    private void objectSelected(final DoorsObject newValue) {
        traverseTreeItem(outlineTreeView.getRoot(), item -> {
            if (item.getValue().getTreeNode().equals(newValue)) {
                outlineTreeView.getSelectionModel().select(item);
            }
        });
    }
    
    @FXML
    public void filterExpressionToggleButtonClicked() {
        this.updateFilter(filterTextField.getText(), includeParentsCheckbox.isSelected(), includeChildrenCheckbox.isSelected(), filterExpressionToggleButton.isSelected());
    }
}
