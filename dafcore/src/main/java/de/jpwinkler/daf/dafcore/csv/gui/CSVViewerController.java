package de.jpwinkler.daf.dafcore.csv.gui;

import java.io.File;
import java.io.IOException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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

    @FXML
    private TabPane tabPane;

    @FXML
    public void openClicked() {
        csvViewerApplication.openModule();
    }

    @FXML
    public void closeClicked() {
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

    public void newTab(final DoorsModule module) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new File("view/csvviewertab.fxml").toURI().toURL());
            final Parent root = loader.load();
            final CSVViewerTabController controller = loader.getController();
            controller.setModule(module);
            controller.setMainApp(csvViewerApplication);
            controller.setStage(primaryStage);
            final Tab tab = new Tab(module.getName(), root);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
