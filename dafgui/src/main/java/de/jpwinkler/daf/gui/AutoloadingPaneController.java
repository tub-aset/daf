/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 *
 * @author fwiesweg
 */
public abstract class AutoloadingPaneController<THIS extends AutoloadingPaneController> {

    private final Parent node;

    public AutoloadingPaneController() {
        try {
            final FXMLLoader paneLoader = new FXMLLoader(MainFX.class.getResource(
                    this.getClass().getSimpleName().replaceFirst("Controller$", "") + ".fxml"));
            paneLoader.setController(this);
            this.node = paneLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public final void initialize() {
        //  make sure nobody implements this since that's prone to error in combination
        // with loading the scene in the constructor
    }

    public Parent getNode() {
        return node;
    }

    public Optional<DialogResult> asDialog(Window owner, String title, ButtonType... buttonTypes) {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setResizable(true);
        dialog.setTitle(title);

        dialog.getDialogPane().setContent(this.getNode());
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypes);

        dialog.setResultConverter(bt -> bt);
        return dialog.showAndWait().map(bt -> new DialogResult(bt, (THIS) this));
    }

    public final class DialogResult {

        public DialogResult(ButtonType buttonType, THIS result) {
            this.buttonType = buttonType;
            this.result = result;
        }

        public final ButtonType buttonType;
        public final THIS result;
    }

}
