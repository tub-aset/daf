/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import de.jpwinkler.daf.filter.model.FilteredDoorsTreeNode;
import java.io.IOException;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Region;
import javafx.stage.Window;

/**
 *
 * @author fwiesweg
 */
public class ExpressionGrammarDialog {

    public static void showDialog(Window parent) {
        try {
            MultiLineTextInputDialog controller = new MultiLineTextInputDialog(FilteredDoorsTreeNode.getGrammar());
            Dialog<ButtonType> dialog = controller.asDialog(parent, "Expression grammar", ButtonType.OK);
            controller.getTextArea().setEditable(false);
            dialog.setHeaderText("Expression grammar");
            dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            dialog.getDialogPane().setMinWidth(800);
            dialog.showAndWait();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
