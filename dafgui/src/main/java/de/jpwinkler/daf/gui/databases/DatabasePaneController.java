package de.jpwinkler.daf.gui.databases;

import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.ApplicationPaneController;
import de.jpwinkler.daf.gui.ApplicationPartController;
import de.jpwinkler.daf.gui.ApplicationPreferences;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.databases.commands.AddTagCommand;
import de.jpwinkler.daf.gui.databases.commands.DeleteCommand;
import de.jpwinkler.daf.gui.databases.commands.NewFolderCommand;
import de.jpwinkler.daf.gui.databases.commands.NewModuleCommand;
import de.jpwinkler.daf.gui.databases.commands.PasteCommand;
import de.jpwinkler.daf.gui.databases.commands.RemoveTagCommand;
import de.jpwinkler.daf.gui.databases.commands.RenameNodeCommand;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsSystemAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;

public final class DatabasePaneController extends ApplicationPartController<DatabasePaneController> {

    public DatabasePaneController(ApplicationPaneController applicationController, DatabasePath path) {
        super(applicationController, path);

        databaseTreeView.setCellFactory(tv -> {
            TextFieldTreeCell<DoorsTreeNode> tc = new TextFieldTreeCell<>(new StringConverter<>() {
                private DoorsTreeNode node;

                @Override
                public String toString(DoorsTreeNode node) {
                    this.node = node;
                    return node.getName();
                }

                @Override
                public DoorsTreeNode fromString(String newName) {
                    DoorsTreeNode node = this.node;
                    this.node = null;
                    executeCommand(new RenameNodeCommand(node, newName));
                    return node;
                }
            });

            return tc;

        });

        databaseTreeView.setRoot(new DoorsTreeItem(getDatabaseInterface().getDatabaseObject().getRoot()));
        databaseTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateGui(UpdateTagsSection, UpdateAttributesView);
        });
        databaseTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        mainSplitPane.setDividerPositions((double) ApplicationPreferences.DATABASE_PANE_SPLITPOS.retrieve());
        mainSplitPane.getDividers().forEach(d -> {
            d.positionProperty().addListener((obs, oldValue, newValue) -> {
                ApplicationPreferences.DATABASE_PANE_SPLITPOS.store(newValue.doubleValue());
            });
        });

        attributeNameColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getKey()));
        attributeValueColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue()));

        attributeNameColumn.setPrefWidth((double) ApplicationPreferences.DATABASE_PANE_ATTRIBUTENAME_WIDTH.retrieve());
        attributeNameColumn.widthProperty().addListener((obs, oldValue, newValue) -> {
            ApplicationPreferences.DATABASE_PANE_ATTRIBUTENAME_WIDTH.store(newValue.doubleValue());
        });

        attributeValueColumn.setPrefWidth((double) ApplicationPreferences.DATABASE_PANE_ATTRIBUTEVALUE_WIDTH.retrieve());
        attributeValueColumn.widthProperty().addListener((obs, oldValue, newValue) -> {
            ApplicationPreferences.DATABASE_PANE_ATTRIBUTEVALUE_WIDTH.store(newValue.doubleValue());
        });

        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), createSnapshotsMenu, this::createSnapshotFromListCicked));
        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), deleteSnapshotListMenu, this::deleteSnapshotListClicked));
        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), showSnapshotsMenu, this::showSnapshotListClicked));
        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), addToSnapshotListMenu, this::addToSnapshotListClicked));

        Map<String, ?> snapshotLists = ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve();
        populateSnapshotMenu(snapshotLists.keySet(), createSnapshotsMenu, this::createSnapshotFromListCicked);
        populateSnapshotMenu(snapshotLists.keySet(), deleteSnapshotListMenu, this::deleteSnapshotListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), showSnapshotsMenu, this::showSnapshotListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), addToSnapshotListMenu, this::addToSnapshotListClicked);
    }

    @FXML
    private SplitPane mainSplitPane;

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

    @FXML
    private Menu createSnapshotsMenu;

    @FXML
    private Menu showSnapshotsMenu;

    @FXML
    private Menu addToSnapshotListMenu;

    @FXML
    private Menu deleteSnapshotListMenu;

    private List<DoorsTreeNode> clipboard;

    private final HashSet<String> knownTags = new HashSet<>();

    @FXML
    public void addTagPressed() {
        databaseTreeView.getSelectionModel().getSelectedItems().forEach(it
                -> executeCommand(new AddTagCommand(it.getValue(), newTagComboBox.getValue(), knownTags)));
    }

    @FXML
    public void removeTagsPressed() throws IOException {
        databaseTreeView.getSelectionModel().getSelectedItems().forEach(
                it -> tagsListView.getSelectionModel().getSelectedItems().forEach(
                        t -> executeCommand(new RemoveTagCommand(it.getValue(), t))));
    }

    @FXML
    public void newFolderClicked() {
        databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .forEach(it -> executeCommand(new NewFolderCommand(it.getValue())));
    }

    @FXML
    public void newModuleClicked() {
        databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .forEach(it -> executeCommand(new NewModuleCommand(it.getValue())));
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
        databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .limit(1)
                .forEach(it -> executeCommand(new PasteCommand(it.getValue(), clipboard)));
    }

    @FXML
    public void deleteClicked() {
        databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .forEach(it -> executeCommand(new DeleteCommand(it.getValue())));
    }

    @FXML
    public void openModulesClicked() {
        databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .map(it -> it.getValue())
                .filter(it -> it instanceof DoorsModule)
                .map(it -> it.getFullName())
                .map(this.getPath()::withPath)
                .forEach(this::open);
    }

    @FXML
    public void addSnapshotListClicked() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add a snapshot list");
        dialog.setHeaderText("Please enter a name for your list");

        dialog.showAndWait().ifPresent(ln -> {
            TreeMap<String, TreeSet<String>> snapshotLists = ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve();
            snapshotLists.putIfAbsent(ln, new TreeSet<>());
            ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.store(snapshotLists);
        });
    }

    public void deleteSnapshotListClicked(String snapshotList) {
        TreeMap<String, ?> snapshotLists = ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve();
        snapshotLists.remove(snapshotList);
        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.store(snapshotLists);
    }

    private void createSnapshotFromListCicked(String snapshotList) {
        System.out.println(snapshotList);
    }

    private void showSnapshotListClicked(String snapshotList) {
        TreeMap<String, TreeSet<String>> snapshotLists = ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve();

        Alert alert = new Alert(Alert.AlertType.NONE,
                snapshotLists.get(snapshotList).isEmpty() ? "This list is empty."
                : snapshotLists.get(snapshotList).stream().collect(Collectors.joining("\n")), ButtonType.OK);
        alert.setTitle("Snapshot list " + snapshotList);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    private void addToSnapshotListClicked(String snapshotList) {
        TreeMap<String, TreeSet<String>> snapshotLists = ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve();
        databaseTreeView.getSelectionModel().getSelectedItems()
                .stream().map(si -> si.getValue().getFullName()).forEach(snapshotLists.get(snapshotList)::add);
        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.store(snapshotLists);
    }

    private void populateSnapshotMenu(Collection<String> snapshotLists, Menu menu, Consumer<String> action) {
        menu.getItems().clear();
        snapshotLists.forEach(ln -> {
            MenuItem mi = new MenuItem(ln);
            mi.setOnAction(eh -> action.accept(ln));
            menu.getItems().add(mi);
        });
    }

    private <T> void traverseTreeItem(final TreeItem<T> root, final Consumer<TreeItem<T>> f) {
        f.accept(root);
        for (final TreeItem<T> child : root.getChildren()) {
            traverseTreeItem(child, f);
        }
    }

    private final WeakHashMap<DoorsTreeNode, DoorsTreeItem> treeNodeCache = new WeakHashMap<>();

    private static DatabasePaneImages getImage(DoorsTreeNode value) {
        if (value.getParent() == null) {
            return DatabasePaneImages.IMAGE_DB;
        } else if (value instanceof DoorsModule) {
            return DatabasePaneImages.IMAGE_FORMAL;
        } else {
            return DatabasePaneImages.IMAGE_FOLDER;

        }
    }

    private class DoorsTreeItem extends TreeItem<DoorsTreeNode> implements Comparable<DoorsTreeItem> {

        private boolean childrenLoaded = false;
        private final Class<? extends DoorsTreeNode> valueCls;

        public DoorsTreeItem(final DoorsTreeNode value) {
            super(value, getImage(value).toImageView());
            this.valueCls = value.getClass();

            treeNodeCache.put(value, this);
            knownTags.addAll(value.getTags());
        }

        @Override
        public ObservableList<TreeItem<DoorsTreeNode>> getChildren() {
            if (isFolder() && !childrenLoaded) {
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
            return !isFolder() || (childrenLoaded && getChildren().isEmpty());
        }

        private boolean isFolder() {
            return !DoorsModule.class.isAssignableFrom(valueCls) && !DoorsObject.class.isAssignableFrom(valueCls);
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

    public static final UpdateAction<DatabasePaneController> UpdateTreeItem(DoorsTreeNode node) {
        return (ctrl) -> {

            DoorsTreeItem parent = ctrl.treeNodeCache.get(node);

            final HashMap<DoorsTreeNode, Boolean> expanded = new HashMap<>();
            ctrl.traverseTreeItem(parent, i -> expanded.put(i.getValue(), i.isExpanded()));

            parent.updateChildren();

            ctrl.traverseTreeItem(parent, i -> i.setExpanded(expanded.containsKey(i.getValue()) && expanded.get(i.getValue())));
            parent.setExpanded(true);
        };
    }

    public static final UpdateAction<DatabasePaneController> RefreshTreeView = ctrl -> {
        ctrl.databaseTreeView.refresh();
    };

    public static final UpdateAction<DatabasePaneController> UpdateTagsSection = ctrl -> {
        final String oldValue = ctrl.newTagComboBox.getValue();
        ctrl.newTagComboBox.getItems().clear();
        for (final String tag : ctrl.knownTags) {
            ctrl.newTagComboBox.getItems().add(tag);
            Collections.sort(ctrl.newTagComboBox.getItems());
        }
        ctrl.newTagComboBox.setValue(oldValue);

        ctrl.tagsListView.getItems().clear();
        ctrl.databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .flatMap(it -> it.getValue().getTags().stream())
                .sorted()
                .forEach(ctrl.tagsListView.getItems()::add);
    };

    public static final UpdateAction<DatabasePaneController> UpdateAttributesView = ctrl -> {
        ctrl.attributesTableView.getItems().clear();
        ctrl.attributesTableView.setItems(ctrl.databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .flatMap(it -> it.getValue().getAttributes().entrySet().stream())
                .filter(it -> DoorsSystemAttributes.getForKey(it.getKey()).map(v -> !v.isSystemKey()).orElse(true))
                .collect(Collectors.toCollection(() -> FXCollections.observableArrayList())));
    };
}
