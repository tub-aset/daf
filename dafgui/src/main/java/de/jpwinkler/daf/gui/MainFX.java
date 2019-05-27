package de.jpwinkler.daf.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainFX extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final ApplicationPaneController applicationPaneController = new ApplicationPaneController();
        final Scene applicationScene = new Scene(applicationPaneController.getNode());

        applicationScene.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });
        applicationScene.setOnDragDropped(event -> {
            if (event.getDragboard().hasFiles()) {
                event.getDragboard().getFiles().stream()
                        .map(f -> ApplicationPart.LOCAL_MODULE.newURI(f.getAbsolutePath()))
                        .forEach(applicationPaneController::open);
                event.setDropCompleted(true);
            }
        });

        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            applicationPaneController.closeAllPanes();
            
            if (applicationPaneController.hasOpenPanes()) {
                event.consume();
            }
        });
        primaryStage.setScene(applicationScene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Doors Access Framework – GUI");
        primaryStage.show();
    }
}
