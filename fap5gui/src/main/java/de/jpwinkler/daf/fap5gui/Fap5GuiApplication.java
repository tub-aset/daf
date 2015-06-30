package de.jpwinkler.daf.fap5gui;

import java.io.File;
import java.io.IOException;

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

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("FAP5 gui");
            primaryStage.show();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            dialogStage.showAndWait();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
                final AnalysisResults results = batchRunner.run(settings, (progress, progressText) -> {
                    Platform.runLater(() -> {
                        mainController.updateAnalysis(progress, progressText);
                    });
                });

                Platform.runLater(() -> {
                    mainController.setResults(results);
                    mainController.stopAnalysis();
                });
            } catch (final Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }
}
