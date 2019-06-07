package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.List;

public class UnwrapChildrenCommand extends AbstractCommand {

    private final DoorsObject object;
    private int objectIndex;
    private final List<DoorsTreeNode> children = new ArrayList<>();
    private DoorsTreeNode parent;

    public UnwrapChildrenCommand(final DoorsObject object) {
        this.object = object;
    }

    @Override
    public String getName() {
        return "Unwrap Children";
    }

    @Override
    public void apply() {
        children.addAll(object.getChildren());
        parent = object.getParent();
        objectIndex = parent.getChildren().indexOf(object);
        redo();
    }

    @Override
    public void undo() {
        object.getChildren().addAll(children);
        parent.getChildren().add(objectIndex, object);
    }

    @Override
    public void redo() {
        parent.getChildren().addAll(objectIndex, children);
        parent.getChildren().remove(object);
    }

    @Override
    public boolean isApplicable() {
        return object != null && !object.getChildren().isEmpty();
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
