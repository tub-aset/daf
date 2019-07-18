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
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SplitLinesCommand extends AbstractCommand {

    private final DatabaseFactory factory;
    private final Map<DoorsObject, List<DoorsObject>> newObjects = new HashMap<>();
    private final DoorsModule module;

    public SplitLinesCommand(DatabaseFactory factory, DoorsModule module) {
        this.factory = factory;
        this.module = module;
    }

    @Override
    public boolean isApplicable() {
        return factory != null;
    }

    @Override
    public String getName() {
        return "Split lines";
    }

    @Override
    public void apply() {
        module.accept(new DoorsTreeNodeVisitor<DoorsObject, Void>(DoorsObject.class) {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                if (object.getText() != null) {
                    final String[] lines = object.getText().split("\n");
                    if (lines.length > 1) {
                        object.setText(lines[0]);
                        for (int i = lines.length - 1; i > 0; i--) {
                            newObjectAfter(object, i, lines[i]);
                        }
                    }
                }

                return true;
            }

        });
        redo();
    }

    @Override
    public void redo() {
        for (final Entry<DoorsObject, List<DoorsObject>> e : newObjects.entrySet()) {
            e.getKey().getParent().getChildren().addAll(e.getKey().getParent().getChildren().indexOf(e.getKey()), e.getValue());
        }
    }

    private void newObjectAfter(final DoorsObject object, final int i, final String string) {
        final DoorsObject newObject = factory.createCopy(object, object.getParent(), false);
        newObject.setObjectIdentifier(object.getObjectIdentifier() + "-" + i);
        newObject.setText(string);

        if (!newObjects.containsKey(object)) {
            newObjects.put(object, new ArrayList<>());
        }
        newObjects.get(object).add(newObject);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canUndo() {
        return false;
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW};
    }

}
