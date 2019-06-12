/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases.commands;

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
public class AddTagCommand extends AbstractCommand {

    private final DoorsTreeNode node;
    private final Collection<String> tag;
    private final Set<String> previouslyKnownTags;
    private final Set<String> currentlyKnownTags;

    public AddTagCommand(DoorsTreeNode node, Collection<String> tag, Set<String> knownTags) {
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
