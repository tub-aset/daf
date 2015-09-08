package de.jpwinkler.daf.dafcore.csv.gui;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class CSVViewerApplication extends Application {

    private CSVViewerController csvViewerController;
    private Stage primaryStage;

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

            final Scene scene = new Scene(root);
            scene.setOnDragOver(event -> {
                if (event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            });
            scene.setOnDragDropped(event -> {
                if (event.getDragboard().hasFiles()) {
                    for (final File file : event.getDragboard().getFiles()) {
                        try {
                            csvViewerController.newTabFromFile(file);
                        } catch (final Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    event.setDropCompleted(true);
                }
            });
            primaryStage.setOnCloseRequest(event -> {
                csvViewerController.exitClicked();
                event.consume();
            });
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("CSV Viewer");
            primaryStage.show();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
