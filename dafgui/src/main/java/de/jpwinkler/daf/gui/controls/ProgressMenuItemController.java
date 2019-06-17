/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.controls;

import de.jpwinkler.daf.gui.AutoloadingPaneController;
import de.jpwinkler.daf.gui.BackgroundTask;
import javafx.fxml.FXML;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

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
    private Label statusLabel;

    @FXML
    private ProgressBar progressBar;

    public final class ProgressMenuItem extends CustomMenuItem {

        private ProgressMenuItem() {
            super(ProgressMenuItemController.this.getNode());
            this.update();
        }

        public BackgroundTask getBackgroundTask() {
            return backgroundTask;
        }

        public void update() {
            statusLabel.setText(backgroundTask.getName());

            Double currentProgress = backgroundTask.getCurrentProgress();
            if (currentProgress == null) {
                progressBar.setProgress(-1);
            } else {
                progressBar.setProgress(currentProgress);
            }

            if (backgroundTask.getTaskStatus() != BackgroundTask.TaskStatus.RUNNING) {
                ProgressMenuItemController.this.getNode().setDisable(true);
            }
        }
    }

}
