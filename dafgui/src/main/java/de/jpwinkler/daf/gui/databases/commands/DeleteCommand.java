/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases.commands;

import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.extensions.AbstractCommand;
import de.jpwinkler.daf.gui.extensions.UpdateAction;
import de.jpwinkler.daf.model.DoorsTreeNode;

/**
 *
 * @author fwiesweg
 */
public class DeleteCommand extends AbstractCommand {

    private final DoorsTreeNode node;
    private final DoorsTreeNode parent;
    private int oldPos;

    public DeleteCommand(final DoorsTreeNode node) {
        this.node = node;
        this.parent = node.getParent();
    }

    @Override
    public String getName() {
        return "Delete";
    }

    @Override
    public boolean isApplicable() {
        return node != null && node.getParent() != null;
    }

    @Override
    public void apply() {
        redo();
    }

    @Override
    public void redo() {
        oldPos = parent.getChildren().indexOf(node);
        parent.getChildren().remove(oldPos);
    }

    @Override
    public void undo() {
        parent.getChildren().add(oldPos, node);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateTreeItem(parent), DatabasePaneController.UpdateModulesView);
    }
}
