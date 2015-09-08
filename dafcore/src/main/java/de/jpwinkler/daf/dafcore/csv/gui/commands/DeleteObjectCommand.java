package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

public class DeleteObjectCommand extends AbstractCommand {

    private final DoorsObject object;
    private DoorsTreeNode parent;
    private int objectIndex;

    public DeleteObjectCommand(final DoorsModule module, final DoorsObject object) {
        super(module);
        this.object = object;
    }

    @Override
    public String getName() {
        return "Delete Object";
    }

    @Override
    public void apply() {
        parent = object.getParent();
        objectIndex = parent.getChildren().indexOf(object);
        redo();
    }

    @Override
    public void undo() {
        parent.getChildren().add(objectIndex, object);
    }

    @Override
    public void redo() {
        parent.getChildren().remove(object);
    }

    @Override
    public boolean isApplicable() {
        return object != null && object.getParent() != null;
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.FIX_OBJECT_NUMBERS, UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
