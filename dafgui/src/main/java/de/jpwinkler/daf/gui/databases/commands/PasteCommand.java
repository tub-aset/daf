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
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PasteCommand extends AbstractCommand {

    private final DatabaseFactory factory;
    private final DoorsTreeNode parent;
    private final List<DoorsTreeNode> objectsToCopy;
            
    private List<DoorsTreeNode> copiedObjects;

    public PasteCommand(DatabaseFactory factory, DoorsTreeNode parent, List<DoorsTreeNode> objectsToCopy) {
        this.factory = factory;
        this.parent = parent;
        this.objectsToCopy = new ArrayList<>(objectsToCopy);
    }

    @Override
    public boolean isApplicable() {
        return factory != null && parent != null && !objectsToCopy.isEmpty();
    }

    @Override
    public String getName() {
        return "Paste Object";
    }

    @Override
    public void apply() {
        copiedObjects = objectsToCopy.stream()
                .map(o -> factory.createCopy(o, parent, false))
                .collect(Collectors.toList());
        redo();
    }

    @Override
    public void redo() {
        parent.getChildren().addAll(copiedObjects);
    }

    @Override
    public void undo() {
        parent.getChildren().removeAll(copiedObjects);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateTreeItem(parent), DatabasePaneController.UpdateModulesView);
    }

}
