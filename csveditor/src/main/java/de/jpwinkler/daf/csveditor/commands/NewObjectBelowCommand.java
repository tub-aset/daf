package de.jpwinkler.daf.csveditor.commands;

import de.jpwinkler.daf.doorscsv.model.DoorsCSVFactory;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public class NewObjectBelowCommand extends AbstractCommand {

    private final DoorsObject object;
    private DoorsObject newObject;

    public NewObjectBelowCommand(final DoorsModule module, final DoorsObject object) {
        super(module);
        this.object = object;
    }

    @Override
    public String getName() {
        return "New Object Below";
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        newObject = DoorsCSVFactory.eINSTANCE.createDoorsObject();
        newObject.setObjectText("");
        newObject.setObjectHeading("");
        newObject.setObjectLevel(object.getObjectLevel() + 1);
        redo();
    }

    @Override
    public void undo() {
        object.getChildren().remove(newObject);
    }

    @Override
    public void redo() {
        object.getChildren().add(newObject);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
