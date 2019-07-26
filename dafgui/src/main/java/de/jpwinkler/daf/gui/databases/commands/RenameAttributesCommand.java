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

import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RenameAttributesCommand extends AttributesCommand {

    public RenameAttributesCommand(String oldKey, String newKey, DoorsTreeNode treeNode) {
        super(Collections.singletonMap(oldKey, newKey).entrySet(), treeNode);

    }

    public RenameAttributesCommand(List<Map.Entry<String, String>> attributes, DoorsTreeNode treeNode) {
        super(attributes, treeNode);
    }

    @Override
    public String getName() {
        return "Rename Attributes";
    }

    @Override
    public void redo() {
        super.attributes.stream()
                .forEach(e -> {
                    String value = super.treeNode.getAttributes().remove(e.getKey());
                    super.treeNode.getAttributes().put(e.getValue(), value);
                });
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateTagsView, DatabasePaneController.UpdateAttributesView, DatabasePaneController.RefreshModulesView);
    }

}
