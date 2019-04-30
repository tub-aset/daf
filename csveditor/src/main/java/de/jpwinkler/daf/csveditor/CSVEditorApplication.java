package de.jpwinkler.daf.csveditor;

import de.jpwinkler.daf.csveditor.util.ExceptionDialog;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jpwinkler.daf.doorscsv.util.CSVParseException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class CSVEditorApplication extends Application {

    private static final Logger LOGGER = Logger.getLogger(CSVEditorApplication.class.getName());

    private CSVEditorController csvEditorController;

    private Stage primaryStage;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        final FXMLLoader loader = new FXMLLoader(CSVEditorApplication.class.getResource("csveditor.fxml"));
        final Parent root = loader.load();

        csvEditorController = loader.getController();
        csvEditorController.setStage(primaryStage);

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
                        csvEditorController.newTabFromFile(file);
                    } catch (final Exception e) {
                        LOGGER.log(Level.SEVERE, e.getMessage(), e);
                    }
                }
                event.setDropCompleted(true);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("CSV Editor");
        primaryStage.show();
    }

    public void openFile(final File file) {
        if (file != null) {
            try {
                csvEditorController.newTabFromFile(file);
            } catch (IOException | CSVParseException e) {
                ExceptionDialog.showExceptionDialog(e);
            }
        }

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
