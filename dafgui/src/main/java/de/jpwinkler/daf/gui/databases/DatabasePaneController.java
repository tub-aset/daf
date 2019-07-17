package de.jpwinkler.daf.gui.databases;

/*-
 * #%L
 * dafgui
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.gui.ApplicationPaneController;
import de.jpwinkler.daf.gui.ApplicationPartController;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPart;
import de.jpwinkler.daf.gui.BackgroundTask;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.controls.CustomTextAreaTableCell;
import de.jpwinkler.daf.gui.controls.CustomTextFieldTableCell;
import de.jpwinkler.daf.gui.controls.CustomTextFieldTreeCell;
import de.jpwinkler.daf.gui.controls.DoorsTreeItem;
import de.jpwinkler.daf.gui.controls.EmptySelectionModel;
import de.jpwinkler.daf.gui.controls.ExtensionPane;
import de.jpwinkler.daf.gui.controls.ForwardingMultipleSelectionModel;
import de.jpwinkler.daf.gui.controls.MultiLineTextInputDialog;
import de.jpwinkler.daf.gui.databases.commands.AddTagCommand;
import de.jpwinkler.daf.gui.databases.commands.DeleteAttributesCommand;
import de.jpwinkler.daf.gui.databases.commands.DeleteCommand;
import de.jpwinkler.daf.gui.databases.commands.EditAttributesCommand;
import de.jpwinkler.daf.gui.databases.commands.NewFolderCommand;
import de.jpwinkler.daf.gui.databases.commands.NewModuleCommand;
import de.jpwinkler.daf.gui.databases.commands.NewProjectCommand;
import de.jpwinkler.daf.gui.databases.commands.PasteCommand;
import de.jpwinkler.daf.gui.databases.commands.RemoveTagCommand;
import de.jpwinkler.daf.gui.databases.commands.RenameAttributesCommand;
import de.jpwinkler.daf.gui.databases.commands.RenameNodeCommand;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.pf4j.PluginWrapper;

public final class DatabasePaneController extends ApplicationPartController<DatabasePaneController> {

    public DatabasePaneController(ApplicationPaneController applicationController, ApplicationPart applicationPart) {
        super(applicationController, applicationPart, DatabasePaneExtension.class);

        if (super.getDatabaseInterface().isReadOnly()) {
            databaseTreeView.setEditable(false);
            moduleNameColumn.setEditable(false);
            moduleDescriptionColumn.setEditable(false);
            attributesTableView.setEditable(false);
            newTagComboBox.setDisable(true);
        }

        databaseTreeView.setCellFactory(tv -> new CustomTextFieldTreeCell<>(
                it -> {
                    knownTags.addAll(it.getTags());
                    return it.getName();
                },
                (it, newName) -> executeCommand(new RenameNodeCommand(it, newName)),
                it -> {
                }));

        databaseTreeView.setRoot(new DoorsTreeItem(super.getBackgroundTaskExecutor(), (DoorsTreeNode) super.getDatabaseInterface().getDatabaseRoot(), node -> node instanceof DoorsFolder, treeNodeCache));
        databaseTreeView.getRoot().setExpanded(true);
        databaseTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateGui(UpdateModulesView, UpdateTagsView, UpdateAttributesView, UpdateNodeTitle);
        });
        databaseTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setupDividerStorage(mainSplitPane, DatabasePanePreferences.SPLITPOS, sideExtensionPane);
        setupDividerStorage(bottomSplitPane, DatabasePanePreferences.BOTTOM_SPLITPOS, bottomExtensionPane);
        setupDividerStorage(attributesModulesSplitPane, DatabasePanePreferences.ATTRIBUTES_MODULES_SPLITPOS, null);

        modulesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateGui(UpdateTagsView, UpdateAttributesView, UpdateNodeTitle);
        });

        moduleNameColumn.setCellFactory(tc -> new CustomTextFieldTableCell<>(tc,
                it -> it.getName(),
                (it, newName) -> this.executeCommand(new RenameNodeCommand(it, newName)),
                it -> this.open(this.getPath().withPath(it.getFullName()), OpenFlag.OPEN_ONLY)));
        moduleDescriptionColumn.setCellFactory(tc -> new CustomTextAreaTableCell<>(tc,
                it -> {
                    CompletableFuture<Map<String, String>> future = it.getAttributesAsync(super.getBackgroundTaskExecutor().withPriority(BackgroundTask.PRIORITY_ATTRIBUTES));
                    if (future.isDone()) {
                        return DoorsAttributes.MODULE_DESCRIPTION.getValue(String.class, it);
                    } else {
                        future.thenRun(() -> Platform.runLater(() -> this.modulesTableView.refresh()));
                    }

                    return "Loading...";
                },
                (it, newValue) -> {
                    this.executeCommand(new EditAttributesCommand(DoorsAttributes.MODULE_DESCRIPTION.getKey(), newValue, it));
                },
                it -> this.open(this.getPath().withPath(it.getFullName()), OpenFlag.OPEN_ONLY)));
        snapshotListsColumn.setCellFactory(tc -> new CustomTextFieldTableCell<>(tc,
                it -> it == null ? "" : getSnapshotLists(it),
                (it, newLists) -> {
                    TreeMap<String, TreeSet<String>> data = DatabasePanePreferences.SNAPSHOT_LISTS.retrieve();
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

        attributeValueColumn.setCellFactory(tc -> new CustomTextAreaTableCell<>(tc,
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

        DatabasePanePreferences.SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), createSnapshotsMenu.getItems(), this::createSnapshotFromListClicked));
        DatabasePanePreferences.SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), deleteSnapshotListMenu.getItems(), this::deleteSnapshotListClicked));
        DatabasePanePreferences.SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), editSnapshotListMenu.getItems(), this::editSnapshotListClicked));
        DatabasePanePreferences.SNAPSHOT_LISTS.addOnChangedHandler(t -> this.populateSnapshotMenu(((Map<String, ?>) t).keySet(), addToSnapshotListMenuButton.getItems(), this::addToSnapshotListClicked));

        Map<String, ?> snapshotLists = DatabasePanePreferences.SNAPSHOT_LISTS.retrieve();
        populateSnapshotMenu(snapshotLists.keySet(), createSnapshotsMenu.getItems(), this::createSnapshotFromListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), deleteSnapshotListMenu.getItems(), this::deleteSnapshotListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), editSnapshotListMenu.getItems(), this::editSnapshotListClicked);
        populateSnapshotMenu(snapshotLists.keySet(), addToSnapshotListMenuButton.getItems(), this::addToSnapshotListClicked);
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
        if (node == null) {
            return false;
        } else if (node instanceof DoorsObject) {
            return true;
        }

        String name = node.getFullName();
        String floor = list.floor(name);
        String ceil = list.ceiling(name);
        if (floor == null && ceil == null) {
            return false;
        } else if (Objects.equals(floor, ceil)) {
            return true;
        }

        // match children
        // add slash to make sure only children are matched, not same level nodes with similar name
        if (floor != null && name.startsWith(floor.charAt(floor.length() - 1) != '/' ? floor + "/" : floor)) {
            return true;
        }

        // match parents if there is a matching child and we're a parent
        if (ceil != null && ceil.startsWith(name.charAt(name.length() - 1) != '/' ? name + "/" : name)) {
            DoorsTreeNode root = node;
            while (root.getParent() != null) {
                root = root.getParent();
            }

            if (root.getChild(ceil) != null) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private SplitPane bottomSplitPane;

    @FXML
    private SplitPane mainSplitPane;

    @FXML
    private SplitPane attributesModulesSplitPane;

    @FXML
    private TreeView<DoorsTreeNode> databaseTreeView;

    @FXML
    private Label currentNodeLabel;

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

    @FXML
    private MenuButton addToSnapshotListMenuButton;

    private final ExtensionPane<DatabasePaneExtension> sideExtensionPane = new ExtensionPane<>(
            () -> super.getExtensions(DatabasePaneExtension.class), e -> e.getSidePanes(), (e, n) -> e.getPaneName(n),
            DatabasePanePreferences.SIDE_EXTENSION.retrieve(), DatabasePanePreferences.SIDE_EXTENSION::store);
    private final ExtensionPane<DatabasePaneExtension> bottomExtensionPane = new ExtensionPane<>(
            () -> super.getExtensions(DatabasePaneExtension.class), e -> e.getBottomPanes(), (e, n) -> e.getPaneName(n),
            DatabasePanePreferences.BOTTOM_EXTENSION.retrieve(), DatabasePanePreferences.BOTTOM_EXTENSION::store);

    private List<DoorsTreeNode> nodeClipboard;
    private List<Entry<String, String>> attributeClipboard;
    private List<String> tagsClipboard;

    private final WeakHashMap<DoorsTreeNode, DoorsTreeItem> treeNodeCache = new WeakHashMap<>();
    private final HashSet<String> knownTags = new HashSet<>();

    @FXML
    public void newFolderClicked() {
        databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .forEach(it -> executeCommand(new NewFolderCommand(super.getDatabaseInterface().getFactory(), it.getValue())));
    }
    
    @FXML
    public void newProjectClicked() {
        databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .forEach(it -> executeCommand(new NewProjectCommand(super.getDatabaseInterface().getFactory(), it.getValue())));
    }

    @FXML
    public void newModuleClicked() {
        databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .forEach(it -> executeCommand(new NewModuleCommand(super.getDatabaseInterface().getFactory(), it.getValue())));
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
                ? databaseTreeView.getSelectionModel().getSelectedItems().stream()
                        .filter(it -> it != null)
                        .map(it -> it.getValue())
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
                    .forEach(it -> executeCommand(new PasteCommand(super.getDatabaseInterface().getFactory(), it.getValue(), nodeClipboard)));
        }
        if (modulesTableView.isFocused()) {
            modulesTableView.getSelectionModel().getSelectedItems().stream()
                    .limit(1)
                    .forEach(it -> executeCommand(new PasteCommand(super.getDatabaseInterface().getFactory(), it.getParent(), nodeClipboard)));
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
        super.createSnapshot(include, null).thenAccept(destinationPath -> {
            if (destinationPath != null) {
                Platform.runLater(() -> {
                    this.open(destinationPath, OpenFlag.OPEN_ONLY);
                });
            }
        });

    }

    private void editSnapshotListClicked(String snapshotList) {
        TreeMap<String, TreeSet<String>> snapshotLists = DatabasePanePreferences.SNAPSHOT_LISTS.retrieve();

        MultiLineTextInputDialog editor = new MultiLineTextInputDialog<>(snapshotLists.get(snapshotList).stream().collect(Collectors.joining("\n")));
        if (editor.showDialog(this.databaseTreeView.getScene().getWindow(), "Snapshot list " + snapshotList, ButtonType.CANCEL, ButtonType.OK).orElse(ButtonType.CANCEL) == ButtonType.CANCEL) {
            return;
        }

        snapshotLists.put(snapshotList, new TreeSet<>(Arrays.asList(editor.getText().split("\n"))));
        DatabasePanePreferences.SNAPSHOT_LISTS.store(snapshotLists);
        this.updateGui(DatabasePaneController.UpdateModulesView);
    }

    private void addToSnapshotListClicked(String t) {
        TreeMap<String, TreeSet<String>> snapshotLists = DatabasePanePreferences.SNAPSHOT_LISTS.retrieve();
        if (!this.getCurrentDoorsTreeNode().peek(n -> snapshotLists.get(t).add(n.getFullName())).findFirst().isPresent()) {
            setStatus("No module or folder has been selected.");
        }

        DatabasePanePreferences.SNAPSHOT_LISTS.store(snapshotLists);
        this.updateGui(DatabasePaneController.UpdateModulesView);

    }

    private void populateSnapshotMenu(Collection<String> snapshotLists, ObservableList<MenuItem> menu, Consumer<String> action) {
        menu.clear();
        snapshotLists.forEach(ln -> {
            MenuItem mi = new MenuItem(ln);
            mi.setOnAction(eh -> action.accept(ln));
            menu.add(mi);
        });
    }

    private void traverseTreeItem(final DoorsTreeItem root, final Consumer<DoorsTreeItem> f) {
        f.accept(root);
        for (final TreeItem<?> child : root.getChildren()) {
            traverseTreeItem((DoorsTreeItem) child, f);
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();

        sideExtensionPane.shutdown();
        bottomExtensionPane.shutdown();
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
        return new ForwardingMultipleSelectionModel<>(databaseTreeView.getSelectionModel(), x -> treeNodeCache.get(x), y -> (DoorsFolder) y.getValue());
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

    public static final UpdateAction<DatabasePaneController> UpdateTreeItem(DoorsTreeNode parentNode) {
        return (ctrl) -> {
            DoorsTreeItem parent = ctrl.treeNodeCache.get(parentNode);
            List<TreeItem<DoorsTreeNode>> selectedItems = new ArrayList<>(ctrl.databaseTreeView.getSelectionModel().getSelectedItems());

            final HashMap<DoorsTreeNode, Boolean> expanded = new HashMap<>();
            ctrl.traverseTreeItem(parent, i -> expanded.put(i.getValue(), i.isExpanded()));

            parent.updateChildren();

            ctrl.traverseTreeItem(parent, i -> i.setExpanded(expanded.containsKey(i.getValue()) && expanded.get(i.getValue())));
            parent.setExpanded(true);

            ctrl.databaseTreeView.refresh();
            if (!selectedItems.isEmpty()) {
                ctrl.databaseTreeView.getSelectionModel().selectIndices(ctrl.databaseTreeView.getRow(selectedItems.get(0)),
                        selectedItems.stream().skip(1).mapToInt(ctrl.databaseTreeView::getRow).toArray());
            }
        };
    }

    public static final UpdateAction<DatabasePaneController> UpdateNodeTitle = ctrl -> {
        ctrl.getCurrentDoorsTreeNode().findFirst()
                .ifPresent(n -> ctrl.currentNodeLabel.setText(n.getFullName()));
    };

    public static final UpdateAction<DatabasePaneController> UpdateModulesView = ctrl -> {
        ctrl.modulesTableView.getItems().clear();

        ctrl.databaseTreeView.getSelectionModel().getSelectedItems().stream()
                .filter(it -> it != null)
                .map(it -> it.getValue().getChildrenAsync(ctrl.getBackgroundTaskExecutor().withPriority(BackgroundTask.PRIORITY_FOLDERS)))
                .peek(ft -> ft.exceptionally(t -> {
            Platform.runLater(() -> {
                Button retryButton = new Button("Retry");
                retryButton.setOnAction(ev -> ctrl.updateGui(ctrl.UpdateModulesView));
                ctrl.modulesTableView.setPlaceholder(retryButton);

            });
            throw new RuntimeException(t);
        }).thenAccept(children -> Platform.runLater(() -> {
            ctrl.modulesTableView.setPlaceholder(null);
            ctrl.modulesTableView.getItems().clear();
            ctrl.modulesTableView.getItems().addAll(children.stream()
                    .filter(it -> it instanceof DoorsModule)
                    .map(it -> (DoorsModule) it)
                    .peek(it -> ctrl.knownTags.addAll(it.getTags()))
                    .collect(Collectors.toList()));
            ctrl.modulesTableView.sort();
        })))
                .limit(1)
                .forEach(ft -> ctrl.modulesTableView.setPlaceholder(new ProgressBar()));
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

        ctrl.getCurrentDoorsTreeNode()
                .map(it -> it.getAttributesAsync(ctrl.getBackgroundTaskExecutor().withPriority(BackgroundTask.PRIORITY_ATTRIBUTES)))
                .peek(ft -> ft.exceptionally(t -> {
            Platform.runLater(() -> {
                Button retryButton = new Button("Retry");
                retryButton.setOnAction(ev -> ctrl.updateGui(ctrl.UpdateAttributesView));
                ctrl.attributesTableView.setPlaceholder(retryButton);

            });
            throw new RuntimeException(t);
        }).thenAccept(attr -> Platform.runLater(() -> {
            ctrl.attributesTableView.setPlaceholder(null);
            ctrl.attributesTableView.getItems().clear();
            ctrl.attributesTableView.getItems().addAll(attr.entrySet());
            ctrl.attributesTableView.sort();
        })))
                .limit(1)
                .forEach(ft -> ctrl.attributesTableView.setPlaceholder(new ProgressBar()));
    };

}
