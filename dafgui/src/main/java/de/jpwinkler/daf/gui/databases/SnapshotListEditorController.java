/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases;

import de.jpwinkler.daf.gui.AutoloadingPaneController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 *
 * @author fwiesweg
 */
public class SnapshotListEditorController<THIS extends SnapshotListEditorController> extends AutoloadingPaneController<THIS> {

    public SnapshotListEditorController(String initialText) {
        textArea.setText(initialText);
    }
    
    public String getText() {
        return textArea.getText();
    }
    
    @FXML
    private TextArea textArea;
    
}
