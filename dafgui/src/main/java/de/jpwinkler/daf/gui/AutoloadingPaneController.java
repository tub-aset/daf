package de.jpwinkler.daf.gui;

/*-
 * #%L
 * dafgui
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
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

    @SuppressWarnings("unchecked")
    public Optional<DialogResult> showDialog(Window owner, String title, ButtonType... buttonTypes) {
        Optional<ButtonType> result = asDialog(owner, title, buttonTypes).showAndWait();
        this.onClose(result);
        return result.map(bt -> new DialogResult(bt, (THIS) this));
    }

    public Dialog<ButtonType> asDialog(Window owner, String title, ButtonType... buttonTypes) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setResizable(true);
        dialog.setTitle(title);

        dialog.getDialogPane().setContent(this.getNode());
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypes);

        dialog.setResultConverter(bt -> bt);
        return dialog;
    }

    @SuppressWarnings("unchecked")
    public final DialogResult resultOf(ButtonType buttonType) {
        return new DialogResult(buttonType, (THIS) this);
    }

    protected void onClose(Optional<ButtonType> closeButton) {
    }

    public final class DialogResult {

        private DialogResult(ButtonType buttonType, THIS result) {
            this.buttonType = buttonType;
            this.result = result;
        }

        public final ButtonType buttonType;
        public final THIS result;
    }

}
