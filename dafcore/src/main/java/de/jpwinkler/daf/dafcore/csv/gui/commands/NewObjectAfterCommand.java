package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class NewObjectAfterCommand extends AbstractCommand {

    private final DoorsObject object;
    private final DoorsObject newObject;

    public NewObjectAfterCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object) {
        super(module, controller);
        this.object = object;
        newObject = CSVFactory.eINSTANCE.createDoorsObject();
        newObject.setObjectText("");
        newObject.setObjectHeading("");
        newObject.setObjectLevel(object.getObjectLevel());
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        object.getParent().getChildren().add(object.getParent().getChildren().indexOf(object) + 1, newObject);
    }

    @Override
    public void undo() {
        object.getParent().getChildren().remove(newObject);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }

}
