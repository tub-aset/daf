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
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteObjectCommand extends AbstractCommand {

    private final List<DoorsObject> objects;
    private final Map<DoorsObject, DoorsTreeNode> parents = new HashMap<>();
    private final Map<DoorsObject, Integer> objectIndices = new HashMap<>();

    public DeleteObjectCommand(final List<DoorsObject> objects) {
        this.objects = new ArrayList<>(objects);
    }

    @Override
    public String getName() {
        return "Delete Object";
    }

    @Override
    public void apply() {
        for (final DoorsObject object : objects) {
            parents.put(object, object.getParent());
            objectIndices.put(object, object.getParent().getChildren().indexOf(object));
        }
        redo();
    }

    @Override
    public void undo() {
        for (final DoorsObject object : objects) {
            parents.get(object).getChildren().add(objectIndices.get(object), object);
        }
    }

    @Override
    public void redo() {
        for (final DoorsObject object : objects) {
            parents.get(object).getChildren().remove(object);
        }
    }

    @Override
    public boolean isApplicable() {
        return objects.stream().allMatch(o -> o != null && o.getParent() != null);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
