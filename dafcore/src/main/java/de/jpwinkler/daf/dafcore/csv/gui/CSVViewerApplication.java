package de.jpwinkler.daf.dafcore.csv.gui;

import java.io.File;
import java.io.IOException;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CSVViewerApplication extends Application {

    private CSVViewerController csvViewerController;
    private Stage primaryStage;
    private final FileChooser chooser = new FileChooser();

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new File("view/csvviewer.fxml").toURI().toURL());
            final Parent root = loader.load();

            csvViewerController = loader.getController();
            csvViewerController.setMainApp(this);
            csvViewerController.setStage(primaryStage);

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("CSV Viewer");
            primaryStage.show();
            chooser.setInitialDirectory(new File("C:/WORK/DOORS"));
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void openModule() {
        final File selectedFile = chooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            chooser.setInitialDirectory(selectedFile.getParentFile());
            try {
                final DoorsModule module = new ModuleCSVParser().parseCSV(selectedFile);

                csvViewerController.newTab(module);
            } catch (IOException | CSVParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
