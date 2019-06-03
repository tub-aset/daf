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
import java.util.Collection;

/**
 *
 * @author fwiesweg
 */
public class RemoveTagCommand extends AbstractCommand {

    private final DoorsTreeNode node;
    private final Collection<String> tags;

    public RemoveTagCommand(DoorsTreeNode node, Collection<String> tags) {
        this.node = node;
        this.tags = tags;
    }

    @Override
    public String getName() {
        return "Remove Tag";
    }

    @Override
    public void apply() {
        this.redo();
    }
    
    @Override
    public boolean isApplicable() {
        return node != null;
    }

    @Override
    public void redo() {
        tags.forEach(node::removeTag);
    }

    @Override
    public void undo() {
        tags.forEach(node::setTag);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateTagsView, DatabasePaneController.UpdateAttributesView);
    }
}
