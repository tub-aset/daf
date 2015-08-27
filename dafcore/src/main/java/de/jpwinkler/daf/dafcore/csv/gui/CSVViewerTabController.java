package de.jpwinkler.daf.dafcore.csv.gui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class CSVViewerTabController {

    private static final String MAIN_COLUMN = "Object Heading & Object Text";
    private static final List<String> WANTED_ATTRIBUTES = Arrays.asList("Object Identifier", MAIN_COLUMN, "Object Type", "FO_Object_Type", "pod_tags");
    private CSVViewerApplication csvViewerApplication;
    private Stage primaryStage;

    @FXML
    private TreeView<OutlineTreeItem> outlineTreeView;

    @FXML
    private TableView<DoorsObject> contentTableView;

    private DoorsModule module;

    public void setMainApp(final CSVViewerApplication csvViewerApplication) {
        this.csvViewerApplication = csvViewerApplication;
    }

    public void setStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setModule(final DoorsModule module) {
        this.module = module;

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

        final List<String> attributeNames = module.getAttributeDefinitions().stream().map(ad -> ad.getName()).filter(an -> WANTED_ATTRIBUTES.contains(an)).collect(Collectors.toList());

        attributeNames.add(MAIN_COLUMN);

        attributeNames.sort(new PredefinedOrderComparator<String>(WANTED_ATTRIBUTES));

        for (final String attributeName : attributeNames) {
            final TableColumn<DoorsObject, String> c = new TableColumn<>(attributeName);
            if (attributeName.equals(MAIN_COLUMN)) {
                c.setCellFactory(param -> new ObjectHeadingAndObjectTextTableCell());
                c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getText()));
                c.setEditable(false);
            } else {
                c.setCellFactory(TextFieldTableCell.forTableColumn());
                c.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAttributes().get(attributeName)));
                c.setOnEditCommit(event -> {
                    event.getRowValue().getAttributes().put(attributeName, event.getNewValue());
                });
            }

            c.setSortable(false);
            c.setMaxWidth(700);
            contentTableView.getColumns().add(c);
        }

        populateContentTableView(module);
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

}
