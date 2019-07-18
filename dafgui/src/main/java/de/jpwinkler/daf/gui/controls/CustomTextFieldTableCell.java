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
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

/**
 *
 * @author fwiesweg
 */
public class CustomTextFieldTableCell<T> extends TextFieldTableCell<T, T> {

    private boolean editAllowed = false;

    public CustomTextFieldTableCell(TableColumn<T, T> tc, Function<T, String> toString, BiConsumer<T, String> editCommand) {
        this(tc, toString, editCommand, (x) -> {
        });
    }

    public CustomTextFieldTableCell(TableColumn<T, T> tc, Function<T, String> toString, BiConsumer<T, String> editCommand, Consumer<T> opener) {
        tc.setCellValueFactory((it) -> new ReadOnlyObjectWrapper<>(it.getValue()));
        setConverter(new StringConverter<T>() {
            @Override
            public String toString(T t) {
                return toString.apply(t);
            }

            @Override
            public T fromString(String string) {
                T it = CustomTextFieldTableCell.this.getItem();
                editCommand.accept(it, string);
                return it;
            }
        });
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, (eh) -> {
            if (!this.isEditing() && eh.getClickCount() >= 2 && eh.getButton() == MouseButton.PRIMARY) {
                editAllowed = true;
                super.getTableView().edit(this.getTableRow().getIndex(), this.getTableColumn());
                editAllowed = false;
                eh.consume();
            } else if (eh.getClickCount() == 1 && eh.getButton() == MouseButton.SECONDARY) {
                opener.accept(this.getItem());
                eh.consume();
            }
        });
    }

    @Override
    public void startEdit() {
        if (editAllowed) {
            super.startEdit();
        }
    }

}
