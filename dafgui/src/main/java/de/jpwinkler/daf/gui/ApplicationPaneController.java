package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.ApplicationPart.ApplicationPartRegistry;
import de.jpwinkler.daf.gui.controls.ProgressMenuItemController;
import de.jpwinkler.daf.gui.controls.ProgressMenuItemController.ProgressMenuItem;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.tuple.Pair;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ManifestPluginDescriptorFinder;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;

public final class ApplicationPaneController extends AutoloadingPaneController<ApplicationPaneController> implements ApplicationPaneInterface {

    private static final int MAX_STATUS_MENU_ITEMS = 20;
    private static final int MAX_RECENT_FILES = 10;

    private final Map<DatabasePath, ApplicationPartController> applicationPartControllers = new HashMap<>();
    private final BackgroundTaskExecutorImpl backgroundTaskExecutor = new BackgroundTaskExecutorImpl(
            (a, b) -> Platform.runLater(() -> this.onBackgroundTaskUpdate(a, b)));

    private final ApplicationPartRegistry applicationPartRegistry = new ApplicationPartRegistry(
            () -> this.pluginManager.getPlugins(PluginState.STARTED).stream().distinct(),
            (databaseInterface, dirty) -> this.tabPane.getTabs().stream()
                    .map(t -> Pair.of(t, this.applicationPartControllers.get((DatabasePath) t.getUserData())))
                    .filter(t -> t.getRight().getDatabaseInterface() == databaseInterface)
                    .forEach(t -> t.getLeft().setText(t.getRight().getPath().toString() + (dirty ? "*" : ""))));
    private final List<ApplicationPaneExtension> extensions = new ArrayList<>();
    private final Map<Menu, PluginWrapper> extensionMenus = new HashMap<>();

    private final PluginManager pluginManager = new DefaultPluginManager() {
        @Override
        protected PluginDescriptorFinder createPluginDescriptorFinder() {
            return new ManifestPluginDescriptorFinder();
        }
    };

    @FXML
    private TabPane tabPane;

    @FXML
    private Label statusBarLabel;

    @FXML
    private MenuButton statusMenuButton;

    @FXML
    private MenuButton backgroundTaskMenuButton;

    @FXML
    private ProgressBar backgroundTaskStatusProgressBar;

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private Menu newMenu;

    @FXML
    private Menu openMenu;

    @FXML
    private Menu recentMenu;

    @FXML
    private Menu pluginStateMenu;

    @FXML
    private Menu uninstallPluginMenu;

    private final Timer generalTimer = new Timer(true);

    private final TreeMap<Long, DatabasePath> recentFiles = ApplicationPreferences.RECENT_FILES.retrieve();
    private final ListChangeListener<Menu> partMenuChangeLister = (change) -> {
        while (change.next()) {
            change.getRemoved().forEach(this.mainMenuBar.getMenus()::remove);
            this.tabChangeListener.changed(tabPane.getSelectionModel().selectedItemProperty(), tabPane.getSelectionModel().getSelectedItem(), tabPane.getSelectionModel().getSelectedItem());
        }
    };
    private final ChangeListener<Tab> tabChangeListener = (observable, oldValue, newValue) -> {

        if (oldValue != null) {
            ObservableList<Menu> partMenus = getApplicationPartController(oldValue).getMenus();
            partMenus.forEach(mainMenuBar.getMenus()::remove);
            partMenus.removeListener(partMenuChangeLister);
        }

        if (newValue != null) {
            ObservableList<Menu> partMenus = getApplicationPartController(newValue).getMenus();
            partMenus.forEach(mainMenuBar.getMenus()::add);
            partMenus.addListener(partMenuChangeLister);
        }
    };

    public ApplicationPaneController() {
        ApplicationPart.registerDefault(applicationPartRegistry);
        tabPane.getSelectionModel().selectedItemProperty().addListener(tabChangeListener);

        tabPane.getTabs().addListener((ListChangeListener<Tab>) (change) -> {
            Set<Tab> closedTabs = new HashSet<>();

            while (change.next()) {
                for (Tab selectedTab : change.getRemoved()) {
                    closedTabs.add(selectedTab);
                }
            }

            closeTabs(closedTabs);
        });
        generateRecentMenu();

        applicationPartRegistry.addListener((added, removed) -> {
            if (removed != null) {
                openMenu.getItems().removeIf(p -> p.getUserData() == removed);
                newMenu.getItems().removeIf(p -> p.getUserData() == removed);

                String openPaths = this.applicationPartControllers.values().stream()
                        .filter(c -> c.getApplicationPart() == removed)
                        .map(c -> c.getPath().toString())
                        .collect(Collectors.joining(", "));
                if (!openPaths.trim().isEmpty()) {
                    throw new RuntimeException("Open tabs remaining: " + openPaths);
                }
            }

            if (added != null) {
                MenuItem it = new MenuItem(added.getName());
                it.setUserData(added);
                it.setOnAction(ev -> {
                    added.openWithSelector(tabPane.getScene().getWindow()).forEach(
                            path -> open(path, OpenFlag.OPEN_ONLY));
                });

                openMenu.getItems().add(it);
                if (added.isAllowNew()) {
                    it = new MenuItem(added.getName());
                    it.setUserData(added);
                    it.setOnAction(ev -> {
                        added.saveWithSelector(tabPane.getScene().getWindow()).forEach(
                                path -> open(path, OpenFlag.ERASE_IF_EXISTS));
                    });
                    newMenu.getItems().add(it);
                }
            }
        });

        pluginManager.loadPlugins();
        String failedPlugins = Stream.concat(
                pluginManager.getPlugins(PluginState.CREATED).stream(),
                pluginManager.getPlugins(PluginState.RESOLVED).stream())
                .filter(pl -> {
                    try {
                        this.startPlugin(pl.getPluginId());
                        return false;
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                        return true;
                    }
                })
                .map(pl -> pl.getPluginId())
                .collect(Collectors.joining(", "));
        if (!failedPlugins.trim().isEmpty()) {
            setStatus("Failed loading plugins: " + failedPlugins);
        }

        pluginManager.getPlugins().forEach(this::addPluginMenuEntries);
    }

    private void addPluginMenuEntries(PluginWrapper plugin) {
        MenuItem mi = new MenuItem(plugin.getPluginId());
        mi.setUserData(plugin.getPluginId());
        mi.setOnAction(ev -> {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to uninstall the plugin " + plugin.getPluginId()
                    + "? You may loose its current state.", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Uninstall plugin " + plugin.getPluginId());
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            if (alert.showAndWait().orElse(ButtonType.NO) != ButtonType.YES) {
                return;
            }

            this.uninstallPlugin((String) ((MenuItem) ev.getTarget()).getUserData());
            setStatus("Plugin uninstalled: " + plugin.getPluginId());
        });
        uninstallPluginMenu.getItems().add(mi);
        uninstallPluginMenu.getItems().sort((mi1, mi2) -> mi1.getText().compareTo(mi2.getText()));

        CheckBox cb = new CheckBox(plugin.getPluginId());
        cb.setStyle("-fx-text-fill: -fx-text-base-color");
        cb.setUserData(plugin.getPluginId());

        CustomMenuItem cmi = new CustomMenuItem(cb);
        cmi.setHideOnClick(false);
        cmi.setUserData(plugin.getPluginId());
        cb.selectedProperty().set(plugin.getPluginState() == PluginState.STARTED);
        MutableBoolean revertedFlag = new MutableBoolean(false);
        cb.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (revertedFlag.isTrue()) {
                revertedFlag.setFalse();
                return;
            }

            try {
                if (newVal) {
                    startPlugin(plugin.getPluginId());
                    setStatus("Plugin started: " + plugin.getPluginId());
                } else {
                    stopPlugin(plugin.getPluginId());
                    setStatus("Plugin stopped: " + plugin.getPluginId());
                }
            } catch (Throwable ex) {
                ex.printStackTrace();
                setStatus("Failed changing plugin state: " + getMessage(ex));
                revertedFlag.setTrue();
                cb.setSelected(oldVal);
            }
        });
        pluginStateMenu.getItems().add(cmi);
        pluginStateMenu.getItems().sort((mi1, mi2) -> mi1.getText().compareTo(mi2.getText()));
    }

    private void onBackgroundTaskUpdate(BackgroundTask t, Double totalProgress) {
        backgroundTaskStatusProgressBar.setProgress(totalProgress.isNaN() || totalProgress.isInfinite() ? -1 : totalProgress);

        backgroundTaskMenuButton.getItems().stream()
                .map(mi -> (ProgressMenuItem) mi)
                .filter(mi -> mi.getBackgroundTask() == t)
                .findFirst().orElseGet(() -> {

                    ProgressMenuItem mi = new ProgressMenuItemController(t).asMenuItem();
                    backgroundTaskMenuButton.getItems().add(mi);
                    return mi;
                }).update();

        backgroundTaskMenuButton.getItems().stream()
                .map(mi -> (ProgressMenuItem) mi)
                .filter(mi -> mi.getBackgroundTask().getTaskStatus() != BackgroundTask.TaskStatus.RUNNING)
                .limit(Math.max(0, backgroundTaskMenuButton.getItems().size() - MAX_STATUS_MENU_ITEMS))
                .forEach(backgroundTaskMenuButton.getItems()::remove);
    }

    @Override
    public BackgroundTaskExecutor getBackgroundTaskExecutor() {
        return backgroundTaskExecutor;
    }

    private ApplicationPartController<?> getCurrentFileStateController() {
        return getApplicationPartController((DatabasePath) tabPane.getSelectionModel().getSelectedItem().getUserData());
    }

    private ApplicationPartController<?> getApplicationPartController(Tab selectedTab) {
        return getApplicationPartController((DatabasePath) selectedTab.getUserData());
    }

    private ApplicationPartController<?> getApplicationPartController(DatabasePath path) {
        return applicationPartControllers.get((DatabasePath) path);
    }

    public void setStatus(final String status) {
        this.setStatus(status, 15000);
    }

    public void setStatus(final String status, int timeout) {
        if (statusMenuButton.getItems().isEmpty() || !status.equals(statusMenuButton.getItems().get(statusMenuButton.getItems().size() - 1).getText())) {
            MenuItem statusMenuItem = new MenuItem(status);
            statusMenuButton.getItems().add(statusMenuItem);
            if (statusMenuButton.getItems().size() > MAX_STATUS_MENU_ITEMS) {
                statusMenuButton.getItems().remove(0);
            }
        }

        statusBarLabel.setText(status);
        generalTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> statusBarLabel.setText(""));
            }

        }, 15000);
    }

    @Override
    public ApplicationPartController open(DatabasePath path, OpenFlag openFlag) {
        if (tabPane.getTabs().stream()
                .filter(t -> path.equals((DatabasePath) t.getUserData()))
                .findAny()
                .map(t -> {
                    addToRecentMenu(path);
                    tabPane.getSelectionModel().select(t);
                    return t;
                }).isPresent()) {

            if (openFlag == OpenFlag.ERASE_IF_EXISTS) {
                setStatus("Database " + path.toString() + " could not be created: already open");
            }

            addToRecentMenu(path);
            return getApplicationPartController(tabPane.getSelectionModel().getSelectedItem());
        }

        try {
            final ApplicationPartController controller = applicationPartRegistry.createController(this, path, openFlag);
            final Parent modulePane = controller.getNode();

            final Tab selectedTab = new Tab(path.toString(), modulePane);
            selectedTab.setUserData(path);
            applicationPartControllers.put(path, controller);

            selectedTab.setClosable(true);
            tabPane.getTabs().add(selectedTab);

            tabPane.getSelectionModel().select(selectedTab);
            addToRecentMenu(path);
            return controller;
        } catch (Throwable ex) {
            ex.printStackTrace();
            while (ex.getCause() != null) {
                ex = ex.getCause();
            }

            setStatus("Open: Failed to open file; " + getMessage(ex));
            return null;
        }
    }

    public static String getMessage(Throwable t) {
        if (t.getMessage() == null && t.getCause() != null) {
            return getMessage(t.getCause());
        } else if (t.getMessage() == null) {
            return t.toString();
        } else {
            return t.getMessage();
        }
    }

    private void addToRecentMenu(DatabasePath path) {
        if (path != null) {
            recentFiles.values().remove(path);
            recentFiles.put(new Date().getTime(), path);
        }
        generateRecentMenu();
    }

    private void generateRecentMenu() {
        while (recentFiles.size() > MAX_RECENT_FILES) {
            recentFiles.remove(recentFiles.firstKey());
        }
        ApplicationPreferences.RECENT_FILES.store(recentFiles);

        recentMenu.getItems().clear();

        if (recentFiles.isEmpty()) {
            MenuItem recentMenuItem = new MenuItem("No valid files available");
            recentMenuItem.setDisable(true);
            recentMenu.getItems().add(recentMenuItem);
            return;
        }

        for (DatabasePath recentFile : recentFiles.descendingMap().values()) {
            MenuItem recentMenuItem = new MenuItem(recentFile.toString());
            recentMenuItem.setUserData(recentFile);
            recentMenu.getItems().add(recentMenuItem);
            recentMenuItem.setOnAction(ev -> {
                DatabasePath uri = (DatabasePath) ((MenuItem) ev.getTarget()).getUserData();
                this.open(uri, OpenFlag.OPEN_ONLY);
            });
        }
    }

    @FXML
    public void saveClicked() {
        save(getCurrentFileStateController().getPath());
    }

    private boolean save(DatabasePath path) {
        try {
            applicationPartControllers.get(path).getDatabaseInterface().flush();
            applicationPartControllers.get(path).getCommandStack().setSavePoint();
        } catch (Throwable ex) {
            ex.printStackTrace();
            while (ex.getCause() != null) {
                ex = ex.getCause();
            }

            this.setStatus("Save: Failed to save file; " + getMessage(ex));
            return false;
        }

        addToRecentMenu(path);
        return true;
    }

    @FXML
    public void saveAsClicked() {
        DatabasePath destinationPath = null;
        try {
            destinationPath = this.createSnapshot(getCurrentFileStateController().getDatabaseInterface(), getCurrentFileStateController().getPath(), x -> true, destinationPath);
        } catch (Throwable ex) {
            ex.printStackTrace();
            while (ex.getCause() != null) {
                ex = ex.getCause();
            }

            this.setStatus("Snapshot failed; " + getMessage(ex));
        }

        if (destinationPath != null) {
            this.open(destinationPath, OpenFlag.OPEN_ONLY);
        }
    }

    @Override
    public DatabasePath createSnapshot(DatabaseInterface sourceDB, DatabasePath sourcePath, Predicate<DoorsTreeNode> include, DatabasePath destinationPath) {
        if (destinationPath == null) {
            ChoiceDialog<ApplicationPart<?>> applicationPartChooser = new ChoiceDialog<>(null, applicationPartRegistry.registry().filter(p -> p.isAllowNew()).collect(Collectors.toList()));
            applicationPartChooser.setTitle("Create snapshot");
            applicationPartChooser.setHeaderText("Select a destination database type");
            destinationPath = Main.asStream(applicationPartChooser.showAndWait())
                    .flatMap(part -> part.saveWithSelector(tabPane.getScene().getWindow()))
                    .findAny().orElse(null);

            if (destinationPath == null) {
                return null;
            }
        }

        try {
            DatabaseInterface destinationDB = applicationPartRegistry.openDatabase(destinationPath, OpenFlag.ERASE_IF_EXISTS).getLeft();
            destinationDB.getFactory().copy(sourceDB.getNode(sourcePath.getPath()), destinationDB.getDatabaseRoot(), include);
            DoorsAttributes.DATABASE_COPIED_FROM.setValue(String.class, destinationDB.getDatabaseRoot(), sourceDB.getPath().toString());
            DoorsAttributes.DATABASE_COPIED_AT.setValue(String.class, destinationDB.getDatabaseRoot(), ZonedDateTime.now(ZoneOffset.UTC).toString());

            destinationDB.flush();
            return destinationPath;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            applicationPartRegistry.closeDatabase(destinationPath);
        }
    }

    @FXML
    public void closeClicked() {
        if (tabPane.getSelectionModel().isEmpty()) {
            return;
        }

        tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
    }

    private boolean backgroundMonitorPreventsClose = false;

    public void closeRequest() {
        if (backgroundTaskExecutor.hasRunningTasks()) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "There are running background tasks. Are you sure you want to close the application? You might lose their state.", ButtonType.CANCEL, ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.CANCEL) {
                backgroundMonitorPreventsClose = true;
                Platform.runLater(() -> backgroundMonitorPreventsClose = false);
                return;
            }
            backgroundMonitorPreventsClose = false;
        }

        tabPane.getTabs().clear();
    }

    public boolean canClose() {
        return applicationPartControllers.isEmpty() && !backgroundMonitorPreventsClose;
    }

    @FXML
    public void exitClicked() {
        Platform.exit();
    }

    private ButtonType closeTabs(Collection<Tab> closedTabs) {
        ButtonType cancelled = ButtonType.YES;
        for (Tab t : closedTabs) {
            if (cancelled == ButtonType.CANCEL || closeTab((DatabasePath) t.getUserData()) == ButtonType.CANCEL) {
                cancelled = ButtonType.CANCEL;
                Platform.runLater(() -> tabPane.getTabs().add(t));
            }
        }
        return cancelled;
    }

    private ButtonType closeTab(DatabasePath path) {
        // non-dirty files can be closed without worries
        if (!getApplicationPartController(path).getCommandStack().isDirty()) {
            applicationPartControllers.remove(path);
            applicationPartRegistry.closeDatabase(path);
            return ButtonType.YES;
        }

        // close if saving is not desired or we saved successfully
        Alert alert = new Alert(AlertType.CONFIRMATION, "There are unsaved changes in " + path.toString() + ", do you want to save them?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        ButtonType response = alert.showAndWait().orElse(ButtonType.NO);
        if (response == ButtonType.NO) { // no
            applicationPartControllers.remove(path);
            applicationPartRegistry.closeDatabase(path);
            return ButtonType.YES;
        } else if (response == ButtonType.YES && this.save(path)) { // yes and save success
            applicationPartControllers.remove(path);
            applicationPartRegistry.closeDatabase(path);
            return ButtonType.YES;
        } else if (response == ButtonType.YES) { // yes but save failed
            return ButtonType.CANCEL;
        } else if (response == ButtonType.CANCEL) { // cancel clicked
            return ButtonType.CANCEL;
        } else {
            throw new AssertionError();
        }
    }

    @FXML
    public void installPluginClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load a plugin");
        fileChooser.setInitialDirectory(ApplicationPreferences.PLUGIN_DIRECTORY.retrieve());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DAF plugin", "*.jar"));

        Stream.of(fileChooser.showOpenDialog(tabPane.getScene().getWindow()))
                .filter(f -> f != null)
                .peek(f -> ApplicationPreferences.PLUGIN_DIRECTORY.store(f.getParentFile().getAbsoluteFile()))
                .forEach(f -> {
                    try (URLClassLoader urlCl = new URLClassLoader(new URL[]{f.toURI().toURL()}, null)) {
                        Manifest mf = new Manifest(urlCl.getResourceAsStream("META-INF/MANIFEST.MF"));
                        String pluginId = mf.getMainAttributes().getValue("Plugin-Id");
                        if (pluginId == null) {
                            throw new IllegalArgumentException("No plugin id in manifest");
                        }

                        if (pluginManager.getPlugin(pluginId) != null) {
                            Alert alert = new Alert(AlertType.CONFIRMATION, "The plugin " + pluginId + " "
                                    + "has already been loaded. Do you want to replace it? You may loose its current state.", ButtonType.YES, ButtonType.NO);
                            alert.setTitle("Replace plugin " + pluginId);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

                            if (alert.showAndWait().orElse(ButtonType.NO) != ButtonType.YES) {
                                return;
                            }

                            this.uninstallPlugin(pluginId);
                        }

                        Path destination = pluginManager.getPluginsRoot().toAbsolutePath().resolve(pluginId + ".jar");
                        if (!destination.equals(f.toPath().toAbsolutePath())) {
                            Files.copy(f.toPath(), destination);
                        }

                        pluginManager.loadPlugin(destination);
                        PluginWrapper plugin = pluginManager.getPlugin(pluginId);
                        if (plugin == null) {
                            throw new RuntimeException("Invalid plugin file");
                        }

                        startPlugin(pluginId);
                        addPluginMenuEntries(plugin);
                        setStatus("Plugin installed: " + pluginId);
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                        setStatus("Failed loading plugin: " + getMessage(ex));
                    }
                });
    }

    private void startPlugin(String pluginId) {
        PluginState initialState = pluginManager.getPlugin(pluginId).getPluginState();
        if (initialState == PluginState.CREATED) {
            throw new RuntimeException("Plugin could not be resolved");
        }
        if (initialState == PluginState.STARTED) {
            throw new RuntimeException("Plugin already started");
        }

        pluginManager.enablePlugin(pluginId);
        pluginManager.startPlugin(pluginId);

        PluginWrapper plugin = pluginManager.getPlugin(pluginId);
        if (plugin.getPluginState() != PluginState.STARTED) {
            throw new RuntimeException("Plugin could not be started");
        }

        List<ApplicationPaneExtension> newExts = plugin.getPluginManager().getExtensions(ApplicationPaneExtension.class, plugin.getPluginId());
        this.extensions.addAll(newExts);
        newExts.stream()
                .flatMap(e -> e.getApplicationMenus().stream())
                .peek(m -> extensionMenus.put(m, plugin))
                .forEach(mainMenuBar.getMenus()::add);
        newExts.stream()
                .flatMap(e -> e.getApplicationParts().stream())
                .forEach(applicationPartRegistry::register);
        applicationPartControllers.values().stream().forEach(a -> a.addPlugin(plugin));
    }

    private void stopPlugin(String pluginId) {
        PluginWrapper plugin = pluginManager.getPlugin(pluginId);
        this.extensions.stream()
                .filter(ext -> ext.getClass().getClassLoader() == plugin.getPluginClassLoader())
                .flatMap(e -> e.getApplicationParts().stream())
                .forEach(applicationPartRegistry::unregister);

        applicationPartControllers.values().forEach(a -> a.removePlugin(plugin));

        List<Menu> extMenus = extensionMenus.entrySet().stream().filter(e -> e.getValue() == plugin).map(e -> e.getKey()).collect(Collectors.toList());
        this.mainMenuBar.getMenus().removeAll(extMenus);
        this.extensions.removeIf(ext -> ext.getClass().getClassLoader() == plugin.getPluginClassLoader());

        pluginManager.stopPlugin(pluginId);
        pluginManager.disablePlugin(pluginId);
    }

    private void uninstallPlugin(String pluginId) {
        stopPlugin(pluginId);

        uninstallPluginMenu.getItems().removeIf(mi -> Objects.equals(mi.getUserData(), pluginId));
        pluginStateMenu.getItems().removeIf(mi -> Objects.equals(mi.getUserData(), pluginId));

        pluginManager.deletePlugin(pluginId);
    }
}
