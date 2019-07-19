package de.jpwinkler.daf.gui;

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
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.filter.model.FilteredDoorsTreeNode;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPart;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPartFactory;
import de.jpwinkler.daf.gui.controls.MultiLineTextInputDialog;
import de.jpwinkler.daf.gui.controls.ProgressMenuItemController;
import de.jpwinkler.daf.gui.controls.ProgressMenuItemController.ProgressMenuItem;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
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
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ManifestPluginDescriptorFinder;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;

public final class ApplicationPaneController extends AutoloadingPaneController<ApplicationPaneController> implements ApplicationPaneInterface {

    private static final int MAX_STATUS_MENU_ITEMS = 10;
    private static final int MAX_RECENT_FILES = 10;

    private final Map<ApplicationPart, ApplicationPartController> applicationPartControllers = new HashMap<>();
    private final BackgroundTaskExecutorImpl backgroundTaskExecutor = new BackgroundTaskExecutorImpl(
            (bt, p) -> Platform.runLater(() -> this.onBackgroundTaskUpdate(bt, p)),
            (bt, t) -> Platform.runLater(() -> this.setStatus("Error in task " + bt.getName() + ": " + getMessage(t))));

    private final ApplicationPartFactoryRegistry applicationPartFactoryRegistry = new ApplicationPartFactoryRegistry(
            () -> this.pluginManager.getPlugins(PluginState.STARTED).stream().distinct(),
            (part, dirty) -> this.tabPane.getTabs().stream()
                    .filter(t -> ((ApplicationPart) t.getUserData()) == part)
                    .forEach(t -> t.setText(((ApplicationPart) t.getUserData()).getDatabasePath().toString() + (dirty ? "*" : ""))));
    private final List<ApplicationPaneExtension> extensions = new ArrayList<>();
    private final Map<Menu, PluginWrapper> extensionMenus = new HashMap<>();

    private final PluginManager pluginManager = new DefaultPluginManager(ApplicationPreference.APPLICATION_PLUGINS_PATH) {
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

    private final TreeMap<Long, ApplicationPart> recentFiles = ApplicationPreferences.RECENT_FILES.retrieve();
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
        tabPane.getSelectionModel().selectedItemProperty().addListener(tabChangeListener);
        try {
            Class<? extends Enum> tdPolicy = (Class<? extends Enum>) Class.forName("javafx.scene.control.TabPane$TabDragPolicy", true, TabPane.class.getClassLoader());
            Enum[] enumConstants = tdPolicy.getEnumConstants();
            Enum constant = Stream.of(enumConstants)
                    .filter(e -> "REORDER".equals(e.name()))
                    .findAny().orElseThrow(() -> new ClassNotFoundException("Missing enum constant: REORDER"));

            tabPane.getClass().getMethod("setTabDragPolicy", tdPolicy).invoke(tabPane, constant);
        } catch (ClassNotFoundException | ClassCastException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ApplicationPaneController.class.getName()).log(Level.WARNING, "Running below Java 10, tab reordering not available", ex);
        }

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

        applicationPartFactoryRegistry.addListener((added, removed) -> {
            if (removed != null) {
                openMenu.getItems().removeIf(p -> p.getUserData() == removed);
                newMenu.getItems().removeIf(p -> p.getUserData() == removed);

                String openPaths = this.applicationPartControllers.values().stream()
                        .filter(c -> c.getApplicationPart().getApplicationPartFactory() == removed)
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
                        added.saveWithSelector(tabPane.getScene().getWindow(), "").forEach(
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
        ApplicationPartFactories.registerDefault(applicationPartFactoryRegistry);

        ((ArrayList<ApplicationPart>) ApplicationPreferences.EXIT_FILES.retrieve()).forEach(part -> this.open(part, OpenFlag.OPEN_ONLY));
        ApplicationPart lastSelectedPart = ApplicationPreferences.LAST_SELECTED_FILE.retrieve();
        if (lastSelectedPart != null) {
            Tab tab = tabPane.getTabs().stream()
                    .filter(t -> lastSelectedPart.equals((ApplicationPart) t.getUserData()))
                    .findAny().orElse(null);
            this.tabPane.getSelectionModel().select(tab);
        }
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

        backgroundTaskMenuButton.getItems().removeAll(backgroundTaskMenuButton.getItems().stream()
                .map(mi -> (ProgressMenuItem) mi)
                .filter(mi -> mi.getBackgroundTask().getTaskStatus() != BackgroundTask.TaskStatus.RUNNING)
                .limit(Math.max(0, backgroundTaskMenuButton.getItems().size() - MAX_STATUS_MENU_ITEMS))
                .collect(Collectors.toList()));
    }

    public ApplicationPartFactoryRegistry getApplicationPartFactoryRegistry() {
        return applicationPartFactoryRegistry;
    }

    @Override
    public BackgroundTaskExecutorImpl getBackgroundTaskExecutor() {
        return backgroundTaskExecutor;
    }

    private ApplicationPartController<?> getCurrentFileStateController() {
        return getApplicationPartController(tabPane.getSelectionModel().getSelectedItem());
    }

    private ApplicationPartController<?> getApplicationPartController(Tab tab) {
        return getApplicationPartController((ApplicationPart) tab.getUserData());
    }

    private ApplicationPartController<?> getApplicationPartController(ApplicationPart part) {
        return applicationPartControllers.get(part);
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
        return this.open(applicationPartFactoryRegistry.getDefaultPart(path), openFlag);
    }

    @Override
    public ApplicationPartController open(ApplicationPart part, OpenFlag openFlag) {
        Tab tab = tabPane.getTabs().stream()
                .filter(t -> part.equals((ApplicationPart) t.getUserData()))
                .findAny()
                .map(t -> {
                    if (openFlag == OpenFlag.ERASE_IF_EXISTS) {
                        setStatus("Database " + part.getDatabasePath().toString() + " could not be created: already open");
                        return null;
                    }

                    addToRecentMenu(part);
                    tabPane.getSelectionModel().select(t);
                    return t;
                })
                .orElseGet(() -> {
                    try {
                        final ApplicationPartController controller = part.start(this, openFlag);
                        final Parent modulePane = controller.getNode();

                        final Tab newTab = new Tab(part.toString(), modulePane);
                        newTab.setTooltip(new Tooltip(part.toString()));

                        newTab.setUserData(part);
                        applicationPartControllers.put(part, controller);

                        newTab.setClosable(true);
                        tabPane.getTabs().add(newTab);
                        return newTab;
                    } catch (Throwable ex) {
                        ex.printStackTrace();

                        setStatus("Open: Failed to open database; " + getMessage(ex));
                        return null;
                    }
                });

        if (tab == null) {
            return null;
        }

        addToRecentMenu(part);
        tabPane.getSelectionModel().select(tab);
        return getApplicationPartController(tabPane.getSelectionModel().getSelectedItem());
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

    private void addToRecentMenu(ApplicationPart part) {
        if (part != null) {
            recentFiles.values().remove(part);
            recentFiles.put(new Date().getTime(), part);
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

        for (ApplicationPart recentFile : recentFiles.descendingMap().values()) {
            MenuItem recentMenuItem = new MenuItem(recentFile.toString());
            recentMenuItem.setUserData(recentFile);
            recentMenu.getItems().add(recentMenuItem);
            recentMenuItem.setOnAction(ev -> this.open((ApplicationPart) ((MenuItem) ev.getTarget()).getUserData(), OpenFlag.OPEN_ONLY));
        }
    }

    @FXML
    public void saveClicked() {
        save(getCurrentFileStateController().getApplicationPart());
    }

    private boolean save(ApplicationPart part) {
        try {
            part.getDatabaseInterface().flush();
            part.getCommandStack().setSavePoint();
        } catch (Throwable ex) {
            ex.printStackTrace();

            this.setStatus("Save: Failed to save file; " + getMessage(ex));
            return false;
        }

        addToRecentMenu(part);
        return true;
    }

    @FXML
    public void saveAsClicked() {
        this.createSnapshot(getCurrentFileStateController().getDatabaseInterface(), getCurrentFileStateController().getPath(), x -> true, null)
                .thenAccept(destinationPath -> {
                    if (destinationPath != null) {
                        Platform.runLater(() -> {
                            this.open(destinationPath, OpenFlag.OPEN_ONLY);
                        });
                    }
                });
    }

    @Override
    public CompletableFuture<DatabasePath> createSnapshot(DatabaseInterface sourceDB, DatabasePath sourcePath, Predicate<DoorsTreeNode> include, DatabasePath destinationPathArg) {
        DatabasePath destinationPath;
        DoorsTreeNode copyRoot = sourceDB.getDatabaseRoot().getChild(sourcePath.getPath());
        if (destinationPathArg == null) {
            String proposedName;
            if (!sourcePath.getPathSegments().isEmpty()) {
                proposedName = sourcePath.getPathSegments().get(sourcePath.getPathSegments().size() - 1);
            } else if (!sourcePath.getDatabasePathSegments().isEmpty()) {
                proposedName = sourcePath.getDatabasePathSegments().get(sourcePath.getDatabasePathSegments().size() - 1);
            } else {
                proposedName = "";
            }

            ChoiceDialog<ApplicationPartFactory> applicationPartChooser = new ChoiceDialog<>(null, applicationPartFactoryRegistry.registry().stream()
                    .filter(p -> p.isAllowNew())
                    .filter(p -> p.canStore(copyRoot))
                    .sorted((p1, p2) -> Objects.compare(p1.getName(), p2.getName(), Comparator.naturalOrder()))
                    .collect(Collectors.toList()));
            applicationPartChooser.setTitle("Create snapshot");
            applicationPartChooser.setHeaderText("Select a destination database type");
            destinationPathArg = Main.asStream(applicationPartChooser.showAndWait())
                    .flatMap(part -> part.saveWithSelector(tabPane.getScene().getWindow(), proposedName))
                    .map(part -> part.getDatabasePath())
                    .findAny().orElse(null);

            if (destinationPathArg == null) {
                return CompletableFuture.completedFuture(null);
            }

            destinationPath = destinationPathArg;
        } else {
            destinationPath = destinationPathArg;
        }

        return this.getBackgroundTaskExecutor().runBackgroundTask("Creating snapshot", i -> {
            DatabaseInterface destinationDB = applicationPartFactoryRegistry.openDatabase(destinationPath, OpenFlag.ERASE_IF_EXISTS).getLeft();
            try {
                destinationDB.getFactory().copy(FilteredDoorsTreeNode.createFilteredTree(copyRoot, include, false), destinationDB.getDatabaseRoot(), true, i);
                DoorsAttributes.DATABASE_COPIED_FROM.setValue(String.class,
                        destinationDB.getDatabaseRoot(), sourceDB.getPath().toString());
                DoorsAttributes.DATABASE_COPIED_AT.setValue(String.class,
                        destinationDB.getDatabaseRoot(), ZonedDateTime.now(ZoneOffset.UTC).toString());

                destinationDB.flush();
                return destinationPath;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } finally {
                applicationPartFactoryRegistry.closeDatabase(destinationPath);
            }
        }).handleAsync((val, ex) -> {
            Platform.runLater(() -> {
                if (ex != null) {
                    ex.printStackTrace();
                }
            });
            return val;
        });
    }

    @FXML
    public void closeClicked() {
        if (tabPane.getSelectionModel().isEmpty()) {
            return;
        }

        tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
    }

    public boolean tryClose() {
        if (backgroundTaskExecutor.hasRunningTasks()) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "There are running background tasks. Are you sure you want to close the application? You might lose their state.", ButtonType.CANCEL, ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.CANCEL) {
                return false;
            }
        }

        ArrayList<ApplicationPart> exitParts = tabPane.getTabs().stream()
                .map(t -> getApplicationPartController(t))
                .map(pc -> pc.getApplicationPart())
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
        ApplicationPart lastSelectedPart = getApplicationPartController(tabPane.getSelectionModel().getSelectedItem()).getApplicationPart();
        tabPane.getTabs().clear();
        if (!applicationPartControllers.isEmpty()) {
            return false;
        }

        ApplicationPreferences.EXIT_FILES.store(exitParts);
        ApplicationPreferences.LAST_SELECTED_FILE.store(lastSelectedPart);
        return true;
    }

    @FXML
    public void exitClicked() {
        this.tabPane.getScene().getWindow().fireEvent(new WindowEvent(this.tabPane.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    private ButtonType closeTabs(Collection<Tab> closedTabs) {
        ButtonType cancelled = ButtonType.YES;
        for (Tab t : closedTabs) {
            if (cancelled == ButtonType.CANCEL || closeTab((ApplicationPart) t.getUserData()) == ButtonType.CANCEL) {
                cancelled = ButtonType.CANCEL;
                Platform.runLater(() -> tabPane.getTabs().add(t));
            }
        }
        return cancelled;
    }

    private ButtonType closeTab(ApplicationPart part) {
        // non-dirty files can be closed without worries
        if (!part.getCommandStack().isDirty()) {
            applicationPartControllers.remove(part.stop());
            return ButtonType.YES;
        }

        // close if saving is not desired or we saved successfully
        Alert alert = new Alert(AlertType.CONFIRMATION, "There are unsaved changes in " + part.toString() + ", do you want to save them?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        ButtonType response = alert.showAndWait().orElse(ButtonType.NO);
        if (response == ButtonType.NO) { // no
            applicationPartControllers.remove(part.stop());
            return ButtonType.YES;
        } else if (response == ButtonType.YES && this.save(part)) { // yes and save success
            applicationPartControllers.remove(part.stop());
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

    @FXML
    public void showLicensesClicked() throws IOException {
        String text;
        try (InputStream is = ApplicationPaneController.class.getClassLoader().getResourceAsStream("THIRD-PARTY.txt")) {
            text = IOUtils.readLines(is, Charset.forName("UTF-8")).stream().collect(Collectors.joining("\n"));
        }

        MultiLineTextInputDialog controller = new MultiLineTextInputDialog("Developed by TU Berlin ASET\nFlorian Wiesweg and Jonas Winkler (2019)\n\nThe following dependencies are bundled with this software.\n" + text);
        Dialog dialog = controller.asDialog(tabPane.getScene().getWindow(), "About " + ((Stage) tabPane.getScene().getWindow()).getTitle(), ButtonType.OK);
        controller.getTextArea().setEditable(false);
        dialog.setHeaderText("About " + ((Stage) tabPane.getScene().getWindow()).getTitle());
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        dialog.getDialogPane().setMinWidth(800);
        dialog.showAndWait();
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

        List<ApplicationPaneExtension> newExts = plugin.getPluginManager().getExtensions(ApplicationPaneExtension.class,
                plugin.getPluginId());
        this.extensions.addAll(newExts);
        newExts.stream()
                .flatMap(e -> e.getApplicationMenus().stream())
                .peek(m -> extensionMenus.put(m, plugin))
                .forEach(mainMenuBar.getMenus()::add);
        newExts.stream()
                .flatMap(e -> e.getApplicationPartFactories().stream())
                .forEach(applicationPartFactoryRegistry::register);
        applicationPartControllers.values().stream().forEach(a -> a.addPlugin(plugin));
    }

    private void stopPlugin(String pluginId) {
        PluginWrapper plugin = pluginManager.getPlugin(pluginId);
        this.extensions.stream()
                .filter(ext -> ext.getClass().getClassLoader() == plugin.getPluginClassLoader())
                .flatMap(e -> e.getApplicationPartFactories().stream())
                .forEach(applicationPartFactoryRegistry::unregister);

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
