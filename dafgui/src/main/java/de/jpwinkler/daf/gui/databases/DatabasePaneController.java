package de.jpwinkler.daf.gui.databases;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.ApplicationPaneController;
import de.jpwinkler.daf.gui.ApplicationPart;
import de.jpwinkler.daf.gui.ApplicationPartController;
import de.jpwinkler.daf.gui.commands.CommandStack;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.controls.CustomTextFieldTableCell;
import de.jpwinkler.daf.gui.controls.CustomTextFieldTreeCell;
import de.jpwinkler.daf.gui.controls.EmptySelectionModel;
import de.jpwinkler.daf.gui.controls.ExtensionPane;
import de.jpwinkler.daf.gui.controls.ForwardingMultipleSelectionModel;
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
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Arrays;
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
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
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
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import org.pf4j.PluginWrapper;

public final class DatabasePaneController extends ApplicationPartController<DatabasePaneController> {

    public DatabasePaneController(ApplicationPaneController applicationController, ApplicationPart applicationPart, DatabasePath path, DatabaseInterface databaseInterface, CommandStack databaseCommandStack) {
        super(applicationController, applicationPart, path, databaseInterface, databaseCommandStack);

        if (databaseInterface.isReadOnly()) {
            databaseTreeView.setEditable(false);
            moduleNameColumn.setEditable(false);
            moduleDescriptionColumn.setEditable(false);
            attributesTableView.setEditable(false);
            newTagComboBox.setDisable(true);
        }

        databaseTreeView.setCellFactory(tv -> new CustomTextFieldTreeCell<>(
                it -> it.getName(),
                (it, newName) -> executeCommand(new RenameNodeCommand(it, newName)),
                it -> {
                }));

        databaseTreeView.setRoot(new DoorsTreeItem((DoorsFolder) databaseInterface.getDatabaseRoot()));
        databaseTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateGui(UpdateModulesView, UpdateTagsView, UpdateAttributesView, UpdateNodeTitle);
        });
        databaseTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        mainSplitPane.setDividerPositions((double[]) DatabasePanePreferences.SPLITPOS.retrieve());
        mainSplitPane.getDividers().forEach(d -> {
            d.positionProperty().addListener((obs, oldValue, newValue) -> {
                DatabasePanePreferences.SPLITPOS.store(mainSplitPane.getDividerPositions());
            });
        });
        sideExtensionPane.visiblePanesProperty().addListener(change -> {
            this.updateExtensionPaneVisibility(sideExtensionPane, mainSplitPane);
        });

        bottomSplitPane.setDividerPositions((double[]) DatabasePanePreferences.BOTTOM_SPLITPOS.retrieve());
        bottomSplitPane.getDividers().forEach(d -> {
            d.positionProperty().addListener((obs, oldValue, newValue) -> {
                DatabasePanePreferences.BOTTOM_SPLITPOS.store(bottomSplitPane.getDividerPositions());
            });
        });
        bottomExtensionPane.visiblePanesProperty().addListener(change -> {
            this.updateExtensionPaneVisibility(bottomExtensionPane, bottomSplitPane);
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
                    TreeMap<String, Set<String>> data = DatabasePanePreferences.SNAPSHOT_LISTS.retrieve();
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
                    DatabasePanePreferences.SNAPSHOT_LISTS.store(data);
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
        attributeNameColumn.setPrefWidth((double) DatabasePanePreferences.ATTRIBUTENAME_WIDTH.retrieve());
        attributeNameColumn.widthProperty().addListener((obs, oldValue, newValue) -> {
            DatabasePanePreferences.ATTRIBUTENAME_WIDTH.store(newValue.doubleValue());
        });

        attributeValueColumn.setCellFactory(tc -> new CustomTextFieldTableCell<>(tc,
                it -> it.getValue(),
                (it, newValue) -> {
                    this.modulesTableView.getSelectionModel().getSelectedItems().stream()
                            .map(module -> new EditAttributesCommand(it.getKey(), newValue, module))
                            .forEach(this::executeCommand);
                }));
        attributeValueColumn.setPrefWidth((double) DatabasePanePreferences.ATTRIBUTEVALUE_WIDTH.retrieve());
        attributeValueColumn.widthProperty().addListener((obs, oldValue, newValue) -> {
            DatabasePanePreferences.ATTRIBUTEVALUE_WIDTH.store(newValue.doubleValue());
        });

        DatabasePanePreferences.SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), createSnapshotsMenu, this::createSnapshotFromListClicked));
        DatabasePanePreferences.SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), deleteSnapshotListMenu, this::deleteSnapshotListClicked));
        DatabasePanePreferences.SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), editSnapshotListMenu, this::editSnapshotListClicked));

        Map<String, ?> snapshotLists = DatabasePanePreferences.SNAPSHOT_LISTS.retrieve();
        populateSnapshotMenu(snapshotLists.keySet(), createSnapshotsMenu, this::createSnapshotFromListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), deleteSnapshotListMenu, this::deleteSnapshotListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), editSnapshotListMenu, this::editSnapshotListClicked);
    }

    private static String getSnapshotLists(DoorsTreeNode node) {
        return ((Map<String, TreeSet<String>>) DatabasePanePreferences.SNAPSHOT_LISTS.retrieve())
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

    private void updateExtensionPaneVisibility(ExtensionPane extPane, SplitPane splitPane) {
        if (!extPane.visiblePanesProperty().get()) {
            splitPane.getItems().remove(extPane.getNode());
        }
        if (extPane.visiblePanesProperty().get() && !splitPane.getItems().contains(extPane.getNode())) {
            splitPane.getItems().add(extPane.getNode());
        }
    }

    @FXML
    private SplitPane bottomSplitPane;

    @FXML
    private SplitPane mainSplitPane;

    @FXML
    private TreeView<DoorsFolder> databaseTreeView;

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
    private Menu editSnapshotListMenu;

    @FXML
    private Menu deleteSnapshotListMenu;

    private final ExtensionPane<DatabasePaneExtension> sideExtensionPane = new ExtensionPane<>(
            () -> super.getExtensions(DatabasePaneExtension.class), e -> e.getSidePanes(), (e, n) -> e.getPaneName(n),
            DatabasePanePreferences.SIDE_EXTENSION.retrieve(), DatabasePanePreferences.SIDE_EXTENSION::store);
    private final ExtensionPane<DatabasePaneExtension> bottomExtensionPane = new ExtensionPane<>(
            () -> super.getExtensions(DatabasePaneExtension.class), e -> e.getBottomPanes(), (e, n) -> e.getPaneName(n),
            DatabasePanePreferences.BOTTOM_EXTENSION.retrieve(), DatabasePanePreferences.BOTTOM_EXTENSION::store);

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
        if (ev.getCode() != KeyCode.ENTER || value == null || value.isEmpty() || value.trim().isEmpty()) {
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
            TreeMap<String, TreeSet<String>> snapshotLists = DatabasePanePreferences.SNAPSHOT_LISTS.retrieve();
            snapshotLists.putIfAbsent(ln, new TreeSet<>());
            DatabasePanePreferences.SNAPSHOT_LISTS.store(snapshotLists);
        });
    }

    public void deleteSnapshotListClicked(String snapshotList) {
        TreeMap<String, ?> snapshotLists = DatabasePanePreferences.SNAPSHOT_LISTS.retrieve();
        snapshotLists.remove(snapshotList);
        DatabasePanePreferences.SNAPSHOT_LISTS.store(snapshotLists);
    }

    @FXML
    public void createFullSnapshotClicked() {
        this.createSnapshot(x -> true);
    }

    private void createSnapshotFromListClicked(String snapshotList) {
        TreeSet<String> sl = ((TreeMap<String, TreeSet<String>>) DatabasePanePreferences.SNAPSHOT_LISTS.retrieve()).get(snapshotList);
        this.createSnapshot(node -> isInSnapshotList(sl, node));
    }

    private void createSnapshot(Predicate<DoorsTreeNode> include) {
        DatabasePath destinationPath = null;
        try {
            destinationPath = super.createSnapshot(include, destinationPath);
        } catch (Throwable ex) {
            ex.printStackTrace();
            while (ex.getCause() != null) {
                ex = ex.getCause();
            }

            this.setStatus("Snapshot failed; " + ApplicationPaneController.getMessage(ex));
        }

        if (destinationPath != null) {
            this.open(destinationPath, OpenFlag.OPEN_ONLY);
        }
    }

    private void editSnapshotListClicked(String snapshotList) {
        TreeMap<String, TreeSet<String>> snapshotLists = DatabasePanePreferences.SNAPSHOT_LISTS.retrieve();

        SnapshotListEditorController editor = new SnapshotListEditorController<>(snapshotLists.get(snapshotList).stream().collect(Collectors.joining("\n")));
        if (editor.asDialog(this.databaseTreeView.getScene().getWindow(), "Snapshot list " + snapshotList, ButtonType.CANCEL, ButtonType.OK).orElse(ButtonType.CANCEL) == ButtonType.CANCEL) {
            return;
        }

        snapshotLists.put(snapshotList, new TreeSet<>(Arrays.asList(editor.getText().split("\n"))));
        DatabasePanePreferences.SNAPSHOT_LISTS.store(snapshotLists);
        this.updateGui(DatabasePaneController.UpdateModulesView);
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

    @Override
    public void removePlugin(PluginWrapper plugin) {
        super.removePlugin(plugin);

        sideExtensionPane.removePlugin(plugin);
        bottomExtensionPane.removePlugin(plugin);
    }

    @Override
    public void addPlugin(PluginWrapper plugin) {
        super.addPlugin(plugin);

        sideExtensionPane.addPlugin(plugin);
        bottomExtensionPane.addPlugin(plugin);
    }

    @Override
    public SelectionModel<DoorsFolder> getCurrentFolderSelectionModel() {
        return new ForwardingMultipleSelectionModel<>(databaseTreeView.getSelectionModel(), x -> treeNodeCache.get(x), y -> y.getValue());
    }

    @Override
    public SelectionModel<DoorsModule> getCurrentModuleSelectionModel() {
        return new ForwardingMultipleSelectionModel<>(modulesTableView.getSelectionModel(), x -> x, y -> y);
    }

    @Override
    public SelectionModel<DoorsObject> getCurrentObjectSelectionModel() {
        return new EmptySelectionModel<>();
    }

    @FXML
    public void showSidePaneClicked() {
        sideExtensionPane.selectFirst();
    }

    @FXML
    public void showBottomPaneClicked() {
        bottomExtensionPane.selectFirst();
    }

    private class DoorsTreeItem extends TreeItem<DoorsFolder> implements Comparable<DoorsTreeItem> {

        private boolean childrenLoaded = false;

        public DoorsTreeItem(final DoorsFolder value) {
            super(value, getImage(value).toImageView());

            treeNodeCache.put(value, this);
            knownTags.addAll(value.getTags());
        }

        @Override
        public ObservableList<TreeItem<DoorsFolder>> getChildren() {
            if (!childrenLoaded) {
                updateChildren();

            }
            return super.getChildren();
        }

        private void updateChildren() {
            final ObservableList<DoorsTreeItem> list = FXCollections.observableArrayList();
            getValue().getChildren().stream()
                    .filter(n -> n instanceof DoorsFolder)
                    .map(n -> new DoorsTreeItem((DoorsFolder) n))
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
            return Objects.compare(this.getValue().getName(), o2.getValue().getName(), Comparator.naturalOrder());
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
