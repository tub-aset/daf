package de.jpwinkler.daf.csveditor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusListener;
import de.jpwinkler.daf.csveditor.background.BackgroundTaskStatusMonitor;
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

    private final FileChooser chooser = new FileChooser();

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
    private TreeMap<Long, File> recentFiles = new TreeMap<>();
    private final int MAX_RECENT_FILES = 10;

    @FXML
    public void initialize() {
        chooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));

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
                        if ((saveConfirm.isPresent() && saveConfirm.get() == ButtonType.YES && controller.save() == null)
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
        final File selectedFile = chooser.showOpenDialog(tabPane.getScene().getWindow());
        if (selectedFile != null) {
            chooser.setInitialDirectory(selectedFile.getParentFile());
            openFile(selectedFile);
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

        try {
            final FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("FilePane.fxml"));
            final Parent filePane = loader.load();

            final FilePaneController controller = loader.getController();
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
        if (selectedFile == null) {
            return;
        }

        recentFiles.values().remove(selectedFile);
        recentFiles.put(new Date().getTime(), selectedFile);
        while (recentFiles.size() > MAX_RECENT_FILES) {
            recentFiles.remove(recentFiles.firstKey());
        }

        recentMenu.getItems().clear();

        for (File recentFile : recentFiles.descendingMap().values()) {
            MenuItem recentMenuItem = new MenuItem(recentFile.getName());
            recentMenuItem.setUserData(recentFile);
            recentMenu.getItems().add(recentMenuItem);
            recentMenuItem.setOnAction(ev -> this.openFile((File) ((MenuItem) ev.getTarget()).getUserData()));
        }
    }

    @FXML
    public void saveClicked() {
        addToRecentMenu(getCurrentFileStateController().save());
        updateTabTitle(tabPane.getSelectionModel().getSelectedItem());

    }

    @FXML
    public void saveAsClicked() {
        addToRecentMenu(getCurrentFileStateController().saveAs());
        updateTabTitle(tabPane.getSelectionModel().getSelectedItem());
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
