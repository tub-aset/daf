/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases.commands;

import de.jpwinkler.daf.gui.CommandStack.AbstractCommand;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author fwiesweg
 */
public class AddTagCommand extends AbstractCommand {

    private final DoorsTreeNode node;
    private final String tag;
    private final Set<String> knownTags;

    public AddTagCommand(DoorsTreeNode node, String tag, Set<String> knownTags) {
        this.node = node;
        this.tag = tag;
        this.knownTags = knownTags.contains(tag) ? new HashSet<>(1) : knownTags;
    }

    @Override
    public String getName() {
        return "Add Tag";
    }

    @Override
    public boolean isApplicable() {
        return node != null;
    }

    @Override
    public void apply() {
        this.redo();
    }

    @Override
    public void redo() {
        node.setTag(tag);
        knownTags.add(tag);
    }

    @Override
    public void undo() {
        node.removeTag(tag);
        knownTags.remove(tag);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateTagsView, DatabasePaneController.UpdateAttributesView);
    }

}
