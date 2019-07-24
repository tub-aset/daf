package de.jpwinkler.daf.gui.modules;

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
import de.jpwinkler.daf.db.ModuleCSV;
import de.jpwinkler.daf.gui.ApplicationPartInterface;
import de.jpwinkler.daf.gui.controls.CombinedTextHeadingCell;
import de.jpwinkler.daf.gui.controls.CustomTextTableCell;
import de.jpwinkler.daf.gui.controls.LinksTableCell;
import de.jpwinkler.daf.gui.modules.commands.EditLinksCommand;
import de.jpwinkler.daf.gui.modules.commands.EditObjectAttributeCommand;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import javafx.scene.control.Cell;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.util.Callback;

public class ViewDefinition implements Serializable {

    public ViewDefinition(String name) {
        this.name = name;
    }

    private String name;
    private final List<ColumnDefinition> columns = new ArrayList<>();
    private final HashMap<String, Double> remainingColumnsWidths = new HashMap<>();
    private boolean displayRemainingColumns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ColumnDefinition> getColumns() {
        return columns;
    }

    public boolean isDisplayRemainingColumns() {
        return displayRemainingColumns;
    }

    public void setDisplayRemainingColumns(boolean displayRemainingColumns) {
        this.displayRemainingColumns = displayRemainingColumns;
    }

    public HashMap<String, Double> getRemainingColumnsWidths() {
        return remainingColumnsWidths;
    }

    @Override
    public String toString() {
        return name;
    }

    public ViewDefinition copy() {
        ViewDefinition copy = new ViewDefinition(name);
        copy.setDisplayRemainingColumns(displayRemainingColumns);
        copy.setName(name);
        this.getColumns().stream()
                .map(c -> c.copy())
                .forEach(copy.getColumns()::add);
        return copy;
    }

    public static enum ColumnType {
        ATTRIBUTE(true, (cd, tc, i) -> {
            BiFunction<DoorsObject, String, Boolean> edit = (it, newValue) -> i.executeCommand(new EditObjectAttributeCommand(it, cd.getAttributeName(), newValue));
            CustomTextTableCell<DoorsObject> cell = new CustomTextTableCell<>(tc, it -> it.getAttributes().get(cd.getAttributeName()), edit, true);
            cell.setContextMenu(new ContextMenu(createLinksContextMenu(cell)));
            return cell;
        }),
        COMBINED_TEXT_HEADING(false, (cd, tc, i) -> {
            BiFunction<DoorsObject, String, Boolean> edit = (it, newValue) -> i.executeCommand(new EditObjectAttributeCommand(it, it.isHeading() ? DoorsAttributes.OBJECT_HEADING.getKey() : DoorsAttributes.OBJECT_TEXT.getKey(), newValue));
            CustomTextTableCell<DoorsObject> cell = new CombinedTextHeadingCell<>(tc, edit);
            cell.setContextMenu(new ContextMenu(createLinksContextMenu(cell)));
            return cell;
        }),
        LINKS(false, (cd, tc, i) -> {
            BiFunction<DoorsObject, String, Boolean> edit = (it, newValue) -> i.executeCommand(new EditLinksCommand(it, ModuleCSV.parseLinks(newValue, i.getDatabaseInterface().getFactory(), it).collect(Collectors.toList())));
            return new LinksTableCell<>(tc, edit, i);
        });

        private final QuadFunction<ColumnDefinition, TableColumn<DoorsObject, DoorsObject>, ApplicationPartInterface, TableCell<DoorsObject, DoorsObject>> cellCreator;
        private final boolean usesAttributeName;

        private ColumnType(boolean usesAttributeName,
                QuadFunction<ColumnDefinition, TableColumn<DoorsObject, DoorsObject>, ApplicationPartInterface, TableCell<DoorsObject, DoorsObject>> cellCreator) {
            this.usesAttributeName = usesAttributeName;
            this.cellCreator = cellCreator;
        }

        public boolean isUsesAttributeName() {
            return usesAttributeName;
        }

        private TableCell<DoorsObject, DoorsObject> createCell(ColumnDefinition colDef, TableColumn<DoorsObject, DoorsObject> col, ApplicationPartInterface appPartInterface) {
            return this.cellCreator.apply(colDef, col, appPartInterface);
        }
    }

    public static MenuItem[] createLinksContextMenu(Cell<? extends DoorsTreeNode> cell) {
        MenuItem copyMenuItem = new MenuItem("Copy link to clipboard");
        copyMenuItem.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            clipboard.setContent(Collections.singletonMap(DataFormat.PLAIN_TEXT, cell.getItem().asLink()));
        });

        return new MenuItem[]{copyMenuItem};
    }

    public static interface QuadFunction<A, B, C, E> {

        E apply(A a, B b, C c);
    }

    public static class ColumnDefinition implements Serializable {

        public ColumnDefinition(ColumnType columnType, String title) {
            this.title = title;
            this.width = 150;
            this.visible = true;
            this.columnType = columnType;
        }

        private String title;
        private ColumnType columnType;

        private String attributeName;
        private double width;
        private boolean visible;

        public String getTitle() {
            return title;
        }

        public void setTitle(final String title) {
            this.title = title;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(final double width) {
            this.width = width;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(final boolean visible) {
            this.visible = visible;
        }

        public ColumnType getColumnType() {
            return columnType;
        }

        public void setColumnType(ColumnType columnType) {
            this.columnType = columnType;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(final String attributeName) {
            this.attributeName = attributeName;
        }

        @Override
        public String toString() {
            return title;
        }

        public Callback<TableColumn<DoorsObject, DoorsObject>, TableCell<DoorsObject, DoorsObject>> getCellFactory(ApplicationPartInterface appPartInterface) {
            return c -> {
                return this.columnType.createCell(this, c, appPartInterface);
            };

        }

        private ColumnDefinition copy() {
            ColumnDefinition copy = new ColumnDefinition(columnType, title);
            copy.setAttributeName(attributeName);
            copy.setVisible(visible);
            copy.setWidth(width);
            return copy;
        }

    }
}
