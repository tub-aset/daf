package de.jpwinkler.daf.csveditor;

import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusListener;
import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusMonitor;
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

public class ApplicationPaneController {

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

    private final TreeMap<Long, ApplicationURI> recentFiles = ApplicationPreferences.RECENT_FILES.retrieve();
    private final int MAX_RECENT_FILES = 10;

    @FXML
    public void initialize() {
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
        addToRecentMenu(null);

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

    private ApplicationPartController getCurrentFileStateController() {
        return getApplicationPartController(tabPane.getSelectionModel().getSelectedItem());
    }

    private ApplicationPartController getApplicationPartController(Tab selectedTab) {
        return applicationPartControllers.get(selectedTab);
    }

    public void setStatus(final String status) {
        statusBarLabel.setText(status);
    }

    @FXML
    public void newLocalModuleClicked() throws URISyntaxException {
        open(ApplicationPart.LOCAL_MODULE.newURI());

    }

    @FXML
    public void newLocalDatabaseClicked() throws URISyntaxException {
        open(ApplicationPart.LOCAL_DATABASE.newURI());

    }

    @FXML
    public void openLocalModuleClicked() throws URISyntaxException {
        ApplicationPart.LOCAL_MODULE.openWithSelector(this, tabPane.getScene().getWindow());
    }

    @FXML
    public void openLocalDatabaseClicked() throws URISyntaxException {
        ApplicationPart.LOCAL_DATABASE.openWithSelector(this, tabPane.getScene().getWindow());
    }

    @FXML
    public void openDoorsDatabaseClicked() throws URISyntaxException {
        ApplicationPart.DOORS_BRIDGE.openWithSelector(this, tabPane.getScene().getWindow());
    }

    public void open(ApplicationURI selectedURI) {
        Tab fileTab = applicationPartControllers.entrySet().stream()
                .filter(e -> selectedURI.equals(e.getValue().getURI()))
                .findAny().map(e -> e.getKey()).orElse(null);

        if (fileTab != null) {
            addToRecentMenu(selectedURI);
            tabPane.getSelectionModel().select(fileTab);
            return;
        }

        try {
            final ApplicationPartController controller = ApplicationPart.openAny(this, selectedURI);
            final Parent modulePane = controller.getNode();

            final Tab selectedTab = new Tab(controller.getURI().toString(), modulePane);
            applicationPartControllers.put(selectedTab, controller);

            controller.getCommandStack().setOnDirty(dirty -> {
                selectedTab.setText(controller.getURI().toString() + (dirty ? "*" : ""));
            });

            selectedTab.setClosable(true);
            tabPane.getTabs().add(selectedTab);

            tabPane.getSelectionModel().select(selectedTab);
            addToRecentMenu(controller.getURI());
        } catch (Exception ex) {
            setStatus("Open: Failed to open file; " + ex.getMessage());
        }
    }

    private void addToRecentMenu(ApplicationURI selectedUri) {
        if (selectedUri != null) {
            recentFiles.values().remove(selectedUri);
            recentFiles.put(new Date().getTime(), selectedUri);
        }
        while (recentFiles.size() > MAX_RECENT_FILES) {
            recentFiles.remove(recentFiles.firstKey());
        }
        ApplicationPreferences.RECENT_FILES.store(recentFiles);
        ApplicationPreferences.RECENT_FILES.retrieve();

        recentMenu.getItems().clear();

        for (ApplicationURI recentFile : recentFiles.descendingMap().values()) {
            MenuItem recentMenuItem = new MenuItem(recentFile.getPath().replaceFirst("^.*/", ""));
            recentMenuItem.setUserData(recentFile);
            recentMenu.getItems().add(recentMenuItem);
            recentMenuItem.setOnAction(ev -> this.open((ApplicationURI) ((MenuItem) ev.getTarget()).getUserData()));
        }
    }

    @FXML
    public void saveClicked() {
        save(getCurrentFileStateController(), true);
    }

    private boolean save(ApplicationPartController fsc, boolean allowSaveAs) {
        if (allowSaveAs && fsc.isValidFile()) {
            fsc.getURI().getApplicationPart().saveWithSelector(this, tabPane.getScene().getWindow()).forEach(uri -> {
                fsc.setURI(uri);
            });
        }

        if (fsc.isValidFile()) {
            return false;
        }

        try {
            fsc.save();
        } catch (IOException ex) {
            this.setStatus("Save: Failed to save file; " + ex.getMessage());
            return false;
        }

        addToRecentMenu(fsc.getURI());
        return true;
    }

    @FXML
    public void saveAsClicked() {
        saveAs(getCurrentFileStateController());
    }

    private void saveAs(ApplicationPartController fsc) {
        fsc.getURI().getApplicationPart().saveWithSelector(this, tabPane.getScene().getWindow()).forEach(uri -> {
            fsc.setURI(uri);
            this.save(fsc, false);
        });
    }

    @FXML
    public boolean closeClicked() {
        tabPane.getTabs().clear();
        return applicationPartControllers.isEmpty();
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
