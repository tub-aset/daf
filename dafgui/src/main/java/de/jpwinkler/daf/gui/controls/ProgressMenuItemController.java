package de.jpwinkler.daf.gui.controls;

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
import de.jpwinkler.daf.gui.AutoloadingPaneController;
import de.jpwinkler.daf.gui.BackgroundTask;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 *
 * @author fwiesweg
 */
public class ProgressMenuItemController extends AutoloadingPaneController<ProgressMenuItemController> {

    private final BackgroundTask backgroundTask;

    public ProgressMenuItemController(BackgroundTask backgroundTask) {
        this.backgroundTask = backgroundTask;
    }

    public ProgressMenuItem asMenuItem() {
        return new ProgressMenuItem();
    }

    @FXML
    private Label nameLabel;

    @FXML
    private Label doneLabel;
    
    @FXML
    private Button cancelButton;

    @FXML
    private ProgressBar progressBar;
    
    @FXML
    private HBox hbox;

    @FXML
    public void cancelButtonClicked() {
        this.backgroundTask.getFuture().cancel(true);
    }

    public final class ProgressMenuItem extends CustomMenuItem {

        private ProgressMenuItem() {
            super(ProgressMenuItemController.this.getNode());
            this.update();
        }

        public BackgroundTask getBackgroundTask() {
            return backgroundTask;
        }

        public void update() {
            nameLabel.setText(backgroundTask.getName());

            Double currentProgress = backgroundTask.getCurrentProgress();
            if (currentProgress == null) {
                progressBar.setProgress(-1);
            } else {
                progressBar.setProgress(currentProgress);
            }

            if (backgroundTask.getTaskStatus() != BackgroundTask.TaskStatus.RUNNING) {
                ProgressMenuItemController.this.getNode().setDisable(true);
                doneLabel.setVisible(true);
                hbox.getChildren().remove(cancelButton);
                hbox.getChildren().remove(progressBar);
            }

            if (backgroundTask.getTaskStatus() == BackgroundTask.TaskStatus.DONE) {
                doneLabel.setText("Finished");
            } else if (backgroundTask.getTaskStatus() == BackgroundTask.TaskStatus.FAILED) {
                doneLabel.setText("Failed");
            }
        }
    }

}
