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
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class ReduceToSelectionCommand extends AbstractCommand {

    private final DoorsModule module;
    private final Set<DoorsTreeNode> retainedObjects;
    private final LinkedHashMap<DoorsTreeNode, List<Pair<Integer, DoorsObject>>> removedNodes = new LinkedHashMap<>();

    public ReduceToSelectionCommand(final DoorsModule module, final Collection<DoorsObject> retainedObjects) {
        this.module = module;
        this.retainedObjects = new HashSet<>(retainedObjects);
    }

    @Override
    public String getName() {
        return "Reduce to Selection";
    }

    @Override
    public boolean isApplicable() {
        return retainedObjects.size() > 0;
    }

    @Override
    public void apply() {
        module.accept(new DoorsTreeNodeVisitor<DoorsObject>(DoorsObject.class) {
            @Override
            protected void visitPostTraverse(DoorsObject object) {
                if (!retainedObjects.contains(object)) {
                    if (!removedNodes.containsKey(object.getParent())) {
                        removedNodes.put(object.getParent(), new ArrayList<>());
                    }

                    removedNodes.get(object.getParent()).add(Pair.of(object.getParent().getChildren().indexOf(object), object));
                } else {
                    retainedObjects.add(object.getParent());
                }
            }
        });

        redo();
    }

    @Override
    public void undo() {
        removedNodes.forEach((parent, removedChildren) -> {
            removedChildren.forEach(p -> parent.getChildren().add(p.getLeft(), p.getRight()));
        });
    }

    @Override
    public void redo() {
        removedNodes.forEach((parent, removedChildren) -> {
            removedChildren.forEach(p -> parent.getChildren().remove(p.getRight()));
        });
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW};
    }
}
