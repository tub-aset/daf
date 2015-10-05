package de.jpwinkler.daf.csveditor.commands;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

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
        return new UpdateAction[] { UpdateAction.FIX_OBJECT_LEVELS, UpdateAction.FIX_OBJECT_NUMBERS, UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
