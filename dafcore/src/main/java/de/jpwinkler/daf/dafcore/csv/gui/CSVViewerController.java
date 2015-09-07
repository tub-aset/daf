package de.jpwinkler.daf.dafcore.csv.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class CSVViewerController {

    @FXML
    private MenuItem openMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    private Stage primaryStage;

    private CSVViewerApplication csvViewerApplication;

    private final Map<Tab, CSVViewerTabController> tabControllers = new HashMap<>();

    final FileChooser chooser = new FileChooser();

    @FXML
    private TabPane tabPane;

    @FXML
    public void initialize() {
        chooser.setInitialDirectory(new File("C:/WORK/DOORS"));
        chooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
    }

    @FXML
    public void openClicked() {
        final File selectedFile = chooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            chooser.setInitialDirectory(selectedFile.getParentFile());
            try {
                newTabFromFile(selectedFile);
            } catch (IOException | CSVParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public void newTabFromFile(final File selectedFile) throws MalformedURLException, IOException, CSVParseException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("view/csvviewertab.fxml").toURI().toURL());
        final Parent root = loader.load();
        final CSVViewerTabController controller = loader.getController();
        final Tab tab = new Tab(selectedFile != null ? selectedFile.getName() : "New Document", root);

        controller.setFile(selectedFile);
        controller.setMainApp(csvViewerApplication);
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
            tabPane.getTabs().remove(selectedTab);
            tabControllers.remove(selectedTab);
        }
    }

    @FXML
    public void exitClicked() {
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

    public void setMainApp(final CSVViewerApplication csvViewerApplication) {
        this.csvViewerApplication = csvViewerApplication;
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
            // TODO Auto-generated catch block
            e.printStackTrace();

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

}
