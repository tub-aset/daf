package de.jpwinkler.daf.dafcore.csv.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVWriter;
import de.jpwinkler.daf.dafcore.csv.gui.commands.AbstractCommand;
import de.jpwinkler.daf.dafcore.csv.gui.commands.DeleteObjectCommand;
import de.jpwinkler.daf.dafcore.csv.gui.commands.DemoteObjectCommand;
import de.jpwinkler.daf.dafcore.csv.gui.commands.EditObjectHeadingTextCommand;
import de.jpwinkler.daf.dafcore.csv.gui.commands.NewObjectAfterCommand;
import de.jpwinkler.daf.dafcore.csv.gui.commands.NewObjectBelowCommand;
import de.jpwinkler.daf.dafcore.csv.gui.commands.PromoteObjectCommand;
import de.jpwinkler.daf.dafcore.csv.gui.commands.ReduceToSelectionCommand;
import de.jpwinkler.daf.dafcore.csv.gui.commands.SwapObjectHeadingAndTextCommand;
import de.jpwinkler.daf.dafcore.csv.gui.commands.UnwrapChildrenCommand;
import de.jpwinkler.daf.dafcore.csv.gui.util.CommandStack;
import de.jpwinkler.daf.dafcore.model.csv.AttributeDefinition;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CSVViewerTabController {

    private static final String MAIN_COLUMN = "Object Heading & Object Text";
    private static final List<String> WANTED_ATTRIBUTES = Arrays.asList("Object Identifier", "FO_Object_Type", MAIN_COLUMN, "Object Type", "pod_tag", "pod_tags", "ASIL", "Maturity", "Edit Type", "Relevance");

    private CSVViewerApplication csvViewerApplication;
    private Stage primaryStage;

    private Tab tab;
    private File file;
    private DoorsModule module;

    private final CommandStack<AbstractCommand> commandStack = new CommandStack<>();
    private final int lastExecutedCommandIndex = -1;

    @FXML
    private TreeView<OutlineTreeItem> outlineTreeView;

    @FXML
    private TableView<DoorsObject> contentTableView;

    @FXML
    private TextArea javascriptTextArea;


    public void setMainApp(final CSVViewerApplication csvViewerApplication) {
        this.csvViewerApplication = csvViewerApplication;
    }

    public void setStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void populateContentTableView(final DoorsModule module) {
        contentTableView.getItems().clear();
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                contentTableView.getItems().add(object);
                return true;
            }

        });
    }

    private TreeItem<OutlineTreeItem> wrapModule(final DoorsTreeNode doorsTreeNode) {
        final TreeItem<OutlineTreeItem> treeItem = new TreeItem<OutlineTreeItem>(new OutlineTreeItem(doorsTreeNode));

        for (final DoorsTreeNode childNode : doorsTreeNode.getChildren()) {
            treeItem.getChildren().add(wrapModule(childNode));
        }
        return treeItem;
    }

    public boolean isDirty() {
        return commandStack.isDirty();
    }

    public void save() {
        try (ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(file))) {
            writer.writeModule(module);
            setClean();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAs() {
        final FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(file.getParentFile());
        chooser.setInitialFileName(file.getName());
        final File newFile = chooser.showSaveDialog(primaryStage);
        if (newFile != null) {
            file = newFile;
            save();
            tab.setText(file.getName());
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

        final List<String> attributeNames = module.getAttributeDefinitions().stream().map(ad -> ad.getName()).filter(name -> WANTED_ATTRIBUTES.contains(name)).collect(Collectors.toList());

        attributeNames.add(MAIN_COLUMN);

        attributeNames.sort(new PredefinedOrderComparator<String>(WANTED_ATTRIBUTES));

        for (final String attributeName : attributeNames) {
            if (attributeName.equals(MAIN_COLUMN)) {
                final TableColumn<DoorsObject, String> c = new TableColumn<>(attributeName);
                c.setCellFactory(param -> new ObjectHeadingAndObjectTextTableCell());
                c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getText()));
                c.setPrefWidth(700);
                c.setSortable(false);
                contentTableView.getColumns().add(c);
                c.setOnEditCommit(event -> {
                    executeCommand(new EditObjectHeadingTextCommand(module, this, event.getRowValue(), event.getNewValue()));
                    Platform.runLater(contentTableView::requestFocus);
                    setDirty();
                });
            } else {
                addAttributeColumn(attributeName);
            }

        }

        populateContentTableView(module);
    }

    private void populateOutlineTreeView() {
        final TreeItem<OutlineTreeItem> wrappedModule = wrapModule(module);
        outlineTreeView.setRoot(wrappedModule);
    }

    private void addAttributeColumn(final String attributeName) {
        final TableColumn<DoorsObject, String> c = new TableColumn<>(attributeName);
        c.setCellFactory(TextFieldTableCell.forTableColumn());
        c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAttributes().get(attributeName)));
        c.setOnEditCommit(event -> {
            event.getRowValue().getAttributes().put(attributeName, event.getNewValue());
            contentTableView.requestFocus();
            setDirty();
        });
        c.setSortable(false);
        contentTableView.getColumns().add(c);
    }

    private void setDirty() {
        // dirty = true;
        // tab.setText(file.getName() + " *");
    }

    private void setClean() {
        // dirty = false;
        // tab.setText(file.getName());
    }

    public void setTab(final Tab tab) {
        this.tab = tab;
    }

    public void reduceToSelection() {

        executeCommand(new ReduceToSelectionCommand(module, this, getCurrentObject()));

    }

    private void fixObjectLevel(final DoorsObject object, final int level) {
        object.setObjectLevel(level);
        for (final DoorsTreeNode child : object.getChildren()) {
            if (child instanceof DoorsObject) {
                fixObjectLevel((DoorsObject) child, level + 1);
            }
        }
        setDirty();
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
                addAttributeColumn(ad.getName());
                setDirty();
            }
        }
    }

    @FXML
    public void runJavaScriptClicked() {
        final Context cx = Context.enter();

        final ScriptableObject scope = cx.initStandardObjects();

        ScriptableObject.putProperty(scope, "module", Context.javaToJS(module, scope));

        final String source = javascriptTextArea.getText();
        cx.evaluateString(scope, source, "script", 1, null);

        Context.exit();
        setDirty();
    }

    public void swapObjectHeadingAndText() {
        executeCommand(new SwapObjectHeadingAndTextCommand(module, this, getCurrentObject()));
    }

    private void executeCommand(final AbstractCommand abstractCommand) {
        if (abstractCommand.isApplicable()) {
            abstractCommand.apply();
            commandStack.addCommand(abstractCommand);
            fixObjectLevels();
            fixObjectNumbers(module, "");
            populateContentTableView(module);
            populateOutlineTreeView();
            setDirty();

        } else {
            new Alert(AlertType.ERROR, "Command is not applicable for current selection", ButtonType.OK);
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
        executeCommand(new DeleteObjectCommand(module, this, getCurrentObject()));
    }

    public void unwrapChildren() {
        executeCommand(new UnwrapChildrenCommand(module, this, getCurrentObject()));
    }

    private void fixObjectLevels() {
        for (final DoorsTreeNode tn : module.getChildren()) {
            if (tn instanceof DoorsObject) {
                fixObjectLevel((DoorsObject) tn, 1);
            }
        }
    }

    public void demoteObject() {
        executeCommand(new DemoteObjectCommand(module, this, getCurrentObject()));
    }

    public void promoteObject() {
        executeCommand(new PromoteObjectCommand(module, this, getCurrentObject()));
    }

    public void newObjectBelow() {
        executeCommand(new NewObjectBelowCommand(module, this, getCurrentObject()));
    }

    public void newObjectAfter() {
        executeCommand(new NewObjectAfterCommand(module, this, getCurrentObject()));
    }

    public void redo() {
        if (commandStack.canRedo()) {
            commandStack.redo().apply();
            fixObjectLevels();
            fixObjectNumbers(module, "");
            populateContentTableView(module);
            populateOutlineTreeView();
            setDirty();
        }
    }

    public void undo() {
        if (commandStack.canUndo()) {
            commandStack.undo().undo();
            fixObjectLevels();
            fixObjectNumbers(module, "");
            populateContentTableView(module);
            populateOutlineTreeView();
            setDirty();
        }
    }

}
