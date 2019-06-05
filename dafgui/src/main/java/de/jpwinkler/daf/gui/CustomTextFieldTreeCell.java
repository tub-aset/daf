/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

/**
 *
 * @author fwiesweg
 */
public class CustomTextFieldTreeCell<T> extends TextFieldTreeCell<T> {
    
    private boolean editAllowed = false;

    public CustomTextFieldTreeCell(Function<T, String> toString, BiConsumer<T, String> editCommand, Consumer<T> opener) {
        this.setConverter(new StringConverter<>() {
            @Override
            public String toString(T node) {
                return toString.apply(node);
            }

            @Override
            public T fromString(String newName) {
                T node = CustomTextFieldTreeCell.this.getItem();
                editCommand.accept(node, newName);
                return node;
            }
        });
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, (eh) -> {
            if (!this.isEditing() && eh.getClickCount() >= 2 && eh.getButton() == MouseButton.PRIMARY) {
                opener.accept(this.getItem());
                eh.consume();
            } else if (eh.getClickCount() == 1 && eh.getButton() == MouseButton.SECONDARY) {
                editAllowed = true;
                super.getTreeView().edit(this.getTreeItem());
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
