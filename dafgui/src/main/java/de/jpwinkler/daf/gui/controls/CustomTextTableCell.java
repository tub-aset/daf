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
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author fwiesweg
 */
public class CustomTextTableCell<T> extends TableCell<T, T> {

    private final boolean multiline;
    private final Function<T, String> toStringFun;
    private final BiFunction<T, String, Boolean> editCommand;

    private TextInputControl textInput;
    private boolean editAllowed = false;

    public CustomTextTableCell(TableColumn<T, T> tc, Function<T, String> toString, BiFunction<T, String, Boolean> editCommand, boolean multiline) {
        this(tc, toString, editCommand, (x, y) -> {
        }, multiline);
    }

    public CustomTextTableCell(TableColumn<T, T> tc, Function<T, String> toString, BiFunction<T, String, Boolean> editCommand, BiConsumer<CustomTextTableCell<T>, T> opener, boolean multiline) {
        tc.setCellValueFactory((it) -> new ReadOnlyObjectWrapper<>(it.getValue()));
        this.toStringFun = toString;
        this.editCommand = editCommand;
        this.multiline = multiline;

        this.addEventFilter(MouseEvent.MOUSE_CLICKED, (eh) -> {
            if (!this.isEditing() && eh.getClickCount() >= 2 && eh.getButton() == MouseButton.PRIMARY) {
                editAllowed = true;
                super.getTableView().edit(this.getTableRow().getIndex(), this.getTableColumn());
                editAllowed = false;
                eh.consume();
            } else if (!this.isEditing() && eh.getClickCount() == 1 && eh.getButton() == MouseButton.SECONDARY && this.getItem() != null) {
                opener.accept(this, this.getItem());
                eh.consume();
            }
        });

        this.setTooltip(new Tooltip() {
            @Override
            protected void show() {
                String originalString = CustomTextTableCell.this.getText();
                Text textNode = (Text) CustomTextTableCell.this.lookup(".text"); // "text" is the style class of Text
                String actualString = textNode.getText();

                boolean clipped = !actualString.isEmpty() && !originalString.equals(actualString);
                if (clipped) {
                    this.setText(originalString);
                    super.show();
                }
            }
        });

    }

    protected String getDisplayedItemText(T item, boolean empty) {
        return getItemText();
    }

    protected String getDisplayedStyle(T item, boolean empty) {
        return "";
    }

    private String getItemText() {
        return toStringFun.apply(this.getItem());
    }

    private TextInputControl createTextInput() {
        TextInputControl textInput;
        if (multiline) {
            TextArea textArea = new TextArea(getItemText());
            textArea.setWrapText(true);
            textInput = textArea;
        } else {
            textInput = new TextField(getItemText());
        }

        textInput.addEventFilter(KeyEvent.KEY_PRESSED, t -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                this.cancelEdit();
                t.consume();
            } else if (t.getCode() == KeyCode.ENTER && t.isShiftDown()) {
                textInput.insertText(textInput.getCaretPosition(), "\n");
                t.consume();
            } else if (t.getCode() == KeyCode.ENTER) {
                T it = CustomTextTableCell.this.getItem();
                String text = textInput.getText();
                textInput.setText(text);
                if (!Objects.equals(text, getItemText()) && editCommand.apply(it, text)) {
                    this.commitEdit(it);
                } else {
                    this.cancelEdit();
                }
                t.consume();
            }
        });
        return textInput;
    }

    @Override
    public final void startEdit() {
        if (!editAllowed || !isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable()) {
            return;
        }

        super.startEdit();

        if (isEditing()) {
            if (textInput == null) {
                textInput = createTextInput();
            }

            textInput.setText(getItemText());

            this.setText(null);
            this.setGraphic(textInput);

            Node text = textInput.lookup(".text");
            if (text != null) {
                textInput.prefHeightProperty().bind(Bindings.createDoubleBinding(() -> text.getBoundsInLocal().getHeight(), text.boundsInLocalProperty()).add(20));
            }

            textInput.selectAll();
            textInput.requestFocus();
        }
    }

    @Override
    public final void cancelEdit() {
        super.cancelEdit();
        this.setText(getDisplayedItemText(getItem(), false));
        this.setGraphic(null);
    }

    @Override
    public final void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        this.setStyle(getDisplayedStyle(item, empty));

        if (this.isEmpty()) {
            this.setText(null);
            this.setGraphic(null);

        } else {
            if (this.isEditing()) {
                if (textInput != null) {
                    textInput.setText(getItemText());
                }
                this.setText(null);
                this.setGraphic(textInput);
            } else {
                this.setText(getDisplayedItemText(item, empty));
                this.setGraphic(null);
            }
        }
    }
}
