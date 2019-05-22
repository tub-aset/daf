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

/**
 *
 * @author fwiesweg
 */
public class RemoveTagCommand extends AbstractCommand {

    private final DoorsTreeNode node;
    private final String tag;

    public RemoveTagCommand(DoorsTreeNode node, String tag) {
        this.node = node;
        this.tag = tag;
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
        node.removeTag(tag);
    }

    @Override
    public void undo() {
        node.setTag(tag);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateTagsSection, DatabasePaneController.UpdateAttributesView);
    }
}
