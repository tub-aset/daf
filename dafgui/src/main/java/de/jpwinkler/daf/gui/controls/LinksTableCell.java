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
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsLinkStatus;
import de.jpwinkler.daf.model.DoorsObject;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;

/**
 *
 * @author fwiesweg
 */
public class LinksTableCell<T extends DoorsObject> extends CustomTextAreaTableCell<T> {

    public LinksTableCell(TableColumn<T, T> tc, BiConsumer<T, String> editCommand, Consumer<DoorsLink> linkOpener) {
        super(tc, it -> it.getOutgoingLinks().stream()
                .map(ol -> ol.getTargetModule() + ":" + ol.getTargetObject())
                .collect(Collectors.joining("\n")),
                editCommand, (cell, it) -> {
                });

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(new MenuItem("test"));
        this.setContextMenu(contextMenu);

        contextMenu.setOnShowing(ev -> {
            contextMenu.getItems().clear();
            this.getItem().getOutgoingLinks()
                    .stream()
                    .map(ol -> {
                        MenuItem mi = new MenuItem("Go to " + ol.getTargetModule() + ":" + ol.getTargetObject());
                        mi.setOnAction(e -> {
                            linkOpener.accept(ol);
                            this.updateItem(getItem(), false);
                        });
                        return mi;
                    })
                    .forEach(contextMenu.getItems()::add);
        });
    }

    @Override
    public void updateItem(final T item, final boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            return;
        }

        this.setText(item.getOutgoingLinks().stream()
                .map(ol -> ol.getTargetModule() + ":" + ol.getTargetObject() + " (" + toDisplayString(ol.getLinkStatus()) + ")")
                .collect(Collectors.joining("\n")));
    }

    public static String toDisplayString(DoorsLinkStatus linkStatus) {
        switch (linkStatus) {
            case UNRESOLVED:
                return "unresolved";
            case RESOLVED:
                return "resolved";
            case RESOLVE_FAILED:
                return "not found";
            default:
                throw new AssertionError(linkStatus.name());

        }
    }

}
