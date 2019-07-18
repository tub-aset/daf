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
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FlattenCommand extends AbstractCommand {

    private final DoorsModule module;

    public FlattenCommand(final DoorsModule module) {
        this.module = module;
    }

    private final List<DoorsObject> objectList = new ArrayList<>();

    private final Map<DoorsObject, DoorsTreeNode> oldParents = new LinkedHashMap<>();


    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public String getName() {
        return "Flatten";
    }

    @Override
    public void apply() {
        module.accept(new DoorsTreeNodeVisitor<DoorsObject, Void>(DoorsObject.class) {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                objectList.add(object);
                oldParents.put(object, object.getParent());
                return true;
            }
        });
        redo();
    }

    @Override
    public void redo() {
        for (final DoorsObject object : objectList) {
            object.getParent().getChildren().remove(object);
            module.getChildren().add(object);
        }
    }

    @Override
    public void undo() {
        for (final DoorsObject object : oldParents.keySet()) {
            object.setParent(oldParents.get(object));
        }
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }

}
