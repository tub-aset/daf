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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SplitLinesCommand extends AbstractCommand {

    private final DatabaseFactory factory;
    private final DoorsModule module;
    private final HashMap<DoorsObject, String[]> originalObjects = new HashMap<>();
    private final Set<DoorsObject> newObjects = new HashSet<>();

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
                        originalObjects.put(object, lines);
                    }
                }
                return true;
            }

        });
        redo();
    }

    @Override
    public void redo() {
        for (final Entry<DoorsObject, String[]> e : originalObjects.entrySet()) {
            DoorsObject object = e.getKey();
            int objectIndex = e.getKey().getParent().getChildren().indexOf(e.getKey());
            for (int i = e.getValue().length - 1; i >= 0; i--) {
                if (e.getValue()[i].trim().isEmpty()) {
                    continue;
                }

                DoorsObject newObject = i == 0 ? object : factory.createCopy(object, null, false);
                newObject.setText(e.getValue()[i]);

                if (i > 0) {
                    newObjects.add(newObject);
                    object.getParent().getChildren().add(objectIndex + 1, newObject);
                }
            }
        }
    }

    @Override
    public void undo() {
        for (final Entry<DoorsObject, String[]> e : originalObjects.entrySet()) {
            e.getKey().setText(Stream.of(e.getValue()).collect(Collectors.joining("\n")));
            e.getKey().getParent().getChildren().removeIf(newObjects::contains);
        }
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW};
    }

}
