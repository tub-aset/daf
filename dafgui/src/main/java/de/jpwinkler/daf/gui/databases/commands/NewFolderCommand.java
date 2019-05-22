/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases.commands;

import de.jpwinkler.daf.gui.CommandStack;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;

/**
 *
 * @author fwiesweg
 */
public class NewFolderCommand extends CommandStack.AbstractCommand {
    private final DoorsObject object;
    private DoorsObject newObject;

    public NewFolderCommand(final DoorsTreeNode node, final DoorsObject object) {
        this.object = object;
    }

    @Override
    public String getName() {
        return "New Object";
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        newObject = DoorsFactory.eINSTANCE.createDoorsObject();
        newObject.setObjectText("New object");
        newObject.setObjectHeading("");
        newObject.setObjectLevel(object.getObjectLevel());
        redo();
    }

    @Override
    public void redo() {
        object.getParent().getChildren().add(object.getParent().getChildren().indexOf(object) + 1, newObject);
    }

    @Override
    public void undo() {
        object.getParent().getChildren().remove(newObject);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModulePaneController.ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModulePaneController.ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
