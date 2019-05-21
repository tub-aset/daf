package de.jpwinkler.daf.csveditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainFX extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("ApplicationPane.fxml"));
        final Scene applicationScene = new Scene(loader.load());
        final ApplicationPaneController applicationPaneController = loader.getController();

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
            if (!applicationPaneController.closeClicked()) {
                event.consume();
            }
        });
        primaryStage.setScene(applicationScene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("CSV Editor");
        primaryStage.show();
    }
}
