package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.CommandStack.AbstractCommand;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsObject;

public class PromoteObjectCommand extends AbstractCommand {

    private final DoorsObject object;

    public PromoteObjectCommand(final DoorsObject object) {
        this.object = object;
    }

    @Override
    public String getName() {
        return "Promote Object";
    }

    @Override
    public boolean isApplicable() {
        return object.getParent() != null && object.getParent().getParent() != null && DoorsModelUtil.getNextObject(object) == null;
    }

    @Override
    public void apply() {
        redo();
    }

    @Override
    public void undo() {
        DoorsModelUtil.getPreviousObject(object).getChildren().add(object);
    }

    @Override
    public void redo() {
        object.getParent().getParent().getChildren().add(object.getParent().getParent().getChildren().indexOf(object.getParent()) + 1, object);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
