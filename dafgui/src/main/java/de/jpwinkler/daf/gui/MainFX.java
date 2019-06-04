package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabasePath;
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
                        .map(f -> new DatabasePath(ApplicationPart.LOCAL_MODULE.getDatabaseInterfaceClass(), f.getAbsolutePath(), null))
                        .forEach(p -> applicationPaneController.open(p, DatabaseInterface.OpenFlag.OPEN_ONLY));
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
