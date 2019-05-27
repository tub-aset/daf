/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases.commands;

import de.jpwinkler.daf.gui.CommandStack;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.model.DoorsTreeNode;

/**
 *
 * @author fwiesweg
 */
public class RenameNodeCommand extends CommandStack.AbstractCommand {
    private DoorsTreeNode node;
    private String oldName;
    private String newName;
    
    public RenameNodeCommand(DoorsTreeNode node, String newName) {
        this.node = node;
        this.newName = newName;
    }

    @Override
    public String getName() {
        return "Rename node";
    }

    @Override
    public boolean isApplicable() {
        return node != null;
    }

    @Override
    public void apply() {
        this.oldName = node.getName();
        this.redo();
    }

    @Override
    public void redo() {
        node.setName(newName);
    }

    @Override
    public void undo() {
        node.setName(oldName);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return DatabasePaneController.RefreshTreeView.asArray();
    }
    
    
}
