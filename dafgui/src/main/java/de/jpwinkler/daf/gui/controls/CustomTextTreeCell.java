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
import java.util.function.BiFunction;
import java.util.function.Function;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.StringConverter;

/**
 *
 * @author fwiesweg
 */
public class CustomTextTreeCell<T> extends TextFieldTreeCell<T> {

    public CustomTextTreeCell(Function<T, String> toString, BiFunction<T, String, Boolean> editCommand) {
        this.setEditable(editCommand != null);

        this.setConverter(new StringConverter<T>() {
            @Override
            public String toString(T node) {
                return toString.apply(node);
            }

            @Override
            public T fromString(String newName) {
                editCommand.apply(CustomTextTreeCell.this.getItem(), newName);
                return CustomTextTreeCell.this.getItem();
            }
        });
    }
}
