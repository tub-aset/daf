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
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.gui.ApplicationPartInterface;
import de.jpwinkler.daf.model.DoorsLinkStatus;
import de.jpwinkler.daf.model.DoorsObject;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;

/**
 *
 * @author fwiesweg
 */
public class LinksTableCell<T extends DoorsObject> extends CustomTextTableCell<T> {

    public LinksTableCell(TableColumn<T, T> tc, BiFunction<T, String, Boolean> editCommand, ApplicationPartInterface appPartInterface) {
        super(tc, it -> it.getOutgoingLinks().stream()
                .map(ol -> ol.getTargetModule() + ":" + ol.getTargetObject())
                .collect(Collectors.joining("\n")),
                editCommand, (cell, it) -> {
                }, true);

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(new MenuItem("test"));
        this.setContextMenu(contextMenu);

        contextMenu.setOnShowing(ev -> {
            contextMenu.getItems().clear();
            this.getItem().getOutgoingLinks()
                    .stream()
                    .map(ol -> {
                        MenuItem mi = new MenuItem(("Go to " + ol.getTargetModule() + ":" + ol.getTargetObject()).trim());
                        mi.setMnemonicParsing(false);
                        mi.setOnAction(e -> {
                            appPartInterface.open(ol, DatabaseInterface.OpenFlag.OPEN_ONLY).whenComplete((ob, ex) -> Platform.runLater(() -> this.updateItem(getItem(), false)));
                        });
                        return mi;
                    })
                    .forEach(contextMenu.getItems()::add);
        });
        this.setWrapText(false);
        this.setStyle("-fx-text-overrun: center-ellipsis");
    }

    @Override
    protected String getDisplayedItemText(T item, boolean empty) {
        if (item == null || empty) {
            return "";
        }

        return item.getOutgoingLinks().stream()
                .map(ol -> toDisplayString(ol.getLinkStatus()) + " " + ol.getTargetModule() + ":" + ol.getTargetObject())
                .collect(Collectors.joining("\n"));
    }

    public static String toDisplayString(DoorsLinkStatus linkStatus) {
        switch (linkStatus) {
            case UNRESOLVED:
                return "?";
            case RESOLVED:
                return "✔";
            case RESOLVE_FAILED:
                return "‼";
            default:
                throw new AssertionError(linkStatus.name());

        }
    }

}
