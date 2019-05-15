package de.jpwinkler.daf.csveditor;

import de.jpwinkler.daf.csv.ModuleCSVParser;
import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusListener;
import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusMonitor;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsModule;
import java.io.File;
import java.io.IOException;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ApplicationPaneController {

    private final Map<Tab, ApplicationPartController> applicationPartControllers = new HashMap<>();

    private final FileChooser openFileChooser = new FileChooser();
    private final FileChooser saveFileChooser = new FileChooser();

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

    private final TreeMap<Long, File> recentFiles = ApplicationPreferences.RECENT_FILES.retrieve();
    private final int MAX_RECENT_FILES = 10;

    @FXML
    public void initialize() {
        openFileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
        saveFileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));

        openFileChooser.setInitialDirectory(ApplicationPreferences.OPEN_DIRECTORY.retrieve());
        saveFileChooser.setInitialDirectory(ApplicationPreferences.SAVE_DIRECTORY.retrieve());

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
    public void newClicked() {
        openFile(null);
    }

    @FXML
    public void openClicked() {
        final File f = openFileChooser.showOpenDialog(tabPane.getScene().getWindow());
        if (f != null) {
            openFileChooser.setInitialDirectory(f.getParentFile());
            ApplicationPreferences.OPEN_DIRECTORY.store(f.getParentFile().getAbsoluteFile());

            openFile(f);
        }
    }

    public void openFile(File selectedFile) {
        if (selectedFile != null) {
            selectedFile = selectedFile.getAbsoluteFile();
            File absFile = selectedFile;
            Tab fileTab = applicationPartControllers.entrySet().stream()
                    .filter(e -> absFile.equals(e.getValue().getFile()))
                    .findAny().map(e -> e.getKey()).orElse(null);

            if (fileTab != null) {
                addToRecentMenu(selectedFile);
                tabPane.getSelectionModel().select(fileTab);
                return;
            }
        }

        try {
            DoorsModule module;
            if (selectedFile != null) {
                module = new ModuleCSVParser().parseCSV(selectedFile);
            } else {
                module = DoorsFactory.eINSTANCE.createDoorsModule();
                module.setName(ModulePaneController.NEW_MODULE);
            }

            final ModulePaneController moduleController = new ModulePaneController(this, module);
            final Parent moduleParent = moduleController.getNode();

            final Tab selectedTab = new Tab(selectedFile != null ? selectedFile.getName() : ModulePaneController.NEW_MODULE, moduleParent);
            applicationPartControllers.put(selectedTab, moduleController);

            moduleController.getCommandStack().setOnDirty(dirty -> {
                File file = moduleController.getFile();
                selectedTab.setText((file != null ? file.getName() : ModulePaneController.NEW_MODULE) + (dirty ? " *" : ""));
            });

            selectedTab.setClosable(true);
            tabPane.getTabs().add(selectedTab);

            tabPane.getSelectionModel().select(selectedTab);
            addToRecentMenu(selectedFile);
        } catch (IOException ex) {
            setStatus("Open: Failed to open file; " + ex.getMessage());
        }
    }

    private void addToRecentMenu(File selectedFile) {
        if (selectedFile != null) {
            recentFiles.values().remove(selectedFile);
            recentFiles.put(new Date().getTime(), selectedFile);
        }
        while (recentFiles.size() > MAX_RECENT_FILES) {
            recentFiles.remove(recentFiles.firstKey());
        }
        ApplicationPreferences.RECENT_FILES.store(recentFiles);
        ApplicationPreferences.RECENT_FILES.retrieve();

        recentMenu.getItems().clear();

        for (File recentFile : recentFiles.descendingMap().values()) {
            MenuItem recentMenuItem = new MenuItem(recentFile.getName());
            recentMenuItem.setUserData(recentFile);
            recentMenu.getItems().add(recentMenuItem);
            recentMenuItem.setOnAction(ev -> this.openFile((File) ((MenuItem) ev.getTarget()).getUserData()));
        }
    }

    @FXML
    public boolean saveClicked() {
        return save(getCurrentFileStateController(), true);
    }

    private boolean save(ApplicationPartController fsc, boolean allowSaveAs) {
        File f = fsc.getFile();
        if (allowSaveAs && f == null) {
            return saveAs(fsc);
        } else if (f == null) {
            throw new IllegalStateException("No file set");
        }

        try {
            fsc.save();
        } catch (IOException ex) {
            this.setStatus("Save: Failed to save file; " + ex.getMessage());
            return false;
        }

        addToRecentMenu(f);
        return true;
    }

    @FXML
    public boolean saveAsClicked() {
        return saveAs(getCurrentFileStateController());
    }

    private boolean saveAs(ApplicationPartController fsc) {
        File f = fsc.getFile();
        if (f != null) {
            saveFileChooser.setInitialDirectory(f.getParentFile());
            saveFileChooser.setInitialFileName(f.getName());
            ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
        }

        f = saveFileChooser.showSaveDialog(tabPane.getScene().getWindow());
        if (f == null) {
            return false;
        }

        fsc.setFile(f);
        return this.save(fsc, false);
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
