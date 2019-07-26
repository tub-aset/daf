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

import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author fwiesweg
 */
public class NewTagCommand extends AbstractCommand {

    private final DoorsTreeNode node;
    private final Collection<String> tag;
    private final Set<String> previouslyKnownTags;
    private final Set<String> currentlyKnownTags;

    public NewTagCommand(DoorsTreeNode node, Collection<String> tag, Set<String> knownTags) {
        this.node = node;
        this.tag = tag;
        this.previouslyKnownTags = new HashSet<>(knownTags);
        this.currentlyKnownTags = knownTags;
    }

    @Override
    public String getName() {
        return "Add Tag";
    }

    @Override
    public boolean isApplicable() {
        return node != null && tag.stream().allMatch(t -> t != null);
    }

    @Override
    public void apply() {
        this.redo();
    }

    @Override
    public void redo() {
        tag.stream().forEach(node::setTag);
        tag.stream().forEach(currentlyKnownTags::add);
    }

    @Override
    public void undo() {
        tag.stream().forEach(node::removeTag);
        currentlyKnownTags.clear();
        currentlyKnownTags.addAll(previouslyKnownTags);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateTagsView, DatabasePaneController.UpdateAttributesView);
    }

}
