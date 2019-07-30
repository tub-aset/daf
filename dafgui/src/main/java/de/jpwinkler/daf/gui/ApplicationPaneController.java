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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
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
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ManifestPluginDescriptorFinder;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;

public final class ApplicationPaneController extends AutoloadingPaneController<ApplicationPaneController> implements ApplicationPaneInterface {

    private static final int MAX_STATUS_MENU_ITEMS = 10;
    private static final int MAX_RECENT_FILES = 10;
    private static final int MAX_SELECTED_FILES_HISTORY = 100;

    private final Map<ApplicationPart, ApplicationPartController> applicationPartControllers = new HashMap<>();
    private final BackgroundTaskExecutorImpl backgroundTaskExecutor = new BackgroundTaskExecutorImpl(
            (bt, p) -> Platform.runLater(() -> this.onBackgroundTaskUpdate(bt, p)),
            (bt, t) -> Platform.runLater(() -> this.setStatus("Error in task " + bt.getName() + ": " + getMessage(t))));

    private final ApplicationPartFactoryRegistry applicationPartFactoryRegistry = new ApplicationPartFactoryRegistry(backgroundTaskExecutor,
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
    private ProgressBar statusMessageProgressBar;

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private Menu newMenu;

    @FXML
    private Menu openMenu;

    @FXML
    private Menu pluginStateMenu;

    @FXML
    private Menu uninstallPluginMenu;

    @FXML
    private Menu recentMenu;

    @FXML
    private VBox recentFilesVbox;
    private int tabPaneRecentFilesVboxPos;

    @FXML
    private VBox mainVbox;

    @FXML
    private CheckMenuItem keepTabsSortedCheckItem;

    @FXML
    private ListView<ApplicationPart> recentFilesList;

    @FXML
    private ImageView iconImageView;

    @FXML
    private Pane tabSwitchPane;

    @FXML
    private ListView<ApplicationPart> tabSwitchListView;
    private final List<ApplicationPart> tabHistory = new LinkedList<>();
    private Integer tabHistoryPosition;

    private final Timer generalTimer = new Timer(true);

    private final TreeMap<Long, ApplicationPart> recentFiles = ApplicationPreferences.RECENT_FILES.retrieve();

    private final ListChangeListener<Menu> partMenuChangeLister = (change) -> {
        while (change.next()) {
            change.getRemoved().forEach(this.mainMenuBar.getMenus()::remove);
            this.tabChangeListener.changed(tabPane.getSelectionModel().selectedItemProperty(), tabPane.getSelectionModel().getSelectedItem(), tabPane.getSelectionModel().getSelectedItem());
        }
    };

    private Consumer<String> titleSetter;
    private final ChangeListener<Tab> tabChangeListener = (observable, oldValue, newValue) -> {
        tabPaneRecentFilesVboxPos = mainVbox.getChildren().indexOf(recentFilesVbox);
        if (tabPaneRecentFilesVboxPos == -1) {
            tabPaneRecentFilesVboxPos = mainVbox.getChildren().indexOf(tabPane);
        }

        if (oldValue != null) {
            ObservableList<Menu> partMenus = getApplicationPartController(oldValue).getMenus();
            partMenus.forEach(mainMenuBar.getMenus()::remove);
            partMenus.removeListener(partMenuChangeLister);
        }

        if (newValue != null) {
            ApplicationPart appPart = getApplicationPartController(newValue).getApplicationPart();
            ObservableList<Menu> partMenus = getApplicationPartController(appPart).getMenus();
            partMenus.forEach(mainMenuBar.getMenus()::add);
            partMenus.addListener(partMenuChangeLister);
            titleSetter.accept(newValue.getText());

            mainVbox.getChildren().remove(recentFilesVbox);
            if (!mainVbox.getChildren().contains(tabPane)) {
                mainVbox.getChildren().add(tabPaneRecentFilesVboxPos, tabPane);
            }

            if (!this.tabSwitchListView.isVisible()) {
                addToHistory(appPart);
            }
        } else {
            mainVbox.getChildren().remove(tabPane);
            if (!mainVbox.getChildren().contains(recentFilesVbox)) {
                mainVbox.getChildren().add(tabPaneRecentFilesVboxPos, recentFilesVbox);
            }

            titleSetter.accept(null);
        }
    };

    public ApplicationPaneController(Consumer<String> titleSetter) {
        this.titleSetter = titleSetter;
        titleSetter.accept(null);
        tabChangeListener.changed(null, null, null);

        this.iconImageView.setImage(ApplicationIcons.DOOR_BIG.toImage());

        tabPane.getSelectionModel().selectedItemProperty().addListener(tabChangeListener);

        tabPane.getTabs().addListener((ListChangeListener<Tab>) (change) -> {
            List<Pair<Integer, Tab>> closedTabs = new ArrayList<>();
            while (change.next()) {
                for (Tab selectedTab : change.getRemoved()) {
                    closedTabs.add(Pair.of(change.getFrom(), selectedTab));
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
                    added.openWithSelector(getNode().getScene().getWindow()).forEach(
                            path -> open(path, OpenFlag.OPEN_ONLY));
                });

                openMenu.getItems().add(it);
                if (added.isAllowNew()) {
                    it = new MenuItem(added.getName());
                    it.setUserData(added);
                    it.setOnAction(ev -> {
                        added.saveWithSelector(getNode().getScene().getWindow(), "").forEach(
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

        this.tabSwitchListView.addEventFilter(MouseEvent.ANY, t -> t.consume());
        this.tabSwitchListView.addEventFilter(KeyEvent.ANY, t -> t.consume());

        this.getNode().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            boolean isCtrlTab = e.isControlDown() && e.getCode() == KeyCode.TAB;
            boolean isAltLeftRight = e.isAltDown() && (e.getCode() == KeyCode.LEFT
                    || e.getCode() == KeyCode.RIGHT
                    || e.getCode() == KeyCode.UP
                    || e.getCode() == KeyCode.DOWN);
            boolean next = (isCtrlTab && !e.isShiftDown()) || (isAltLeftRight && (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.DOWN));
            SelectionModel<ApplicationPart> selMod = this.tabSwitchListView.getSelectionModel();

            if (!this.tabSwitchListView.isVisible() && (isCtrlTab || isAltLeftRight)) {
                // initial selection
                if (isCtrlTab) {
                    this.tabHistory.stream()
                            .filter(a -> this.applicationPartControllers.containsKey(a))
                            .distinct()
                            .forEach(this.tabSwitchListView.getItems()::add);
                } else if (isAltLeftRight) {
                    this.tabSwitchListView.getItems().addAll(tabHistory);
                }

                if (!this.tabSwitchListView.getItems().isEmpty()) {
                    this.tabSwitchPane.setVisible(true);
                    this.tabSwitchListView.setVisible(true);

                    this.tabSwitchListView.requestFocus();

                    if (this.tabSwitchListView.getItems().size() == 1) {
                        selMod.selectFirst();
                    } else if (next && (isCtrlTab || this.tabHistoryPosition == null)) {
                        selMod.select(1);
                    } else if (!next && (isCtrlTab || this.tabHistoryPosition == null)) {
                        selMod.selectLast();
                    } else if (next && isAltLeftRight) {
                        selMod.select(this.tabHistoryPosition + 1);
                    } else if (!next && isAltLeftRight) {
                        selMod.select(this.tabHistoryPosition - 1);
                    }

                    this.tabSwitchListView.scrollTo(selMod.getSelectedIndex());
                    if (isAltLeftRight) {
                        tabHistoryPosition = selMod.getSelectedIndex();
                    }
                }

                tabPane.getTabs().stream()
                        .filter(t -> Objects.equals(selMod.getSelectedItem(), ((ApplicationPart) t.getUserData())))
                        .findFirst()
                        .ifPresent(t -> this.tabPane.getSelectionModel().select(t));
                e.consume();
            } else if (this.tabSwitchListView.isVisible() && (isCtrlTab || isAltLeftRight)) {
                // following selections

                if (selMod.isEmpty() && next) {
                    selMod.select(2);
                } else if (selMod.isEmpty() && !next) {
                    selMod.selectLast();
                } else if (next) {
                    if (selMod.getSelectedIndex() == this.tabSwitchListView.getItems().size() - 1) {
                        selMod.selectFirst();
                    } else {
                        selMod.select(selMod.getSelectedIndex() + 1);
                    }
                } else {
                    if (selMod.getSelectedIndex() == 0) {
                        selMod.selectLast();
                    } else {
                        selMod.select(selMod.getSelectedIndex() - 1);
                    }
                }

                this.tabSwitchListView.scrollTo(selMod.getSelectedIndex());
                if (isAltLeftRight) {
                    tabHistoryPosition = selMod.getSelectedIndex();
                    if (this.applicationPartControllers.containsKey(selMod.getSelectedItem())) {
                        this.open(selMod.getSelectedItem(), OpenFlag.OPEN_ONLY);
                    }
                }
                e.consume();
            } else if (e.isControlDown() && e.getCode() == KeyCode.TAB) {
                e.consume();
            }
        });

        Platform.runLater(() -> this.getNode().getScene().getWindow().focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                this.tabSwitchPane.setVisible(false);
                this.tabSwitchListView.setVisible(false);

                this.tabSwitchListView.getItems().clear();
            }
        }));

        this.getNode().addEventFilter(KeyEvent.KEY_RELEASED, e -> {
            if (this.tabSwitchListView.isVisible() && (e.getCode() == KeyCode.CONTROL || e.getCode() == KeyCode.ALT)) {
                ApplicationPart selectedPart = tabSwitchListView.getSelectionModel().getSelectedItem();
                if (e.getCode() == KeyCode.ALT && !this.applicationPartControllers.containsKey(selectedPart)) {
                    this.open(selectedPart, OpenFlag.OPEN_ONLY);
                } else {
                    if (e.getCode() != KeyCode.ALT) {
                        addToHistory(selectedPart);
                    }
                }

                this.tabSwitchPane.setVisible(false);
                this.tabSwitchListView.setVisible(false);

                this.tabSwitchListView.getItems().clear();
                e.consume();
            }
        });

        this.tabSwitchListView.setCellFactory(lv -> {
            return new ListCell<ApplicationPart>() {
                @Override
                protected void updateItem(ApplicationPart t, boolean empty) {
                    super.updateItem(t, empty);
                    if (t != null && !empty) {
                        setText(t.toString());

                        if (!ApplicationPaneController.this.applicationPartControllers.containsKey(t)) {
                            setStyle("-fx-text-fill: lightgrey");
                        } else {
                            setStyle("");
                        }
                    } else {
                        setText(null);
                    }
                }

            };
        });

        keepTabsSortedCheckItem.setSelected(ApplicationPreferences.KEEP_TABS_SORTED.retrieve());
        setTagsDraggable();
        keepTabsSortedCheckItem.selectedProperty().addListener((ov, oldValue, newValue) -> {
            ApplicationPreferences.KEEP_TABS_SORTED.store(newValue);
            setTagsDraggable();
        });
    }

    public void setTagsDraggable() {
        String target;
        if (ApplicationPreferences.KEEP_TABS_SORTED.retrieve()) {
            sortTabsClicked();
            target = "FIXED";
        } else {
            target = "REORDER";
        }

        try {
            @SuppressWarnings("unchecked")
            Class<? extends Enum> tdPolicy = (Class<? extends Enum>) Class.forName("javafx.scene.control.TabPane$TabDragPolicy", true, TabPane.class.getClassLoader());
            Enum[] enumConstants = tdPolicy.getEnumConstants();
            Enum constant = Stream.of(enumConstants)
                    .filter(e -> target.equals(e.name()))
                    .findAny().orElseThrow(() -> new ClassNotFoundException("Missing enum constant: " + target));

            tabPane.getClass().getMethod("setTabDragPolicy", tdPolicy).invoke(tabPane, constant);
        } catch (ClassNotFoundException | ClassCastException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ApplicationPaneController.class.getName()).log(Level.WARNING, "Running below Java 10, tab reordering not available", ex);
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
        return tab == null ? null : getApplicationPartController((ApplicationPart) tab.getUserData());
    }

    private ApplicationPartController<?> getApplicationPartController(ApplicationPart part) {
        return applicationPartControllers.get(part);
    }

    public void setStatus(final String status) {
        this.setStatus(status, 10000);
    }

    private TimerTask statusTimerTask = new TimerTask() {
        @Override
        public void run() {
        }
    };

    public void setStatus(final String status, int timeout) {
        if (statusMenuButton.getItems().isEmpty() || !status.equals(statusMenuButton.getItems().get(statusMenuButton.getItems().size() - 1).getText())) {
            MenuItem statusMenuItem = new MenuItem(status);
            statusMenuButton.getItems().add(statusMenuItem);
            if (statusMenuButton.getItems().size() > MAX_STATUS_MENU_ITEMS) {
                statusMenuButton.getItems().remove(0);
            }
        }

        statusBarLabel.setText(status);
        statusMessageProgressBar.setProgress(1);
        statusMessageProgressBar.setVisible(true);
        long startTime = System.currentTimeMillis();

        this.statusTimerTask.cancel();
        this.statusTimerTask = new TimerTask() {
            @Override
            public void run() {
                double progress = (System.currentTimeMillis() - (double) startTime) / timeout;
                if (progress >= 1d) {
                    this.cancel();
                    Platform.runLater(() -> {
                        statusBarLabel.setText("");
                        statusMessageProgressBar.setVisible(false);
                    });
                } else {
                    Platform.runLater(() -> statusMessageProgressBar.setProgress(1 - progress));
                }
            }
        };

        generalTimer.scheduleAtFixedRate(statusTimerTask, 100, 100);
    }

    @Override
    public ApplicationPartController open(DatabasePath path, OpenFlag openFlag) {
        return this.open(applicationPartFactoryRegistry.getDefaultPart(path), openFlag);
    }

    @Override
    public ApplicationPartController open(ApplicationPart part, OpenFlag openFlag) {
        if (!tabSwitchListView.isVisible() && tabHistoryPosition != null) {
            int targetSize = tabHistory.size() - tabHistoryPosition;
            while (tabHistory.size() > targetSize) {
                tabHistory.remove(0);
            }
            tabHistoryPosition = null;
        }

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
                        newTab.setStyle("-fx-background-color: " + getTabColor(part.getDatabasePath()));

                        newTab.setUserData(part);
                        applicationPartControllers.put(part, controller);

                        newTab.setClosable(true);
                        tabPane.getTabs().add(newTab);

                        if (ApplicationPreferences.KEEP_TABS_SORTED.retrieve()) {
                            sortTabsClicked();
                        }
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
        if (t.getCause() != null) {
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
        recentFilesList.getItems().clear();

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

            recentFilesList.getItems().add(recentFile);
        }
    }

    @FXML
    public void recentFilesListClicked(MouseEvent me) {
        if (me.getButton() == MouseButton.PRIMARY && me.getClickCount() == 2) {
            this.open(this.recentFilesList.getSelectionModel().getSelectedItem(), OpenFlag.OPEN_ONLY);
        }
    }

    @FXML
    public void saveClicked() {
        save(getCurrentFileStateController().getApplicationPart());
    }

    private boolean save(ApplicationPart part) {
        try {
            part.getDatabaseInterface().flushAsync().get();
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
        return this.getBackgroundTaskExecutor().runBackgroundTask("Creating snapshot", i -> {
            DatabasePath destinationPath = destinationPathArg;
            DoorsTreeNode copyRoot;
            try {
                copyRoot = sourceDB.getDatabaseRootAsync().get().getChild(sourcePath.getPath());
                if (destinationPathArg == null) {
                    String proposedName;
                    if (!sourcePath.getPathSegments().isEmpty()) {
                        proposedName = sourcePath.getPathSegments().get(sourcePath.getPathSegments().size() - 1);
                    } else if (!sourcePath.getDatabasePathSegments().isEmpty()) {
                        proposedName = sourcePath.getDatabasePathSegments().get(sourcePath.getDatabasePathSegments().size() - 1);
                    } else {
                        proposedName = "";
                    }

                    CompletableFuture<DatabasePath> destinationPathFuture = new CompletableFuture<>();
                    Platform.runLater(() -> {
                        ChoiceDialog<ApplicationPartFactory> applicationPartChooser = new ChoiceDialog<>(null, applicationPartFactoryRegistry.registry().stream()
                                .filter(p -> p.isAllowNew())
                                .filter(p -> p.canStore(copyRoot))
                                .sorted((p1, p2) -> Objects.compare(p1.getName(), p2.getName(), Comparator.naturalOrder()))
                                .collect(Collectors.toList()));
                        applicationPartChooser.setTitle("Create snapshot");
                        applicationPartChooser.setHeaderText("Select a destination database type");
                        destinationPathFuture.complete(Main.asStream(applicationPartChooser.showAndWait())
                                .flatMap(part -> part.saveWithSelector(getNode().getScene().getWindow(), proposedName))
                                .map(part -> part.getDatabasePath())
                                .findAny().orElse(null));
                    });

                    destinationPath = destinationPathFuture.get();
                    if (destinationPath == null) {
                        return null;
                    }
                }
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            }

            DatabaseInterface destinationDB = applicationPartFactoryRegistry.openDatabase(destinationPath, OpenFlag.ERASE_IF_EXISTS).getLeft();
            try {
                destinationDB.getFactory().copy(FilteredDoorsTreeNode.createFilteredTree(copyRoot, include, false), destinationDB.getDatabaseRootAsync().get(), true, i);
                DoorsAttributes.DATABASE_COPIED_FROM.setValue(String.class,
                        destinationDB.getDatabaseRootAsync().get(), sourceDB.getPath().toString());
                DoorsAttributes.DATABASE_COPIED_AT.setValue(String.class,
                        destinationDB.getDatabaseRootAsync().get(), ZonedDateTime.now(ZoneOffset.UTC).toString());

                destinationDB.flushAsync().get();
                return destinationPath;
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            } finally {
                applicationPartFactoryRegistry.closeDatabase(destinationPath);
            }
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
            Alert alert = new Alert(AlertType.CONFIRMATION, "There are running background tasks. "
                    + "Are you sure you want to close the application? You might lose their state.", ButtonType.NO, ButtonType.YES);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.NO) {
                return false;
            }
        }

        ApplicationPartController lastSelectedPartCtrl = getApplicationPartController(tabPane.getSelectionModel().getSelectedItem());
        ArrayList<ApplicationPart> exitParts = tabPane.getTabs().stream()
                .map(t -> getApplicationPartController(t))
                .map(pc -> pc.getApplicationPart())
                .collect(Collectors.toCollection(() -> new ArrayList<>()));

        String dirtyDatabases = exitParts.stream()
                .filter(p -> p.getCommandStack().isDirty())
                .map(p -> p.getDatabasePath().withPath("").toString())
                .sorted()
                .distinct()
                .collect(Collectors.joining("\n"));
        if (!dirtyDatabases.isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "There unsaved changes in the following databases:\n\n" + dirtyDatabases
                    + "\n\nAre you sure you want to close the application? You will lose those changes.", ButtonType.NO, ButtonType.YES);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.NO) {
                return false;
            }
        }

        ApplicationPreferences.EXIT_FILES.store(exitParts);
        ApplicationPreferences.LAST_SELECTED_FILE.store(lastSelectedPartCtrl != null ? lastSelectedPartCtrl.getApplicationPart() : null);
        return true;
    }

    @FXML
    public void exitClicked() {
        this.getNode().getScene().getWindow().fireEvent(new WindowEvent(this.getNode().getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    private ButtonType closeTabs(Collection<Pair<Integer, Tab>> closedTabs) {
        ButtonType cancelled = ButtonType.YES;
        for (Pair<Integer, Tab> t : closedTabs) {
            if (cancelled == ButtonType.CANCEL || closeTab((ApplicationPart) t.getRight().getUserData()) == ButtonType.CANCEL) {
                cancelled = ButtonType.CANCEL;
                Platform.runLater(() -> tabPane.getTabs().add(t.getLeft(), t.getRight()));
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
        
        // files for which there is another open view as well
        if (part.getController().isOpened(part.getDatabasePath().withPath(""))) {
            applicationPartControllers.remove(part.stop());
            return ButtonType.YES;
        }

        // close if saving is not desired or we saved successfully
        Alert alert = new Alert(AlertType.CONFIRMATION, "There are unsaved changes in:\n\n" + part.toString() + "\n\nDo you want to save them?",
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

        Stream.of(fileChooser.showOpenDialog(getNode().getScene().getWindow()))
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
        Dialog dialog = controller.asDialog(getNode().getScene().getWindow(), "About DOORS Access Framework", ButtonType.OK);
        controller.getTextArea().setEditable(false);
        dialog.setHeaderText("About DOORS Access Framework");
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        dialog.getDialogPane().setMinWidth(800);
        dialog.showAndWait();
    }

    @FXML
    public void sortTabsClicked() {
        this.tabPane.getTabs().sort(
                Comparator.comparing(t -> {
                    DatabasePath path = getApplicationPartController(t).getApplicationPart().getDatabasePath();
                    return Triple.of(path.getDatabaseInterface(), path.getDatabasePath(), path.getPath());
                }));
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

    public Collection<ApplicationPartController> getApplicationPartControllers() {
        return Collections.unmodifiableCollection(this.applicationPartControllers.values());
    }

    private String getTabColor(DatabasePath path) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(path.withPath("").toString().getBytes("UTF-8"));
            Color color = Color.hsb(new BigInteger(digest).doubleValue(), 0.3d, 0.8d);
            return String.format("#%02X%02X%02X",
                    (int) (color.getRed() * 255),
                    (int) (color.getGreen() * 255),
                    (int) (color.getBlue() * 255));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToHistory(ApplicationPart appPart) {
        if (tabHistory.isEmpty() || !tabHistory.get(0).equals(appPart)) {
            tabHistory.add(0, appPart);

            while (tabHistory.size() > MAX_SELECTED_FILES_HISTORY) {
                tabHistory.remove(tabHistory.size() - 1);
            }
        }
    }
}
