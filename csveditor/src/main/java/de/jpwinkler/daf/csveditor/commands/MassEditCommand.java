package de.jpwinkler.daf.csveditor.commands;

import java.util.List;

import de.jpwinkler.daf.csveditor.massedit.MassEditOperation;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public class MassEditCommand extends AbstractCommand {

    private final MassEditOperation operation;

    private final List<DoorsObject> objects;

    public MassEditCommand(final DoorsModule module, final List<DoorsObject> objects, final MassEditOperation operation) {
        super(module);
        this.objects = objects;
        this.operation = operation;
    }

    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public String getName() {
        return "Mass Edit";
    }

    @Override
    public void apply() {
        redo();
    }

    @Override
    public void redo() {
        for (final DoorsObject object : objects) {
            operation.apply(object);
        }
    }

    @Override
    public void undo() {
        // nope!
    }

    @Override
    public boolean canUndo() {
        // TODO: make this undo-able.
        return false;
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.UPDATE_CONTENT_VIEW };
    }

}
