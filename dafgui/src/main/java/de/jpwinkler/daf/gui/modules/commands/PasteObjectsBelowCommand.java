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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PasteObjectsBelowCommand extends AbstractCommand {

    private final DatabaseFactory factory;
    private final DoorsObject reference;
    private final List<DoorsObject> objectsToCopy;
    private List<DoorsObject> copiedObjects;

    public PasteObjectsBelowCommand(DatabaseFactory factory, DoorsObject reference, List<DoorsObject> objectsToCopy) {
        this.factory = factory;
        this.reference = reference;
        this.objectsToCopy = new ArrayList<>(objectsToCopy);
    }

    @Override
    public boolean isApplicable() {
        return reference != null && !objectsToCopy.isEmpty();
    }

    @Override
    public String getName() {
        return "Copy Object";
    }

    @Override
    public void apply() {
        copiedObjects = objectsToCopy.stream()
                .map(o -> (DoorsObject) factory.createCopy(o, reference, false))
                .collect(Collectors.toList());
        redo();
    }

    @Override
    public void redo() {
        reference.getChildren().addAll(copiedObjects);
    }

    @Override
    public void undo() {
        reference.getChildren().removeAll(copiedObjects);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }

}
