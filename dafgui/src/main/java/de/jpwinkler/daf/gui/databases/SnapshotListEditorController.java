package de.jpwinkler.daf.gui.databases;

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
