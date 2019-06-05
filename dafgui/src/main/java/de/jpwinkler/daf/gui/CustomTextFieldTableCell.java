/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

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
                opener.accept(this.getItem());
                eh.consume();
            } else if (eh.getClickCount() == 1 && eh.getButton() == MouseButton.SECONDARY) {
                editAllowed = true;
                super.getTableView().edit(this.getTableRow().getIndex(), this.getTableColumn());
                editAllowed = false;
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
