package de.jpwinkler.daf.csveditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jpwinkler.daf.csveditor.commands.AbstractCommand;
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
import de.jpwinkler.daf.csveditor.commands.SwapObjectHeadingAndTextCommand;
import de.jpwinkler.daf.csveditor.commands.UnwrapChildrenCommand;
import de.jpwinkler.daf.csveditor.commands.UpdateAction;
import de.jpwinkler.daf.csveditor.filter.CascadingFilter;
import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilter;
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
import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;
import de.jpwinkler.daf.documenttagging.TaggedDocument;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

public class CSVEditorTabController {

    private static final Logger LOGGER = Logger.getLogger(CSVEditorTabController.class.getName());

    private static final String MAIN_COLUMN = "Object Heading & Object Text";
    private static final List<String> WANTED_ATTRIBUTES = Arrays.asList("Object Identifier", "SourceID", "FO_Object_Type", MAIN_COLUMN, "Object Type", "pod_tag", "pod_tags", "ASIL", "Maturity", "Edit Type", "Relevance", "Potential Verification Method");

    private Stage primaryStage;
    private CSVEditorController csvEditorController;

    private Tab tab;
    private File file;
    private DoorsModule module;

    private final CommandStack<AbstractCommand> commandStack = new CommandStack<>();
    private final List<DoorsObject> clipboard = new ArrayList<>();
    private final ViewModel viewModel = new ViewModel();

    @FXML
    private TableView<DoorsObject> contentTableView;

    private TaggedDocument<DoorsTreeNode, String> algorithmResult;

    private ObjectTextPreprocessor preprocessor;

    @FXML
    public void initialize() {
        contentTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public DoorsModule getModule() {
        return module;
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

        contentTableView.getColumns().clear();

        final ColumnDefinition tagColumn = new ColumnDefinition();
        tagColumn.setColumnTitle("TAG");
        tagColumn.setColumnType(ColumnType.TAG_COLUMN);
        tagColumn.setWidth(100);
        viewModel.getDisplayedColumns().add(tagColumn);

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnType(ColumnType.OBJECT_TEXT_HEADING_COLUMN);
        columnDefinition.setColumnTitle(MAIN_COLUMN);
        columnDefinition.setWidth(700);
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
                contentTableView.getColumns().add(c);
                break;
            case OBJECT_TEXT_HEADING_COLUMN:
                c.setCellFactory(param -> new ObjectHeadingAndObjectTextTableCell(preprocessor));
                c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getText()));
                c.setOnEditCommit(event -> {
                    executeCommand(new EditObjectHeadingTextCommand(module, event.getRowValue(), event.getNewValue()));
                    contentTableView.requestFocus();
                    contentTableView.getFocusModel().focusNext();
                    // contentTableView.edit(contentTableView.getFocusModel().getFocusedIndex(),
                    // contentTableView.getFocusModel().getFocusedCell().getTableColumn());
                });
                contentTableView.getColumns().add(c);
                break;
            case TAG_COLUMN:
                final TableColumn<DoorsObject, Pair<String, String>> c2 = new TableColumn<>(columnDefinition.getColumnTitle());
                c2.setSortable(false);
                c2.setPrefWidth(columnDefinition.getWidth());
                c2.setCellFactory(param -> new TagTableCell());
                c2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DoorsObject, Pair<String, String>>, ObservableValue<Pair<String, String>>>() {

                    @Override
                    public ObservableValue<Pair<String, String>> call(final CellDataFeatures<DoorsObject, Pair<String, String>> param) {
                        if (algorithmResult != null) {
                            return new ReadOnlyObjectWrapper<Pair<String, String>>(new Pair<>(algorithmResult.getPredictedTag(param.getValue()), algorithmResult.getActualTag(param.getValue())));
                        } else {
                            return new ReadOnlyObjectWrapper<>(new Pair<String, String>("", ""));
                        }
                    }
                });
                contentTableView.getColumns().add(c2);
                break;
            }

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

    private List<DoorsObject> getCurrentObjects() {
        return contentTableView.getSelectionModel().getSelectedItems();
    }

    public void deleteObject() {
        executeCommand(new DeleteObjectCommand(module, getCurrentObjects()));
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
                csvEditorController.populateOutlineTreeView(module);
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
                new Alert(AlertType.ERROR, "Last command cannot be undone, sorry.").show();
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
        final DoorsObjectFilter filter = DoorsObjectFilter.compile(text);
        if (includeChildren) {
            viewModel.setFilter(new CascadingFilter(filter));
        } else {
            viewModel.setFilter(filter);
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
        executeCommand(new DeleteObjectCommand(module, getCurrentObjects()));
    }

    public void setMainController(final CSVEditorController csvEditorController) {
        this.csvEditorController = csvEditorController;
    }

    public void setTaggingResult(final TaggedDocument<DoorsTreeNode, String> result) {
        algorithmResult = result;
        populateContentTableView();
    }

    public void flatten() {
        executeCommand(new FlattenCommand(module));
    }

    public void setPreprocessor(final ObjectTextPreprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

}
