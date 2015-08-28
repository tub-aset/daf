package de.jpwinkler.daf.dafcore.csv.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;

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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class CSVViewerTabController {

    private static final String MAIN_COLUMN = "Object Heading & Object Text";
    private static final List<String> WANTED_ATTRIBUTES = Arrays.asList("Object Identifier", "FO_Object_Type", MAIN_COLUMN, "Object Type", "pod_tag", "ASIL", "Maturity", "Edit Type", "Relevance");
    private CSVViewerApplication csvViewerApplication;
    private Stage primaryStage;

    private boolean dirty = false;

    @FXML
    private TreeView<OutlineTreeItem> outlineTreeView;

    @FXML
    private TableView<DoorsObject> contentTableView;

    private Tab tab;
    private File file;
    private DoorsModule module;

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
        return dirty;
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
        // setClean();
    }

    public void setFile(final File file) throws IOException, CSVParseException {
        module = new ModuleCSVParser().parseCSV(file);
        this.file = file;

        final TreeItem<OutlineTreeItem> wrappedModule = wrapModule(module);

        outlineTreeView.setRoot(wrappedModule);
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
                c.setEditable(false);
                c.setPrefWidth(700);
                c.setSortable(false);
                contentTableView.getColumns().add(c);
            } else {
                addAttributeColumn(attributeName);
            }

        }

        populateContentTableView(module);
    }

    private void addAttributeColumn(final String attributeName) {
        final TableColumn<DoorsObject, String> c = new TableColumn<>(attributeName);
        c.setCellFactory(TextFieldTableCell.forTableColumn());
        c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAttributes().get(attributeName)));
        c.setOnEditCommit(event -> {
            event.getRowValue().getAttributes().put(attributeName, event.getNewValue());
            setDirty();
        });
        c.setSortable(false);
        contentTableView.getColumns().add(c);
    }

    private void setDirty() {
        dirty = true;
        tab.setText(file.getName() + " *");
    }

    private void setClean() {
        dirty = false;
        tab.setText(file.getName());
    }

    public void setTab(final Tab tab) {
        this.tab = tab;
    }

    public void saveSubmoduleAs() {
        final FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
        chooser.setTitle("Save Submodule as...");
        chooser.setInitialDirectory(file.getParentFile());
        chooser.setInitialFileName(file.getName());
        final File saveAsFile = chooser.showSaveDialog(primaryStage);
        if (saveAsFile != null) {

            final DoorsModule moduleCopy = EcoreUtil.copy(module);

            final DoorsObject subTree = EcoreUtil.copy(contentTableView.getSelectionModel().getSelectedItem());


            moduleCopy.getChildren().clear();
            moduleCopy.getChildren().add(subTree);

            fixObjectLevel(subTree, 1);

            try (ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(saveAsFile))) {
                writer.writeModule(moduleCopy);
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
                addAttributeColumn(ad.getName());
            }
        }
    }

}
