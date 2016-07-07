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
import de.jpwinkler.daf.csveditor.commands.AddColumnCommand;
import de.jpwinkler.daf.csveditor.commands.ApplyPreprocessingCommand;
import de.jpwinkler.daf.csveditor.commands.CopyObjectsAfterCommand;
import de.jpwinkler.daf.csveditor.commands.CopyObjectsBelowCommand;
import de.jpwinkler.daf.csveditor.commands.DeleteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.DemoteObjectCommand;
import de.jpwinkler.daf.csveditor.commands.EditObjectAttributeCommand;
import de.jpwinkler.daf.csveditor.commands.EditObjectHeadingTextCommand;
import de.jpwinkler.daf.csveditor.commands.FlattenCommand;
import de.jpwinkler.daf.csveditor.commands.MassEditCommand;
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
import de.jpwinkler.daf.csveditor.massedit.MassEditOperation;
import de.jpwinkler.daf.csveditor.massedit.MassEditTarget;
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
import de.jpwinkler.daf.dafcore.util.CSVParseException;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;
import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CSVEditorTabController {

    private static final Logger LOGGER = Logger.getLogger(CSVEditorTabController.class.getName());

    private static final String MAIN_COLUMN = "Object Heading & Object Text";

    private static final List<String> WANTED_ATTRIBUTES = Arrays.asList("SourceID", MAIN_COLUMN, "Object Type");


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

    private ObjectTextPreprocessor preprocessor;

    @FXML
    public void initialize() {
        contentTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        contentTableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<DoorsObject>) (observable, oldValue, newValue) -> {
            csvEditorController.objectSelected(newValue);
        });
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
                break;
            }
            contentTableView.getColumns().add(c);

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
                executeCommand(new AddColumnCommand(module, viewModel, result.get()));
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
                updateView();
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

    public void updateFilter(final String text, final boolean includeParents, final boolean includeChildren, final boolean isExpression) {

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

        if (visibleObjects < totalObjects) {
            csvEditorController.setStatus(String.format("Showing %d out of %d objects.", visibleObjects, totalObjects));
        } else {
            csvEditorController.setStatus("");
        }
    }

    public void setupColumns() {
        try {
            final Stage dialogStage = new Stage();
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/selectcolumns.fxml"));
            final Parent root = loader.load();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            dialogStage.setScene(new Scene(root));

            final SelectColumnsController selectColumnsController = loader.getController();
            selectColumnsController.setTabController(this);
            selectColumnsController.setDialogStage(dialogStage);

            dialogStage.showAndWait();

        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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

    public void flatten() {
        executeCommand(new FlattenCommand(module));
    }

    public void setPreprocessor(final ObjectTextPreprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public void selectObject(final DoorsObject object) {
        contentTableView.getSelectionModel().select(object);
        contentTableView.scrollTo(object);
    }

    public void applyPreprocessing() {
        executeCommand(new ApplyPreprocessingCommand(module, preprocessor));
    }

    public void massEdit(final MassEditTarget target, final MassEditOperation operation) {
        final List<DoorsObject> objects = new ArrayList<>();
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                if (target == MassEditTarget.ALL_OBJECTS || (target == MassEditTarget.FILTERED_OBJECTS && !viewModel.getFilteredObjects().contains(object)) || (target == MassEditTarget.SELECTED_OBJECTS && contentTableView.getSelectionModel().getSelectedItems().contains(object))) {
                    objects.add(object);
                }
                return true;
            }
        });
        executeCommand(new MassEditCommand(module, objects, operation));
    }

    public void splitLines() {
        executeCommand(new SplitLinesCommand(module));
    }

}
