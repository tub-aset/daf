package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.gui.CommandStack.AbstractCommand;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsModuleUtil;
import de.jpwinkler.daf.model.DoorsObject;

public class DemoteObjectCommand extends AbstractCommand {

    private final DoorsObject object;

    public DemoteObjectCommand(final DoorsModule module, final DoorsObject object) {
        super(module);
        this.object = object;
    }

    @Override
    public String getName() {
        return "Demote Object";
    }

    @Override
    public void apply() {
        redo();
    }

    @Override
    public void undo() {
        object.getParent().getParent().getChildren().add(object.getParent().getParent().getChildren().indexOf(object.getParent()) + 1, object);
    }

    @Override
    public void redo() {
        DoorsModuleUtil.getPreviousObject(object).getChildren().add(object);
    }

    @Override
    public boolean isApplicable() {
        return object != null && DoorsModuleUtil.getPreviousObject(object) != null;
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
