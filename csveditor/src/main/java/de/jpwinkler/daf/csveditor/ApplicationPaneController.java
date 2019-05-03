package de.jpwinkler.daf.csveditor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusListener;
import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusMonitor;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ApplicationPaneController implements ApplicationStateController {

    private final Map<Tab, FileStateController> fileStateControllers = new HashMap<>();

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
            getFileStateController(oldValue).getMenus().forEach(m -> {
                mainMenuBar.getMenus().remove(m);
            });

            mainMenuBar.getMenus().addAll(getFileStateController(newValue).getMenus());
        });
        tabPane.getTabs().addListener((ListChangeListener<Tab>) (change) -> {
            List<Tab> toBeAdded = new ArrayList<>();

            while (change.next()) {
                for (Tab selectedTab : change.getRemoved()) {
                    final FileStateController controller = getFileStateController(selectedTab);
                    if (controller.isDirty()) {
                        final Optional<ButtonType> saveConfirm = new Alert(AlertType.CONFIRMATION, "There are unsaved changes, do you want to save them?",
                                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL).showAndWait();
                        if ((saveConfirm.isPresent() && saveConfirm.get() == ButtonType.YES && !this.saveClicked())
                                || (saveConfirm.isPresent() && saveConfirm.get() == ButtonType.CANCEL)
                                || !saveConfirm.isPresent()) {
                            toBeAdded.add(selectedTab);
                            continue;
                        }
                    }

                    fileStateControllers.remove(selectedTab);
                }
            }

            if (!toBeAdded.isEmpty()) {
                tabPane.getTabs().addAll(toBeAdded);
            }
        });
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

    private FileStateController getCurrentFileStateController() {
        return getFileStateController(tabPane.getSelectionModel().getSelectedItem());
    }

    private FileStateController getFileStateController(Tab selectedTab) {
        FileStateController controller = fileStateControllers.get(selectedTab);
        return controller == null ? FileStateController.empty() : controller;
    }

    @Override
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
            Tab fileTab = fileStateControllers.entrySet().stream()
                    .filter(e -> absFile.equals(e.getValue().getFile()))
                    .findAny().map(e -> e.getKey()).orElse(null);

            if (fileTab != null) {
                addToRecentMenu(selectedFile);
                tabPane.getSelectionModel().select(fileTab);
                return;
            }
        }

        final FilePaneController controller;
        final Parent filePane;
        try {
            final FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("FilePane.fxml"));
            filePane = loader.load();
            controller = loader.getController();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            controller.initialize(this, selectedFile);

            final Tab selectedTab = new Tab(selectedFile != null ? selectedFile.getName() : "New Document", filePane);
            fileStateControllers.put(selectedTab, controller);

            selectedTab.setClosable(true);
            tabPane.getTabs().add(selectedTab);
            updateTabTitle(selectedTab);

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
        File f = getCurrentFileStateController().getFile();
        if (f == null) {
            return saveAsClicked();
        }

        try ( FileOutputStream fos = new FileOutputStream(f)) {
            getCurrentFileStateController().save(fos);
        } catch (IOException ex) {
            this.setStatus("Open: Failed to save file; " + ex.getMessage());
            return false;
        }

        addToRecentMenu(f);
        updateTabTitle(tabPane.getSelectionModel().getSelectedItem());
        return true;
    }

    @FXML
    public boolean saveAsClicked() {
        File f = getCurrentFileStateController().getFile();
        if (f != null) {
            saveFileChooser.setInitialDirectory(f.getParentFile());
            saveFileChooser.setInitialFileName(f.getName());
            ApplicationPreferences.SAVE_DIRECTORY.store(f.getParentFile().getAbsoluteFile());
        }

        f = saveFileChooser.showSaveDialog(tabPane.getScene().getWindow());
        if (f == null) {
            return false;
        }

        getCurrentFileStateController().setFile(f);
        return this.saveClicked();
    }

    private void updateTabTitle(Tab selectedTab) {
        FileStateController fileStateController = getFileStateController(selectedTab);
        File file = fileStateController.getFile();
        selectedTab.setText((file != null ? file.getName() : "New Document") + (fileStateController.isDirty() ? " *" : ""));
    }

    @FXML
    public void closeClicked() {
        tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void exitClicked() {
        for (final Tab tab : new ArrayList<>(fileStateControllers.keySet())) {
            tabPane.getTabs().remove(tab);
        }

        if (!tabPane.getTabs().isEmpty()) {
            return;
        }

        Platform.exit();
    }
}
