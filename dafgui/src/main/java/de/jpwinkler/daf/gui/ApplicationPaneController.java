package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.background.BackgroundTaskStatusListener;
import de.jpwinkler.daf.gui.background.BackgroundTaskStatusMonitor;
import de.jpwinkler.daf.gui.commands.CommandStack;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.tuple.Triple;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ManifestPluginDescriptorFinder;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;

public final class ApplicationPaneController extends AutoloadingPaneController<ApplicationPaneController> {

    private final Map<DatabasePath, Triple<MutableInt, DatabaseInterface, CommandStack>> databaseInterfaces = new HashMap<>();
    private final Map<DatabasePath, ApplicationPartController> applicationPartControllers = new HashMap<>();
    private final BackgroundTaskStatusMonitor backgroundTaskStatusMonitor = new BackgroundTaskStatusMonitor();

    final PluginManager pluginManager = new DefaultPluginManager() {
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
    private Label backgroundTaskStatusLabel;

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private ProgressBar backgroundTaskStatusProgressBar;

    @FXML
    private ToolBar backgroundTaskStatusToolBar;

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
    private final int MAX_RECENT_FILES = 10;

    public ApplicationPaneController() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Tab>) (observable, oldValue, newValue) -> {
            if (oldValue != null) {
                getApplicationPartController(oldValue).getMenus().forEach(m -> {
                    mainMenuBar.getMenus().remove(m);
                });
            }

            if (newValue != null) {
                mainMenuBar.getMenus().addAll(getApplicationPartController(newValue).getMenus());
            }
        });
        tabPane.getTabs().addListener((ListChangeListener<Tab>) (change) -> {
            Set<Tab> closedTabs = new HashSet<>();

            while (change.next()) {
                for (Tab selectedTab : change.getRemoved()) {
                    closedTabs.add(selectedTab);
                }
            }

            closeTabs(closedTabs);
        }
        );
        generateRecentMenu();

        backgroundTaskStatusToolBar.setManaged(false);
        backgroundTaskStatusToolBar.setVisible(false);
        backgroundTaskStatusMonitor.addListener(new BackgroundTaskStatusListener() {

            @Override
            public void onUpdateStatus(final String taskName, final int current, final int max) {
                Platform.runLater(() -> {
                    backgroundTaskStatusToolBar.setManaged(true);
                    backgroundTaskStatusToolBar.setVisible(true);
                    backgroundTaskStatusLabel.setText(taskName);
                    backgroundTaskStatusProgressBar.setProgress((double) current / (double) max);
                });
            }

            @Override
            public void onDone() {
                Platform.runLater(() -> {
                    backgroundTaskStatusToolBar.setManaged(false);
                    backgroundTaskStatusToolBar.setVisible(false);
                });
            }

        });

        ApplicationPart.registry()
                .peek(part -> {
                    MenuItem it = new MenuItem(part.getName());
                    it.setOnAction(ev -> {
                        part.openWithSelector(tabPane.getScene().getWindow()).forEach(
                                path -> open(path, OpenFlag.OPEN_ONLY));
                    });
                    openMenu.getItems().add(it);
                })
                .filter(p -> p.isAllowNew())
                .forEach(part -> {
                    MenuItem it = new MenuItem(part.getName());
                    it.setOnAction(ev -> {
                        part.saveWithSelector(tabPane.getScene().getWindow()).forEach(
                                path -> open(path, OpenFlag.ERASE_IF_EXISTS));
                    });
                    newMenu.getItems().add(it);
                });

        pluginManager.loadPlugins();
        pluginManager.startPlugins();
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
        cb.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                startPlugin(plugin.getPluginId());
            } else {
                stopPlugin(plugin.getPluginId());
            }
        });
        pluginStateMenu.getItems().add(cmi);
        pluginStateMenu.getItems().sort((mi1, mi2) -> mi1.getText().compareTo(mi2.getText()));
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
        statusBarLabel.setText(status);
        generalTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> statusBarLabel.setText(""));
            }

        }, 15000);
    }

    public void open(DatabasePath path, OpenFlag openFlag) {
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
            return;
        }

        try {
            final DatabasePath databasePath = path.withPath("");
            final DatabaseInterface databaseInterface;
            final CommandStack commandStack;
            if (databaseInterfaces.containsKey(databasePath)) {
                databaseInterfaces.get(databasePath).getLeft().increment();
                databaseInterface = databaseInterfaces.get(databasePath).getMiddle();
                commandStack = databaseInterfaces.get(databasePath).getRight();
            } else {
                databaseInterface = (DatabaseInterface) path.getDatabaseInterface().getConstructor(DatabasePath.class, OpenFlag.class).newInstance(databasePath, openFlag);
                commandStack = new CommandStack(dirty -> {
                    this.tabPane.getTabs().stream()
                            .filter(t -> this.applicationPartControllers.get((DatabasePath) t.getUserData()).getDatabaseInterface() == databaseInterface)
                            .forEach(t -> t.setText(path.toString() + (dirty ? "*" : "")));
                });
                databaseInterfaces.put(databasePath, Triple.of(new MutableInt(1), databaseInterface, commandStack));
            }

            final ApplicationPartController controller = ApplicationPart.createControllerForAny(this, path, databaseInterface, commandStack);
            final Parent modulePane = controller.getNode();

            final Tab selectedTab = new Tab(path.toString(), modulePane);
            selectedTab.setUserData(path);
            applicationPartControllers.put(path, controller);

            selectedTab.setClosable(true);
            tabPane.getTabs().add(selectedTab);

            tabPane.getSelectionModel().select(selectedTab);
            addToRecentMenu(path);
            return;
        } catch (Throwable ex) {
            ex.printStackTrace();
            while (ex.getCause() != null) {
                ex = ex.getCause();
            }

            setStatus("Open: Failed to open file; " + getMessage(ex));
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

    public DatabasePath createSnapshot(DatabaseInterface sourceDB, DatabasePath sourcePath, Predicate<DoorsTreeNode> include, DatabasePath destinationPath) {
        if (destinationPath == null) {
            ChoiceDialog<ApplicationPart<?>> applicationPartChooser = new ChoiceDialog<>(null, ApplicationPart.registry().filter(p -> p.isAllowNew()).collect(Collectors.toList()));
            destinationPath = applicationPartChooser.showAndWait().stream()
                    .flatMap(part -> part.saveWithSelector(tabPane.getScene().getWindow()))
                    .findAny().orElse(null);

            if (destinationPath == null) {
                return null;
            }
        }

        try {
            DatabaseInterface destinationDB = (DatabaseInterface) destinationPath.getDatabaseInterface().getConstructor(DatabasePath.class, OpenFlag.class).newInstance(destinationPath, OpenFlag.ERASE_IF_EXISTS);
            destinationDB.getDatabaseRoot().copyFrom(sourceDB.getNode(sourcePath.getPath()), null, include);
            DoorsAttributes.DATABASE_COPIED_FROM.setValue(String.class, destinationDB.getDatabaseRoot(), sourceDB.getPath().toString());
            DoorsAttributes.DATABASE_COPIED_AT.setValue(String.class, destinationDB.getDatabaseRoot(), ZonedDateTime.now(ZoneOffset.UTC).toString());

            destinationDB.flush();
            return destinationPath;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void closeClicked() {
        if (tabPane.getSelectionModel().isEmpty()) {
            return;
        }

        tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
    }

    public void closeAllPanes() {
        tabPane.getTabs().clear();
    }

    public boolean hasOpenPanes() {
        return !applicationPartControllers.isEmpty();
    }

    @FXML
    public void exitClicked() {
        Platform.exit();
    }

    private void closeTabs(Collection<Tab> closedTabs) {
        ButtonType cancelled = ButtonType.YES;
        for (Tab t : closedTabs) {
            if (cancelled == ButtonType.CANCEL || closeTab((DatabasePath) t.getUserData()) == ButtonType.CANCEL) {
                cancelled = ButtonType.CANCEL;
                Platform.runLater(() -> tabPane.getTabs().add(t));
            }
        }
    }

    private ButtonType closeTab(DatabasePath path) {
        // non-dirty files can be closed without worries
        if (!getApplicationPartController(path).getCommandStack().isDirty()) {
            removeDatabaseReference(path);
            return ButtonType.YES;
        }

        // close if saving is not desired or we saved successfully
        Alert alert = new Alert(AlertType.CONFIRMATION, "There are unsaved changes in " + path.toString() + ", do you want to save them?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        ButtonType response = alert.showAndWait().orElse(ButtonType.NO);
        if (response == ButtonType.NO) { // no
            removeDatabaseReference(path);
            return ButtonType.YES;
        } else if (response == ButtonType.YES && this.save(path)) { // yes and save success
            removeDatabaseReference(path);
            return ButtonType.YES;
        } else if (response == ButtonType.YES) { // yes but save failed
            return ButtonType.CANCEL;
        } else if (response == ButtonType.CANCEL) { // cancel clicked
            return ButtonType.CANCEL;
        } else {
            throw new AssertionError();
        }
    }

    private void removeDatabaseReference(DatabasePath path) {
        ApplicationPartController ctrl = applicationPartControllers.remove(path);
        DatabasePath databasePath = ctrl.getDatabaseInterface().getPath();
        MutableInt refCounter = databaseInterfaces.get(databasePath).getLeft();
        refCounter.decrement();

        if (refCounter.intValue() == 0) {
            databaseInterfaces.remove(databasePath);
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
                        if (plugin.getPluginState() != PluginState.RESOLVED && plugin.getPluginState() != PluginState.DISABLED) {
                            this.uninstallPlugin(pluginId);
                            throw new RuntimeException("Plugin could not be resolved");
                        }

                        startPlugin(pluginId);
                        addPluginMenuEntries(plugin);
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                        setStatus("Failed loading plugin: " + getMessage(ex));
                    }
                });
    }

    private void startPlugin(String pluginId) {
        pluginManager.startPlugin(pluginId);
        pluginManager.enablePlugin(pluginId);

        PluginWrapper plugin = pluginManager.getPlugin(pluginId);
        if (plugin.getPluginState() != PluginState.STARTED) {
            this.uninstallPlugin(pluginId);
            throw new RuntimeException("Plugin could not be started");
        }
        applicationPartControllers.values().stream().forEach(a -> a.addPlugin(plugin));
    }

    private void stopPlugin(String pluginId) {
        applicationPartControllers.values().forEach(a -> a.removePlugin(pluginManager.getPlugin(pluginId)));

        pluginManager.stopPlugin(pluginId);
        pluginManager.disablePlugin(pluginId);
    }

    private void uninstallPlugin(String pluginId) {
        uninstallPluginMenu.getItems().removeIf(mi -> Objects.equals(mi.getUserData(), pluginId));
        pluginStateMenu.getItems().removeIf(mi -> Objects.equals(mi.getUserData(), pluginId));
        stopPlugin(pluginId);

        pluginManager.deletePlugin(pluginId);
    }
}
