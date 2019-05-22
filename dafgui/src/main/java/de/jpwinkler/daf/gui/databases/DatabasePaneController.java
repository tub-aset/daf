package de.jpwinkler.daf.gui.databases;

import de.jpwinkler.daf.gui.databases.commands.PasteCommand;
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.gui.ApplicationPaneController;
import de.jpwinkler.daf.gui.ApplicationPartController;
import de.jpwinkler.daf.gui.ApplicationURI;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.databases.commands.DeleteCommand;
import de.jpwinkler.daf.gui.databases.commands.NewFolderCommand;
import de.jpwinkler.daf.gui.databases.commands.NewModuleCommand;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public final class DatabasePaneController extends ApplicationPartController<DatabasePaneController> {

    public static final ApplicationPartController openLocal(ApplicationPaneController applicationController, ApplicationURI uri) {
        try {
            DatabaseInterface dbInterface = DatabaseInterface.openFileDatabase(uri.isValid() ? new File(uri.getPath()).toPath() : null);
            if (dbInterface.getPath() == null) {
                dbInterface.getDatabaseObject().getRoot().setName(uri.getApplicationPart().getUnnamedName());
            }

            return new DatabasePaneController(applicationController, dbInterface);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static final ApplicationPartController openDoors(ApplicationPaneController applicationController, ApplicationURI uri) {
        try {
            return new DatabasePaneController(applicationController, DatabaseInterface.openDoorsApplicationDatabase());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public DatabasePaneController(ApplicationPaneController applicationController, DatabaseInterface database) {
        super(applicationController);
        this.database = database;

        databaseTreeView.setRoot(new DoorsTreeItem(database.getDatabaseObject().getRoot()));
        updateNewTagComboBox();

        databaseTreeView.setOnMouseClicked(event -> {
            // TODO: open preview
        });

        attributeNameColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getKey()));
        attributeValueColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue()));

        databaseTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateTagsListView();
            updateAttributesView();
        });
    }

    @FXML
    private TreeView<DoorsTreeNode> databaseTreeView;

    @FXML
    private ListView<String> tagsListView;

    @FXML
    private ComboBox<String> newTagComboBox;

    @FXML
    private TableView<Entry<String, String>> attributesTableView;

    @FXML
    private TableColumn<Entry<String, String>, String> attributeNameColumn;

    @FXML
    private TableColumn<Entry<String, String>, String> attributeValueColumn;

    private List<DoorsTreeNode> clipboard;

    private final HashSet<String> knownTags = new HashSet<>();
    private final DatabaseInterface database;

    @FXML
    public void addTagPressed() {
        String tag = newTagComboBox.getValue();
        knownTags.add(tag);
        databaseTreeView.getSelectionModel().getSelectedItem().getValue().setTag(tag);

        updateTagsListView();
        updateNewTagComboBox();
    }

    @FXML
    public void removeTagsPressed() throws IOException {
        tagsListView.getSelectionModel().getSelectedItems().forEach(t
                -> databaseTreeView.getSelectionModel().getSelectedItem().getValue().removeTag(t));

        updateTagsListView();
    }

    @FXML
    public void newFolderClicked() {
        executeCommand(new NewFolderCommand(getSelectedNode()));
    }

    @FXML
    public void newModuleClicked() {
        executeCommand(new NewModuleCommand(getSelectedNode()));
    }

    @FXML
    public void cutClicked() {
        copyClicked();
        deleteClicked();
    }

    @FXML
    public void copyClicked() {
        clipboard = databaseTreeView.getSelectionModel().getSelectedItems().stream().map(it -> it.getValue()).collect(Collectors.toList());
    }

    @FXML
    public void pasteClicked() {
        executeCommand(new PasteCommand(getSelectedNode(), clipboard));
    }

    @FXML
    public void deleteClicked() {
        executeCommand(new DeleteCommand(getSelectedNode()));
    }

    private DoorsTreeNode getSelectedNode() {
        return databaseTreeView.getSelectionModel().getSelectedItem().getValue();
    }

    private void updateNewTagComboBox() {
        final String oldValue = newTagComboBox.getValue();
        newTagComboBox.getItems().clear();
        for (final String tag : knownTags) {
            newTagComboBox.getItems().add(tag);
            Collections.sort(newTagComboBox.getItems());
        }
        newTagComboBox.setValue(oldValue);
    }

    private void updateTagsListView() {
        tagsListView.getItems().clear();
        databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .flatMap(it -> it.getValue().getTags().stream())
                .forEach(tagsListView.getItems()::add);
    }

    private void updateAttributesView() {
        attributesTableView.getItems().clear();
        attributesTableView.setItems(databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .flatMap(it -> it.getValue().getAttributes().entrySet().stream())
                .collect(Collectors.toCollection(() -> FXCollections.observableArrayList())));

    }

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }

    @Override
    public void save() throws IOException {
        this.database.flush();
    }

    private final WeakHashMap<DoorsTreeNode, DoorsTreeItem> treeNodeCache = new WeakHashMap<>();

    private class DoorsTreeItem extends TreeItem<DoorsTreeNode> implements Comparable<DoorsTreeItem> {

        private boolean childrenLoaded = false;

        public DoorsTreeItem(final DoorsTreeNode value) {
            super(value, (value instanceof DoorsModule) ? DatabasePaneImages.IMAGE_FORMAL.toImageView()
                    : DatabasePaneImages.IMAGE_FOLDER.toImageView());
            treeNodeCache.put(value, this);
        }

        @Override
        public ObservableList<TreeItem<DoorsTreeNode>> getChildren() {
            if (!isLeaf() && !childrenLoaded) {
                updateChildren();

            }
            return super.getChildren();
        }

        public void updateChildren() {
            final ObservableList<DoorsTreeItem> list = FXCollections.observableArrayList();
            for (final DoorsTreeNode c : getValue().getChildren()) {
                list.add(new DoorsTreeItem(c));
            }
            list.sort(Comparator.naturalOrder());
            super.getChildren().setAll(list);
            childrenLoaded = true;
        }

        @Override
        public boolean isLeaf() {
            return getValue() instanceof DoorsModule || getValue() instanceof DoorsObject;
        }

        @Override
        public int compareTo(DoorsTreeItem o2) {
            if (this.getValue() instanceof DoorsModule && !(o2.getValue() instanceof DoorsModule)) {
                return -1;
            } else if (!(this.getValue() instanceof DoorsModule) && o2.getValue() instanceof DoorsModule) {
                return 1;
            } else {
                return Objects.compare(this.getValue().getName(), o2.getValue().getName(), Comparator.naturalOrder());
            }

        }
    }

    public static class UpdateTreeItem implements UpdateAction<DatabasePaneController> {

        private final DoorsTreeNode node;

        public UpdateTreeItem(DoorsTreeNode node) {
            this.node = node;
        }

        @Override
        public void update(DatabasePaneController ctrl) {
            DoorsTreeItem parent = ctrl.treeNodeCache.get(node);

            final HashMap<DoorsTreeNode, Boolean> expanded = new HashMap<>();
            ctrl.traverseTreeItem(parent, i -> expanded.put(i.getValue(), i.isExpanded()));

            parent.updateChildren();

            ctrl.traverseTreeItem(parent, i -> i.setExpanded(expanded.containsKey(i.getValue()) && expanded.get(i.getValue())));
            parent.setExpanded(true);
        }

    }
}
