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

import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.List;

public class ReduceToSelectionCommand extends AbstractCommand {

    private final DoorsModule module;
    private final DoorsObject object;

    private int oldObjectIndex;
    private final List<DoorsTreeNode> moduleChildren = new ArrayList<>();
    private DoorsTreeNode oldParent;

    public ReduceToSelectionCommand(final DoorsModule module, final DoorsObject object) {
        this.module = module;
        this.object = object;
    }

    @Override
    public String getName() {
        return "Reduce to Selection";
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        moduleChildren.addAll(module.getChildren());
        oldParent = object.getParent();
        oldObjectIndex = oldParent.getChildren().indexOf(object);
        redo();
    }

    @Override
    public void undo() {
        module.getChildren().clear();
        module.getChildren().addAll(moduleChildren);
        oldParent.getChildren().add(oldObjectIndex, object);

    }

    @Override
    public void redo() {
        module.getChildren().clear();
        module.getChildren().add(object);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW};
    }
}
