package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import java.util.HashSet;
import java.util.Objects;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public final class DatabasePaneController extends ApplicationPartController {

    public static final ApplicationPartController openLocal(ApplicationPaneController applicationController, ApplicationURI uri) {
        try {
            return new DatabasePaneController(applicationController, DatabaseInterface.openFileDatabase(
                    uri.isValid() ? new File(uri.getPath()).toPath() : null));
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

        updateDatabaseTree();
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

    private void updateDatabaseTree() {
        final HashMap<DoorsTreeNode, Boolean> expanded = new HashMap<>();

        if (databaseTreeView.getRoot() != null) {
            traverseTreeItem(databaseTreeView.getRoot(), i -> expanded.put(i.getValue(), i.isExpanded()));
        }

        databaseTreeView.setRoot(new DoorsTreeItem(database.getDatabaseObject().getRoot()));
        traverseTreeItem(databaseTreeView.getRoot(), i -> i.setExpanded(expanded.containsKey(i.getValue()) && expanded.get(i.getValue())));
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
        for (String tag : databaseTreeView.getSelectionModel().getSelectedItem().getValue().getTags()) {
            tagsListView.getItems().add(tag);
        }
    }

    private void updateAttributesView() {
        attributesTableView.getItems().clear();
        attributesTableView.setItems(FXCollections.observableArrayList(databaseTreeView.getSelectionModel().getSelectedItem().getValue().getAttributes().entrySet()));

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

    private static class DoorsTreeItem extends TreeItem<DoorsTreeNode> implements Comparable<DoorsTreeItem> {

        private boolean childrenLoaded = false;

        public DoorsTreeItem(final DoorsTreeNode value) {
            super(value, (value instanceof DoorsModule) ? new ImageView(IMAGE_FORMAL) : new ImageView(IMAGE_FOLDER));
        }

        @Override
        public ObservableList<TreeItem<DoorsTreeNode>> getChildren() {
            if (!(getValue() instanceof DoorsModule)) {
                if (!childrenLoaded) {
                    super.getChildren().setAll(buildChildren());
                }
                childrenLoaded = true;
            }
            return super.getChildren();
        }

        private ObservableList<DoorsTreeItem> buildChildren() {
            if (getValue() != null) {
                final ObservableList<DoorsTreeItem> list = FXCollections.observableArrayList();
                for (final DoorsTreeNode c : getValue().getChildren()) {
                    list.add(new DoorsTreeItem(c));
                }
                list.sort(Comparator.naturalOrder());
                return list;
            } else {
                return FXCollections.emptyObservableList();
            }
        }

        @Override
        public boolean isLeaf() {
            return getValue() instanceof DoorsModule;
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

        public static final Image IMAGE_FOLDER = new Image(DatabasePaneController.class.getResourceAsStream("icons/doors_folder.png"));
        public static final Image IMAGE_DB = new Image(DatabasePaneController.class.getResourceAsStream("icons/doors_db.png"));
        public static final Image IMAGE_FORMAL = new Image(DatabasePaneController.class.getResourceAsStream("icons/doors_formal.png"));
        public static final Image IMAGE_LINK = new Image(DatabasePaneController.class.getResourceAsStream("icons/doors_link.png"));
        public static final Image IMAGE_PROJECT = new Image(DatabasePaneController.class.getResourceAsStream("icons/doors_project.png"));
    }

}
