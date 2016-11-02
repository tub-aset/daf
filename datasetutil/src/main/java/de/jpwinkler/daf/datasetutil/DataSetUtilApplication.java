package de.jpwinkler.daf.datasetutil;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DataSetUtilApplication extends Application {

    private static final Logger LOGGER = Logger.getLogger(DataSetUtilApplication.class.getName());

    @Override
    public void start(final Stage primaryStage) throws Exception {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/gui.fxml"));
            final Parent root = loader.load();

            final DataSetUtilController controller = loader.getController();
            controller.setStage(primaryStage);

            final Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("DataSet Util");
            primaryStage.show();
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }

}
