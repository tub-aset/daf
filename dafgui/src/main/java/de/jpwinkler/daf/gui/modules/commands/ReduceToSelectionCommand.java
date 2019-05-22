package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.CommandStack.AbstractCommand;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.List;

public class ReduceToSelectionCommand extends AbstractCommand {

    private final DoorsObject object;
    private int oldObjectIndex;
    private final List<DoorsTreeNode> moduleChildren = new ArrayList<>();
    private DoorsTreeNode oldParent;

    public ReduceToSelectionCommand(final DoorsModule module, final DoorsObject object) {
        super(module);
        this.object = object;
    }

    @Override
    public String getName() {
        return "Reduce to Selection";
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        moduleChildren.addAll(getModule().getChildren());
        oldParent = object.getParent();
        oldObjectIndex = oldParent.getChildren().indexOf(object);
        redo();
    }

    @Override
    public void undo() {
        getModule().getChildren().clear();
        getModule().getChildren().addAll(moduleChildren);
        oldParent.getChildren().add(oldObjectIndex, object);

    }

    @Override
    public void redo() {
        getModule().getChildren().clear();
        getModule().getChildren().add(object);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
