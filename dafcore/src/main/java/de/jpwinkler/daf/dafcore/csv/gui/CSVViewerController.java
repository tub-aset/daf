package de.jpwinkler.daf.dafcore.csv.gui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
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
    }

    @FXML
    public void openClicked() {
        final File selectedFile = chooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            chooser.setInitialDirectory(selectedFile.getParentFile());
            try {
                final FXMLLoader loader = new FXMLLoader();
                loader.setLocation(new File("view/csvviewertab.fxml").toURI().toURL());
                final Parent root = loader.load();
                final CSVViewerTabController controller = loader.getController();
                final Tab tab = new Tab(selectedFile.getName(), root);

                controller.setFile(selectedFile);
                controller.setMainApp(csvViewerApplication);
                controller.setStage(primaryStage);
                controller.setTab(tab);
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
                tabControllers.put(tab, controller);
            } catch (IOException | CSVParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    @FXML
    public void saveClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        tabControllers.get(selectedTab).save();
    }

    @FXML
    public void closeClicked() {
        final Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        tabPane.getTabs().remove(selectedTab);
        tabControllers.remove(selectedTab);
    }

    @FXML
    public void exitClicked() {
    }

    public void setMainApp(final CSVViewerApplication csvViewerApplication) {
        this.csvViewerApplication = csvViewerApplication;
    }

    public void setStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
