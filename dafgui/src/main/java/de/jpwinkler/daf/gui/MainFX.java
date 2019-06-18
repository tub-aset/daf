package de.jpwinkler.daf.gui;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import de.jpwinkler.daf.gui.ApplicationPreferences.SerializableRectangle2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainFX extends Application {

    private Stage primaryStage;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        SvgImageLoaderFactory.install();
        this.primaryStage = primaryStage;

        final ApplicationPaneController applicationPaneController = new ApplicationPaneController();
        final Scene applicationScene = new Scene(applicationPaneController.getNode());

        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            applicationPaneController.closeRequest();

            if (!applicationPaneController.canClose()) {
                event.consume();
            }
        });

        SerializableRectangle2D storedRectangle = ApplicationPreferences.WINDOW_RECTANGLE.retrieve();
        if (storedRectangle != null && Screen.getScreens().stream().anyMatch(sc -> sc.getBounds().contains(storedRectangle.toRectangle2D()))) {
            primaryStage.setX(storedRectangle.getX());
            primaryStage.setY(storedRectangle.getY());
            primaryStage.setWidth(storedRectangle.getWidth());
            primaryStage.setHeight(storedRectangle.getHeight());
        }
        
        primaryStage.setMaximized(ApplicationPreferences.WINDOW_MAXIMIZED.retrieve());

        primaryStage.setScene(applicationScene);
        primaryStage.setTitle("Doors Access Framework – GUI");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        ApplicationPreferences.WINDOW_MAXIMIZED.store(primaryStage.isMaximized());
        ApplicationPreferences.WINDOW_RECTANGLE.store(new ApplicationPreferences.SerializableRectangle2D(
                primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight()));
    }

}
