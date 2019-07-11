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

import de.jpwinkler.daf.db.DatabaseFactory;
import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;

/**
 *
 * @author fwiesweg
 */
public class NewProjectCommand extends AbstractCommand {

    private final DatabaseFactory factory;
    private final DoorsTreeNode parent;

    private DoorsTreeNode newProject;

    public NewProjectCommand(DatabaseFactory factory, DoorsTreeNode parent) {
        this.factory = factory;
        this.parent = parent;
    }

    @Override
    public String getName() {
        return "New project";
    }

    @Override
    public boolean isApplicable() {
        return parent != null && !(parent instanceof DoorsModule) && !(parent instanceof DoorsObject);
    }

    @Override
    public void apply() {
        newProject = factory.createFolder(null, "New project", true);
        redo();
    }

    @Override
    public void redo() {
        parent.getChildren().add(parent.getChildren().indexOf(parent) + 1, newProject);
    }

    @Override
    public void undo() {
        parent.getChildren().remove(newProject);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return DatabasePaneController.UpdateTreeItem(parent).asArray();
    }
}
