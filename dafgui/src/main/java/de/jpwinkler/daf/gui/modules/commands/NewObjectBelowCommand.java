package de.jpwinkler.daf.gui.modules.commands;

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
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;

public class NewObjectBelowCommand extends AbstractCommand {

    private final DatabaseFactory factory;
    private final DoorsTreeNode parent;
    private DoorsObject newObject;

    public NewObjectBelowCommand(DatabaseFactory factory, DoorsTreeNode parent) {
        this.factory = factory;
        this.parent = parent;
    }

    @Override
    public boolean isApplicable() {
        return factory != null && parent != null;
    }
    
    

    @Override
    public String getName() {
        return "New Object Below";
    }

    @Override
    public void apply() {
        newObject = factory.createObject(parent, "New object");
        newObject.setParent(null);
        redo();
    }

    @Override
    public void undo() {
        parent.getChildren().remove(newObject);
    }

    @Override
    public void redo() {
        parent.getChildren().add(newObject);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW};
    }
}
