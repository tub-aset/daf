package de.jpwinkler.daf.doorsdbgui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DoorsDBBrowserApplication extends Application {

    private static final Logger LOGGER = Logger.getLogger(DoorsDBBrowserApplication.class.getName());

    private BrowserController browserController;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/browser.fxml"));
            final Parent root = loader.load();

            browserController = loader.getController();

            final Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Doors DB Browser");
            primaryStage.show();
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }

}
