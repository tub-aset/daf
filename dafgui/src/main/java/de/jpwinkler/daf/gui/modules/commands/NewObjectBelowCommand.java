package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;

public class NewObjectBelowCommand extends AbstractCommand {

    private final DoorsTreeNode parent;
    private DoorsObject newObject;

    public NewObjectBelowCommand(final DoorsTreeNode parent) {
        this.parent = parent;
    }

    @Override
    public String getName() {
        return "New Object Below";
    }

    @Override
    public void apply() {
        newObject = DoorsModelUtil.createObject(parent, "New object");
        newObject.setParent(null);
        redo();
    }

    @Override
    public void undo() {
        parent.getChildren().remove(newObject);
    }

    @Override
    public void redo() {
        parent.getChildren().add(newObject);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW};
    }
}
