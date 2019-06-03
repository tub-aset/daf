package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.background.BackgroundTaskStatusListener;
import de.jpwinkler.daf.gui.background.BackgroundTaskStatusMonitor;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;

public final class ApplicationPaneController extends AutoloadingPaneController<ApplicationPaneController> {

    private final Map<Tab, ApplicationPartController> applicationPartControllers = new HashMap<>();
    private final BackgroundTaskStatusMonitor backgroundTaskStatusMonitor = new BackgroundTaskStatusMonitor();

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
    private Menu recentMenu;

    private final TreeMap<Long, DatabasePath> recentFiles = ApplicationPreferences.RECENT_FILES.retrieve();
    private final int MAX_RECENT_FILES = 10;

    public ApplicationPaneController() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Tab>) (observable, oldValue, newValue) -> {
            if (getApplicationPartController(oldValue) != null) {
                getApplicationPartController(oldValue).getMenus().forEach(m -> {
                    mainMenuBar.getMenus().remove(m);
                });
            }

            if (getApplicationPartController(newValue) != null) {
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
    }

    private ApplicationPartController<?> getCurrentFileStateController() {
        return getApplicationPartController(tabPane.getSelectionModel().getSelectedItem());
    }

    private ApplicationPartController<?> getApplicationPartController(Tab selectedTab) {
        return applicationPartControllers.get(selectedTab);
    }

    public void setStatus(final String status) {
        statusBarLabel.setText(status);
    }

    @FXML
    public void newLocalModuleClicked() {
        open(ApplicationPart.LOCAL_MODULE.newPath(), true);

    }

    @FXML
    public void newLocalDatabaseClicked() {
        open(ApplicationPart.LOCAL_DATABASE.newPath(), true);

    }

    @FXML
    public void openLocalModuleClicked() throws URISyntaxException {
        ApplicationPart.LOCAL_MODULE.openWithSelector(tabPane.getScene().getWindow()).forEach(this::open);
    }

    @FXML
    public void openLocalDatabaseClicked() throws URISyntaxException {
        ApplicationPart.LOCAL_DATABASE.openWithSelector(tabPane.getScene().getWindow()).forEach(this::open);
    }

    @FXML
    public void openDoorsDatabaseClicked() throws URISyntaxException {
        ApplicationPart.DOORS_DATABASE.openWithSelector(tabPane.getScene().getWindow()).forEach(this::open);
    }

    public boolean open(DatabasePath path) {
        return this.open(path, false);
    }

    public boolean open(DatabasePath path, boolean newFile) {
        if (!newFile && applicationPartControllers.entrySet().stream()
                .filter(e -> path.equals(e.getValue().getPath()))
                .findAny()
                .map(e -> {
                    addToRecentMenu(path);
                    tabPane.getSelectionModel().select(e.getKey());
                    return e;
                }).isPresent()) {

            addToRecentMenu(path);
            return true;
        }

        try {
            final ApplicationPartController controller = ApplicationPart.createControllerForAny(this, path);
            final Parent modulePane = controller.getNode();

            final Tab selectedTab = new Tab(path.toString(), modulePane);
            applicationPartControllers.put(selectedTab, controller);

            controller.getCommandStack().setOnDirty(dirty -> {
                selectedTab.setText(path.toString() + (dirty ? "*" : ""));
            });

            selectedTab.setClosable(true);
            tabPane.getTabs().add(selectedTab);

            tabPane.getSelectionModel().select(selectedTab);
            if (!newFile) {
                addToRecentMenu(path);
            }
            return true;
        } catch (Throwable ex) {
            ex.printStackTrace();
            while (ex.getCause() != null) {
                ex = ex.getCause();
            }

            setStatus("Open: Failed to open file; " + getMessage(ex));
            return false;
        }
    }

    private String getMessage(Throwable t) {
        if (t.getMessage() == null && t.getCause() != null) {
            return getMessage(t.getCause());
        } else if (t.getMessage() == null) {
            return t.toString();
        } else {
            return t.getMessage();
        }
    }

    private void addToRecentMenu(DatabasePath path) {
        if (path != null && path.isValid()) {
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
                if (!this.open(uri)) {
                    recentFiles.entrySet().removeIf(e -> e.getValue().equals(uri));
                    ApplicationPreferences.RECENT_FILES.store(recentFiles);
                    generateRecentMenu();
                }
            });
        }
    }

    @FXML
    public void saveClicked() {
        save(getCurrentFileStateController(), true);
    }

    private boolean save(ApplicationPartController fsc, boolean allowSaveAs) {
        if (allowSaveAs && !fsc.getPath().isValid()) {
            ApplicationPart.saveWithSelector(fsc.getPath(), tabPane.getScene().getWindow()).forEach(path -> {
                fsc.setPath(path);
            });
        }

        if (!fsc.getPath().isValid()) {
            this.setStatus("Save: Failed to save file; invalid storage URI");
            return false;
        }

        try {
            fsc.save();
        } catch (IOException ex) {
            this.setStatus("Save: Failed to save file; " + ex.getMessage());
            return false;
        }

        addToRecentMenu(fsc.getPath());
        return true;
    }

    @FXML
    public void saveAsClicked() {
        saveAs(getCurrentFileStateController());
    }

    private void saveAs(ApplicationPartController fsc) {
        ApplicationPart.saveWithSelector(fsc.getPath(), tabPane.getScene().getWindow()).forEach(path -> {
            fsc.setPath(path);
            this.save(fsc, false);
        });
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
        boolean cancelled = false;
        for (Tab t : closedTabs) {
            if (cancelled) {
                Platform.runLater(() -> tabPane.getTabs().add(t));
                continue;
            }

            // non-dirty files can be closed without worries
            if (!getApplicationPartController(t).getCommandStack().isDirty()) {
                applicationPartControllers.remove(t);
                continue;
            }

            // close if saving is not desired or we saved successfully
            Alert alert = new Alert(AlertType.CONFIRMATION, "There are unsaved changes in " + t.getText() + ", do you want to save them?",
                    ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            ButtonType response = alert.showAndWait().orElse(ButtonType.NO);
            if (response == ButtonType.NO || (response == ButtonType.YES && this.save(this.getApplicationPartController(t), true))) {
                applicationPartControllers.remove(t);
                continue;
            }

            // allow cancellation
            cancelled = response == ButtonType.CANCEL;
            Platform.runLater(() -> tabPane.getTabs().add(t));
        }
    }
}
