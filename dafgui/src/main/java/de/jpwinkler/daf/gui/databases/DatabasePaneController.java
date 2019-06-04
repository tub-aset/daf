package de.jpwinkler.daf.gui.databases;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.ApplicationPaneController;
import de.jpwinkler.daf.gui.ApplicationPartController;
import de.jpwinkler.daf.gui.ApplicationPreferences;
import de.jpwinkler.daf.gui.CommandStack;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.databases.commands.AddTagCommand;
import de.jpwinkler.daf.gui.databases.commands.DeleteAttributesCommand;
import de.jpwinkler.daf.gui.databases.commands.DeleteCommand;
import de.jpwinkler.daf.gui.databases.commands.EditAttributesCommand;
import de.jpwinkler.daf.gui.databases.commands.NewFolderCommand;
import de.jpwinkler.daf.gui.databases.commands.NewModuleCommand;
import de.jpwinkler.daf.gui.databases.commands.PasteCommand;
import de.jpwinkler.daf.gui.databases.commands.RemoveTagCommand;
import de.jpwinkler.daf.gui.databases.commands.RenameAttributesCommand;
import de.jpwinkler.daf.gui.databases.commands.RenameNodeCommand;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
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
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;

public final class DatabasePaneController extends ApplicationPartController<DatabasePaneController> {

    public DatabasePaneController(ApplicationPaneController applicationController, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        super(applicationController, path, databaseInterface, databaseCommandStack);

        if (databaseInterface.isReadOnly()) {
            databaseTreeView.setEditable(false);
            moduleNameColumn.setEditable(false);
            moduleDescriptionColumn.setEditable(false);
            attributesTableView.setEditable(false);
            newTagComboBox.setDisable(true);
        }

        databaseTreeView.setCellFactory(tv -> new NodeTextFieldTreeCell<>(
                it -> it.getName(),
                (it, newName) -> executeCommand(new RenameNodeCommand(it, newName)),
                it -> {
                }));

        databaseTreeView.setRoot(new DoorsTreeItem(databaseInterface.getDatabaseRoot()));
        databaseTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateGui(UpdateModulesView, UpdateTagsView, UpdateAttributesView, UpdateNodeTitle);
        });
        databaseTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        mainSplitPane.setDividerPositions((double) ApplicationPreferences.DATABASE_PANE_SPLITPOS.retrieve());
        mainSplitPane.getDividers().forEach(d -> {
            d.positionProperty().addListener((obs, oldValue, newValue) -> {
                ApplicationPreferences.DATABASE_PANE_SPLITPOS.store(newValue.doubleValue());
            });
        });

        modulesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateGui(UpdateTagsView, UpdateAttributesView, UpdateNodeTitle);
        });

        moduleNameColumn.setCellFactory(tc -> new CustomTextFieldTableCell<>(tc,
                it -> it.getName(),
                (it, newName) -> this.executeCommand(new RenameNodeCommand(it, newName)),
                it -> this.open(this.getPath().withPath(it.getFullName()), OpenFlag.OPEN_ONLY)));
        moduleDescriptionColumn.setCellFactory(tc -> new CustomTextFieldTableCell<>(tc,
                it -> DoorsAttributes.MODULE_DESCRIPTION.getValue(String.class, it),
                (it, newValue) -> {
                    this.executeCommand(new EditAttributesCommand(DoorsAttributes.MODULE_DESCRIPTION.getKey(), newValue, it));
                },
                it -> this.open(this.getPath().withPath(it.getFullName()), OpenFlag.OPEN_ONLY)));
        snapshotListsColumn.setCellFactory(tc -> new CustomTextFieldTableCell<>(tc,
                it -> it == null ? "" : getSnapshotLists(it),
                (it, newLists) -> {
                    TreeMap<String, Set<String>> data = ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve();
                    Set<String> newValueSet = Stream.of(newLists.split(","))
                            .map(v -> v.trim())
                            .collect(Collectors.toSet());

                    data.entrySet().forEach(e -> {
                        if (newValueSet.contains(e.getKey())) {
                            e.getValue().add(it.getFullName());
                        } else {
                            e.getValue().remove(it.getFullName());
                        }
                    });
                    ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.store(data);
                    modulesTableView.refresh();
                },
                it -> this.open(this.getPath().withPath(it.getFullName()), OpenFlag.OPEN_ONLY)));

        attributeNameColumn.setCellFactory(tc -> new CustomTextFieldTableCell<>(tc,
                it -> it.getKey(),
                (it, newKey) -> {
                    getCurrentDoorsTreeNode()
                            .map(module -> new RenameAttributesCommand(it.getKey(), newKey, module))
                            .forEach(this::executeCommand);
                }));
        attributeNameColumn.setPrefWidth((double) ApplicationPreferences.DATABASE_PANE_ATTRIBUTENAME_WIDTH.retrieve());
        attributeNameColumn.widthProperty().addListener((obs, oldValue, newValue) -> {
            ApplicationPreferences.DATABASE_PANE_ATTRIBUTENAME_WIDTH.store(newValue.doubleValue());
        });

        attributeValueColumn.setCellFactory(tc -> new CustomTextFieldTableCell<>(tc,
                it -> it.getValue(),
                (it, newValue) -> {
                    this.modulesTableView.getSelectionModel().getSelectedItems().stream()
                            .map(module -> new EditAttributesCommand(it.getKey(), newValue, module))
                            .forEach(this::executeCommand);
                }));
        attributeValueColumn.setPrefWidth((double) ApplicationPreferences.DATABASE_PANE_ATTRIBUTEVALUE_WIDTH.retrieve());
        attributeValueColumn.widthProperty().addListener((obs, oldValue, newValue) -> {
            ApplicationPreferences.DATABASE_PANE_ATTRIBUTEVALUE_WIDTH.store(newValue.doubleValue());
        });

        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), createSnapshotsMenu, this::createSnapshotFromListClicked));
        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), deleteSnapshotListMenu, this::deleteSnapshotListClicked));
        ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), showSnapshotListMenu, this::showSnapshotListClicked));

        Map<String, ?> snapshotLists = ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve();
        populateSnapshotMenu(snapshotLists.keySet(), createSnapshotsMenu, this::createSnapshotFromListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), deleteSnapshotListMenu, this::deleteSnapshotListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), showSnapshotListMenu, this::showSnapshotListClicked);
    }

    private static String getSnapshotLists(DoorsTreeNode node) {
        return ((Map<String, TreeSet<String>>) ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve())
                .entrySet().stream()
                .filter(e -> isInSnapshotList(e.getValue(), node))
                .map(e -> e.getKey())
                .sorted()
                .collect(Collectors.joining(", "));
    }

    private static boolean isInSnapshotList(TreeSet<String> list, DoorsTreeNode node) {
        String fn = node.getFullName();
        String ceil = list.ceiling(fn);
        return ceil != null && ceil.startsWith(fn);
    }

    @FXML
    private SplitPane mainSplitPane;

    @FXML
    private TreeView<DoorsTreeNode> databaseTreeView;

    @FXML
    private TitledPane currentNodePane;

    @FXML
    private TableView<DoorsModule> modulesTableView;

    @FXML
    private TableColumn<DoorsModule, DoorsModule> moduleNameColumn;

    @FXML
    private TableColumn<DoorsModule, DoorsModule> moduleDescriptionColumn;

    @FXML
    private TableColumn<DoorsModule, DoorsModule> snapshotListsColumn;

    @FXML
    private ListView<String> tagsListView;

    @FXML
    private ComboBox<String> newTagComboBox;

    @FXML
    private TableView<Entry<String, String>> attributesTableView;

    @FXML
    private TableColumn<Entry<String, String>, Entry<String, String>> attributeNameColumn;

    @FXML
    private TableColumn<Entry<String, String>, Entry<String, String>> attributeValueColumn;

    @FXML
    private Menu createSnapshotsMenu;

    @FXML
    private Menu showSnapshotListMenu;

    @FXML
    private Menu deleteSnapshotListMenu;

    private List<DoorsTreeNode> nodeClipboard;
    private List<Entry<String, String>> attributeClipboard;
    private List<String> tagsClipboard;

    private final HashSet<String> knownTags = new HashSet<>();

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
    public void newAttributeClicked() {
        getCurrentDoorsTreeNode().forEach(it -> executeCommand(new EditAttributesCommand("New attribute", "", it)));
    }

    @FXML
    public void newTagEnterPressed(KeyEvent ev) {
        String value = newTagComboBox.getValue();
        if (ev.getCode() != KeyCode.ENTER || value == null || value.isEmpty() || value.isBlank()) {
            return;
        }

        getCurrentDoorsTreeNode().forEach(it -> executeCommand(new AddTagCommand(it, Collections.singleton(value.trim()), knownTags)));
        ev.consume();
    }

    private Stream<? extends DoorsTreeNode> getCurrentDoorsTreeNode() {
        return modulesTableView.getSelectionModel().isEmpty()
                ? databaseTreeView.getSelectionModel().getSelectedItems().stream().map(it -> it.getValue())
                : modulesTableView.getSelectionModel().getSelectedItems().stream();
    }

    @FXML
    public void cutClicked() {
        copyClicked();
        deleteClicked();
    }

    @FXML
    public void copyClicked() {
        if (databaseTreeView.isFocused()) {
            nodeClipboard = databaseTreeView.getSelectionModel().getSelectedItems().stream().map(it -> it.getValue()).collect(Collectors.toList());
        }
        if (modulesTableView.isFocused()) {
            nodeClipboard = modulesTableView.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
        }
        if (attributesTableView.isFocused()) {
            attributeClipboard = attributesTableView.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
        }
        if (tagsListView.isFocused()) {
            tagsClipboard = tagsListView.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
        }
    }

    @FXML
    public void pasteClicked() {
        if (databaseTreeView.isFocused()) {
            databaseTreeView.getSelectionModel().getSelectedItems().stream()
                    .limit(1)
                    .forEach(it -> executeCommand(new PasteCommand(it.getValue(), nodeClipboard)));
        }
        if (modulesTableView.isFocused()) {
            modulesTableView.getSelectionModel().getSelectedItems().stream()
                    .limit(1)
                    .forEach(it -> executeCommand(new PasteCommand(it.getParent(), nodeClipboard)));
        }
        if (attributesTableView.isFocused()) {
            getCurrentDoorsTreeNode()
                    .map(it -> new EditAttributesCommand(attributeClipboard, it))
                    .forEach(this::executeCommand);
        }
        if (tagsListView.isFocused()) {
            getCurrentDoorsTreeNode()
                    .map(it -> new AddTagCommand(it, tagsClipboard, knownTags))
                    .forEach(this::executeCommand);
        }
    }

    @FXML
    public void deleteClicked() {
        if (databaseTreeView.isFocused()) {
            databaseTreeView.getSelectionModel().getSelectedItems().stream()
                    .map(it -> it.getValue())
                    .map(it -> new DeleteCommand(it))
                    .forEach(it -> {
                        Platform.runLater(() -> this.executeCommand(it));
                    });
        }
        if (modulesTableView.isFocused()) {
            modulesTableView.getSelectionModel().getSelectedItems().stream()
                    .map(it -> new DeleteCommand(it))
                    .forEach(it -> {
                        Platform.runLater(() -> this.executeCommand(it));
                    });
        }
        if (attributesTableView.isFocused()) {
            getCurrentDoorsTreeNode()
                    .map(it -> new DeleteAttributesCommand(attributesTableView.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList()), it))
                    .forEach(this::executeCommand);
        }
        if (tagsListView.isFocused()) {
            getCurrentDoorsTreeNode()
                    .map(it -> new RemoveTagCommand(it, tagsListView.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList())))
                    .forEach(this::executeCommand);
        }
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

    @FXML
    public void createFullSnapshotClicked() {
        createSnapshot(x -> true);
    }

    private void createSnapshotFromListClicked(String snapshotList) {
        TreeSet<String> sl = ((TreeMap<String, TreeSet<String>>) ApplicationPreferences.DATABASE_PANE_SNAPSHOT_LISTS.retrieve()).get(snapshotList);
        createSnapshot(node -> isInSnapshotList(sl, node));
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

    private class CustomTextFieldTableCell<T> extends TextFieldTableCell<T, T> {

        private boolean editAllowed = false;

        public CustomTextFieldTableCell(TableColumn<T, T> tc, Function<T, String> toString, BiConsumer<T, String> editCommand) {
            this(tc, toString, editCommand, x -> {
            });
        }

        public CustomTextFieldTableCell(TableColumn<T, T> tc, Function<T, String> toString, BiConsumer<T, String> editCommand, Consumer<T> opener) {
            tc.setCellValueFactory((it) -> new ReadOnlyObjectWrapper<>(it.getValue()));
            setConverter(new StringConverter<T>() {
                @Override
                public String toString(T t) {
                    return toString.apply(t);
                }

                @Override
                public T fromString(String string) {
                    T it = CustomTextFieldTableCell.this.getItem();
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

    public static final UpdateAction<DatabasePaneController> UpdateTreeView = ctrl -> {
        ctrl.databaseTreeView.refresh();
    };

    public static final UpdateAction<DatabasePaneController> UpdateNodeTitle = ctrl -> {
        ctrl.getCurrentDoorsTreeNode().findFirst()
                .ifPresent(n -> ctrl.currentNodePane.setText(n.getFullName()));
    };

    public static final UpdateAction<DatabasePaneController> UpdateModulesView = ctrl -> {
        ctrl.modulesTableView.getItems().clear();
        ctrl.modulesTableView.setItems(ctrl.databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .flatMap(it -> it.getValue().getChildren().stream())
                .filter(it -> it instanceof DoorsModule)
                .map(it -> (DoorsModule) it)
                .peek(it -> ctrl.knownTags.addAll(it.getTags()))
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

        ctrl.getCurrentDoorsTreeNode().flatMap(it -> it.getTags().stream())
                .sorted()
                .forEach(ctrl.tagsListView.getItems()::add);
    };

    public static final UpdateAction<DatabasePaneController> UpdateAttributesView = ctrl -> {
        ctrl.attributesTableView.getItems().clear();
        ctrl.attributesTableView.setItems(ctrl.getCurrentDoorsTreeNode()
                .flatMap(it -> it.getAttributes().entrySet().stream())
                .filter(it -> DoorsAttributes.getForKey(it.getKey()).map(v -> !v.isSystemKey()).orElse(true))
                .collect(Collectors.toCollection(() -> FXCollections.observableArrayList())));

    };
}
