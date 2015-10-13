package de.jpwinkler.daf.csveditor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class CSVEditorController {

    private static final Logger LOGGER = Logger.getLogger(CSVEditorController.class.getName());

    private Stage primaryStage;

    private CSVEditorApplication csvEditorApplication;

    private final Map<Tab, CSVEditorTabController> tabControllers = new HashMap<>();

    final FileChooser chooser = new FileChooser();

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField filterTextField;

    @FXML
    private CheckBox includeChildrenCheckbox;

    @FXML
    public void initialize() {
        chooser.setInitialDirectory(new File("C:/WORK/DOORS"));
        chooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
        filterTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                tabControllers.get(selectedTab).updateFilter(newValue, includeChildrenCheckbox.isSelected());
            }
        });
        includeChildrenCheckbox.selectedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                tabControllers.get(selectedTab).updateFilter(filterTextField.getText(), newValue);
            }
        });
    }

    @FXML
    public void openClicked() {
        final File selectedFile = chooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            chooser.setInitialDirectory(selectedFile.getParentFile());
            try {
                newTabFromFile(selectedFile);
            } catch (IOException | CSVParseException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }

        }
    }

    public void newTabFromFile(final File selectedFile) throws IOException, CSVParseException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("view/csveditortab.fxml").toURI().toURL());
        final Parent root = loader.load();
        final CSVEditorTabController controller = loader.getController();
        final Tab tab = new Tab(selectedFile != null ? selectedFile.getName() : "New Document", root);

        controller.setFile(selectedFile);
        controller.setMainApp(csvEditorApplication);
        controller.setStage(primaryStage);
        controller.setTab(tab);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        tabControllers.put(tab, controller);
    }

    @FXML
    public void saveClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).save();
        }
    }

    @FXML
    public void saveAsClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).saveAs();
        }
    }

    @FXML
    public void reduceToSelectionClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).reduceToSelection();
        }
    }

    @FXML
    public void closeClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            closeTab(selectedTab);
        }
    }

    private boolean closeTab(final Tab selectedTab) {
        final CSVEditorTabController controller = tabControllers.get(selectedTab);
        if (controller.isDirty()) {
            final Optional<ButtonType> saveConfirm = new Alert(AlertType.CONFIRMATION, "There are unsaved changes, what shall we save those?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL).showAndWait();
            if (saveConfirm.isPresent() && saveConfirm.get() == ButtonType.YES) {
                if (!controller.save()) {
                    return false;
                }
            } else if (saveConfirm.isPresent() && saveConfirm.get() == ButtonType.CANCEL) {
                return false;
            } else if (!saveConfirm.isPresent()) {
                return false;
            }
        }
        tabPane.getTabs().remove(selectedTab);
        tabControllers.remove(selectedTab);
        return true;
    }

    @FXML
    public void exitClicked() {
        for (final Tab tab : tabControllers.keySet()) {
            if (!closeTab(tab)) {
                return;
            }
        }
        Platform.exit();
    }

    @FXML
    public void addColumnClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).addColumn();
        }
    }

    @FXML
    public void swapObjectHeadingAndTextClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).swapObjectHeadingAndText();
        }
    }

    @FXML
    public void deleteObjectClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).deleteObject();
        }
    }

    @FXML
    public void unwrapChildrenClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).unwrapChildren();
        }
    }

    public void setMainApp(final CSVEditorApplication csvEditorApplication) {
        this.csvEditorApplication = csvEditorApplication;
    }

    public void setStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void newObjectAfterClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).newObjectAfter();
        }
    }

    @FXML
    public void newObjectBelowClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).newObjectBelow();
        }
    }

    @FXML
    public void promoteObjectClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).promoteObject();
        }
    }

    @FXML
    public void demoteObjectClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).demoteObject();
        }
    }

    @FXML
    public void newClicked() {
        try {
            newTabFromFile(null);
        } catch (IOException | CSVParseException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @FXML
    public void undoClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).undo();
        }
    }

    @FXML
    public void redoClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).redo();
        }
    }

    @FXML
    public void columnsClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).setupColumns();
        }
    }

    @FXML
    public void filterClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).setupFilter();
        }

    }

    @FXML
    public void showUntaggedClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).showUntagged();
        }
    }

    @FXML
    public void showOutlineClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).showOutline();
        }
    }

    @FXML
    public void cutClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).cut();
        }
    }

    @FXML
    public void copyClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).copy();
        }
    }

    @FXML
    public void pasteAfterClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).pasteAfter();
        }
    }

    @FXML
    public void pasteBelowClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            tabControllers.get(selectedTab).pasteBelow();
        }
    }
}