package de.jpwinkler.daf.gui.databases.commands;

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

import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.model.DoorsTreeNode;

/**
 *
 * @author fwiesweg
 */
public class RenameNodeCommand extends AbstractCommand {

    private final DoorsTreeNode node;
    private String oldName;
    private final String newName;

    public RenameNodeCommand(DoorsTreeNode node, String newName) {
        this.node = node;
        this.newName = newName;
    }

    @Override
    public String getName() {
        return "Rename node";
    }

    @Override
    public boolean isApplicable() {
        return node != null;
    }

    @Override
    public void apply() {
        this.oldName = node.getName();
        this.redo();
    }

    @Override
    public void redo() {
        node.setName(newName);
    }

    @Override
    public void undo() {
        node.setName(oldName);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateTreeView, DatabasePaneController.UpdateModulesView, DatabasePaneController.UpdateNodeTitle);
    }

}
