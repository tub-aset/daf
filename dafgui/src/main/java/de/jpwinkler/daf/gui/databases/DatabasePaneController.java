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
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
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
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;

public final class DatabasePaneController extends ApplicationPartController<DatabasePaneController> {

    public DatabasePaneController(ApplicationPaneController applicationController, DatabasePath path) {
        super(applicationController, path);

        databaseTreeView.setCellFactory(tv -> new NodeTextFieldTreeCell<>(
                it -> it.getName(),
                (it, newName) -> executeCommand(new RenameNodeCommand(it, newName)),
                it -> {
                }));

        databaseTreeView.setRoot(new DoorsTreeItem(getDatabaseInterface().getDatabaseObject().getRoot()));
        databaseTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateGui(UpdateModulesView, UpdateTagsView, UpdateAttributesView);
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

        modulesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateGui(UpdateTagsView, UpdateAttributesView);
        });

        moduleNameColumn.setEditable(true);
        moduleNameColumn.setCellFactory(tc -> new ModulesTextFieldTableCell<>(
                it -> it.getName(),
                (it, newName) -> this.executeCommand(new RenameNodeCommand(it, newName)),
                it -> this.open(this.getPath().withPath(it.getFullName()))
        ));
        moduleNameColumn.setCellValueFactory((it) -> new ReadOnlyObjectWrapper<>(it.getValue()));

        moduleDescriptionColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(DoorsSystemAttributes.MODULE_DESCRIPTION.getValue(String.class, param.getValue().getAttributes())));

        snapshotListsColumn.setEditable(true);
        snapshotListsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        snapshotListsColumn.setCellValueFactory(param -> {
            SimpleObjectProperty<String> prop = new SimpleObjectProperty<>(
                    getSnapshotLists(param.getValue())
            );

            prop.addListener((observable, oldValue, newValue) -> {
                TreeMap<String, Set<String>> data = ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve();
                Set<String> newValueSet = Stream.of(newValue.split(","))
                        .map(v -> v.trim())
                        .collect(Collectors.toSet());

                data.entrySet().forEach(e -> {
                    if (newValueSet.contains(e.getKey())) {
                        e.getValue().add(param.getValue().getFullName());
                    } else {
                        e.getValue().remove(param.getValue().getFullName());
                    }
                });
                ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.store(data);

                prop.setValue(getSnapshotLists(param.getValue()));
                modulesTableView.refresh();
            });
            return prop;
        });

        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), createSnapshotsMenu, this::createSnapshotFromListCicked));
        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), deleteSnapshotListMenu, this::deleteSnapshotListClicked));
        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), showSnapshotListMenu, this::showSnapshotListClicked));

        Map<String, ?> snapshotLists = ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve();
        populateSnapshotMenu(snapshotLists.keySet(), createSnapshotsMenu, this::createSnapshotFromListCicked);
        populateSnapshotMenu(snapshotLists.keySet(), deleteSnapshotListMenu, this::deleteSnapshotListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), showSnapshotListMenu, this::showSnapshotListClicked);
    }

    private static String getSnapshotLists(DoorsTreeNode node) {
        return ((Map<String, Set<String>>) ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve())
                .entrySet().stream()
                .filter(e -> e.getValue().contains(node.getFullName()))
                .map(e -> e.getKey())
                .sorted()
                .collect(Collectors.joining(", "));
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
    private TableView<DoorsModule> modulesTableView;

    @FXML
    private TableColumn<DoorsModule, DoorsModule> moduleNameColumn;

    @FXML
    private TableColumn<DoorsModule, String> moduleDescriptionColumn;

    @FXML
    private TableColumn<DoorsModule, String> snapshotListsColumn;

    @FXML
    private Menu createSnapshotsMenu;

    @FXML
    private Menu showSnapshotListMenu;

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

        public DoorsTreeItem(final DoorsTreeNode value) {
            super(value, getImage(value).toImageView());

            treeNodeCache.put(value, this);
            knownTags.addAll(value.getTags());
        }

        @Override
        public ObservableList<TreeItem<DoorsTreeNode>> getChildren() {
            if (!childrenLoaded) {
                updateChildren();

            }
            return super.getChildren();
        }

        private void updateChildren() {
            final ObservableList<DoorsTreeItem> list = FXCollections.observableArrayList();
            getValue().getChildren().stream()
                    .filter(n -> n instanceof DoorsFolder)
                    .map(n -> new DoorsTreeItem(n))
                    .forEach(list::add);

            list.sort(Comparator.naturalOrder());
            super.getChildren().setAll(list);
            childrenLoaded = true;
        }

        @Override
        public boolean isLeaf() {
            return childrenLoaded && getChildren().isEmpty();
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

    private class ModulesTextFieldTableCell<T> extends TextFieldTableCell<T, T> {

        private boolean editAllowed = false;

        public ModulesTextFieldTableCell(Function<T, String> toString, BiConsumer<T, String> editCommand, Consumer<T> opener) {
            setConverter(new StringConverter<T>() {
                @Override
                public String toString(T t) {
                    return toString.apply(t);
                }

                @Override
                public T fromString(String string) {
                    T it = ModulesTextFieldTableCell.this.getItem();
                    editCommand.accept(it, string);
                    return it;
                }
            });

            this.addEventFilter(MouseEvent.MOUSE_CLICKED, eh -> {
                if (!this.isEditing() && eh.getClickCount() >= 2 && eh.getButton() == MouseButton.PRIMARY) {
                    opener.accept(this.getItem());
                    eh.consume();
                } else if (eh.getClickCount() == 1 && eh.getButton() == MouseButton.SECONDARY) {
                    editAllowed = true;
                    super.getTableView().edit(this.getTableRow().getIndex(), this.getTableColumn());
                    editAllowed = false;
                    eh.consume();
                }
            });
        }

        @Override
        public void startEdit() {
            if (editAllowed) {
                super.startEdit();
            }
        }

    }

    private class NodeTextFieldTreeCell<T> extends TextFieldTreeCell<T> {

        private boolean editAllowed = false;

        public NodeTextFieldTreeCell(Function<T, String> toString, BiConsumer<T, String> editCommand, Consumer<T> opener) {
            this.setConverter(new StringConverter<>() {
                @Override
                public String toString(T node) {
                    return toString.apply(node);
                }

                @Override
                public T fromString(String newName) {
                    T node = NodeTextFieldTreeCell.this.getItem();
                    editCommand.accept(node, newName);
                    return node;
                }
            });

            this.addEventFilter(MouseEvent.MOUSE_CLICKED, eh -> {
                if (!this.isEditing() && eh.getClickCount() >= 2 && eh.getButton() == MouseButton.PRIMARY) {
                    opener.accept(this.getItem());
                    eh.consume();
                } else if (eh.getClickCount() == 1 && eh.getButton() == MouseButton.SECONDARY) {
                    editAllowed = true;
                    super.getTreeView().edit(this.getTreeItem());
                    editAllowed = false;
                    eh.consume();
                }
            });
        }

        @Override
        public void startEdit() {
            if (editAllowed) {
                super.startEdit();
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

    public static final UpdateAction<DatabasePaneController> UpdateModulesView = ctrl -> {
        ctrl.modulesTableView.getItems().clear();
        ctrl.modulesTableView.setItems(ctrl.databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .flatMap(it -> it.getValue().getChildren().stream())
                .filter(it -> it instanceof DoorsModule)
                .map(it -> (DoorsModule) it)
                .collect(Collectors.toCollection(() -> FXCollections.observableArrayList())));
    };

    public static final UpdateAction<DatabasePaneController> UpdateTagsView = ctrl -> {
        final String oldValue = ctrl.newTagComboBox.getValue();
        ctrl.newTagComboBox.getItems().clear();
        for (final String tag : ctrl.knownTags) {
            ctrl.newTagComboBox.getItems().add(tag);
            Collections.sort(ctrl.newTagComboBox.getItems());
        }
        ctrl.newTagComboBox.setValue(oldValue);

        ctrl.tagsListView.getItems().clear();

        Stream<? extends DoorsTreeNode> selModel = ctrl.modulesTableView.getSelectionModel().isEmpty()
                ? ctrl.databaseTreeView.getSelectionModel().getSelectedItems().stream().map(it -> it.getValue())
                : ctrl.modulesTableView.getSelectionModel().getSelectedItems().stream();
        selModel
                .flatMap(it -> it.getTags().stream())
                .sorted()
                .forEach(ctrl.tagsListView.getItems()::add);
    };

    public static final UpdateAction<DatabasePaneController> UpdateAttributesView = ctrl -> {
        ctrl.attributesTableView.getItems().clear();
        Stream<? extends DoorsTreeNode> selModel = ctrl.modulesTableView.getSelectionModel().isEmpty()
                ? ctrl.databaseTreeView.getSelectionModel().getSelectedItems().stream().map(it -> it.getValue())
                : ctrl.modulesTableView.getSelectionModel().getSelectedItems().stream();

        ctrl.attributesTableView.setItems(selModel
                .flatMap(it -> it.getAttributes().entrySet().stream())
                .filter(it -> DoorsSystemAttributes.getForKey(it.getKey()).map(v -> !v.isSystemKey()).orElse(true))
                .collect(Collectors.toCollection(() -> FXCollections.observableArrayList())));

    };
}
