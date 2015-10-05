package de.jpwinkler.daf.csveditor.commands;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;

public class PromoteObjectCommand extends AbstractCommand {

    private final DoorsObject object;

    public PromoteObjectCommand(final DoorsModule module, final DoorsObject object) {
        super(module);
        this.object = object;
    }

    @Override
    public String getName() {
        return "Promote Object";
    }

    @Override
    public boolean isApplicable() {
        return object.getParent() != null && object.getParent().getParent() != null && DoorsModuleUtil.getNextObject(object) == null;
    }

    @Override
    public void apply() {
        redo();
    }

    @Override
    public void undo() {
        DoorsModuleUtil.getPreviousObject(object).getChildren().add(object);
    }

    @Override
    public void redo() {
        object.getParent().getParent().getChildren().add(object.getParent().getParent().getChildren().indexOf(object.getParent()) + 1, object);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.FIX_OBJECT_LEVELS, UpdateAction.FIX_OBJECT_NUMBERS, UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
