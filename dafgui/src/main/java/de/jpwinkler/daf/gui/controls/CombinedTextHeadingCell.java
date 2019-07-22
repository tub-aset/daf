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
import de.jpwinkler.daf.model.DoorsObject;
import java.util.function.BiFunction;
import javafx.scene.control.TableColumn;

/**
 *
 * @author fwiesweg
 */
public class CombinedTextHeadingCell<T extends DoorsObject> extends CustomTextTableCell<T> {

    public CombinedTextHeadingCell(TableColumn<T, T> tc, BiFunction<T, String, Boolean> editCommand) {
        super(tc, it -> it.getText(), editCommand, true);
    }

    @Override
    protected String getDisplayedItemText(T item, boolean empty) {
        if (item == null || empty) {
            return "";
        }

        return item.isHeading() ? item.getObjectNumber() + " " + item.getObjectHeading() : item.getObjectText();
    }

    @Override
    protected String getDisplayedStyle(T item, boolean empty) {
        if (item == null || empty) {
            return "";
        }

        String style = "-fx-padding: 0 0 0 " + (item.getObjectLevel() - 1) * 10 + ";";
        if (item.isHeading()) {
            style += "-fx-font-weight: bold;";
            if (item.getObjectLevel() <= 2) {
                style += "-fx-font-size: 140%;";
            } else if (item.getObjectLevel() == 3) {
                style += "-fx-font-size: 130%;";
            } else if (item.getObjectLevel() == 4) {
                style += "-fx-font-size: 120%;";
            } else if (item.getObjectLevel() == 5) {
                style += "-fx-font-size: 110%;";
            }
        }
        return style;
    }

}
