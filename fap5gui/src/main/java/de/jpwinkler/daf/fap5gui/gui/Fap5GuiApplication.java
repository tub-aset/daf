package de.jpwinkler.daf.fap5gui.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import de.jpwinkler.daf.fap5gui.AnalysisRunner;
import de.jpwinkler.daf.fap5gui.model.AnalysisResults;
import de.jpwinkler.daf.fap5gui.model.AnalysisSettings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Fap5GuiApplication extends Application {

    private static final Logger LOGGER = Logger.getLogger(Fap5GuiApplication.class.getName());

    private static final File CACHE_FILE = new File("temp", "cache.json");

    private Stage primaryStage;
    private MainController mainController;

    @Override
    public void start(final Stage primaryStage) {

        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new File("view/main.fxml").toURI().toURL());
            final Parent root = loader.load();

            mainController = loader.getController();
            mainController.setMainApp(this);
            mainController.setStage(primaryStage);

            if (CACHE_FILE.exists()) {
                mainController.setResults(new Gson().fromJson(FileUtils.readFileToString(CACHE_FILE), AnalysisResults.class));
            }

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("FAP5 gui");
            primaryStage.show();
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    public void showAnalysisDialog() {

        try {
            final Stage dialogStage = new Stage();
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new File("view/analysis_dialog.fxml").toURI().toURL());
            final Parent root = loader.load();
            dialogStage.setTitle("Run Analysis");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            final Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            final AnalysisDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.loadSettings();
            controller.updateUI();
            dialogStage.showAndWait();
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    public static void main(final String[] args) {
        launch(args);
    }

    public void runAnalysis(final AnalysisSettings settings) {
        mainController.startAnalysis();
        new Thread(() -> {
            final AnalysisRunner batchRunner = new AnalysisRunner();
            try {
                final AnalysisResults results = batchRunner.run(CACHE_FILE, settings, (progress, progressText) -> {
                    Platform.runLater(() -> {
                        mainController.updateAnalysis(progress, progressText);
                    });
                });

                Platform.runLater(() -> {
                    mainController.setResults(results);
                    mainController.stopAnalysis();
                });
            } catch (final Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                Platform.runLater(() -> {
                    mainController.updateAnalysis(1, "Error: " + e.getMessage());
                    mainController.stopAnalysis();
                });
            }
        }).start();
    }
}
