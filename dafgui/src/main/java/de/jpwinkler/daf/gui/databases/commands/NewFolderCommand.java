/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases.commands;

import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;

/**
 *
 * @author fwiesweg
 */
public class NewFolderCommand extends AbstractCommand {

    private final DoorsTreeNode parent;
    private DoorsTreeNode newFolder;

    public NewFolderCommand(final DoorsTreeNode parent) {
        this.parent = parent;
    }

    @Override
    public String getName() {
        return "New folder";
    }

    @Override
    public boolean isApplicable() {
        return parent != null && !(parent instanceof DoorsModule) && !(parent instanceof DoorsObject);
    }

    @Override
    public void apply() {
        newFolder = DoorsModelUtil.createFolder(null, "New folder");
        redo();
    }

    @Override
    public void redo() {
        parent.getChildren().add(parent.getChildren().indexOf(parent) + 1, newFolder);
    }

    @Override
    public void undo() {
        parent.getChildren().remove(newFolder);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return DatabasePaneController.UpdateTreeItem(parent).asArray();
    }
}
