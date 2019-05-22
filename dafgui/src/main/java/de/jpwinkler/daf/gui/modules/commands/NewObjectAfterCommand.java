package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.CommandStack.AbstractCommand;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsObject;

public class NewObjectAfterCommand extends AbstractCommand {

    private final DoorsObject object;
    private DoorsObject newObject;

    public NewObjectAfterCommand(final DoorsObject object) {
        this.object = object;
    }

    @Override
    public String getName() {
        return "New Object After";
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        newObject = DoorsFactory.eINSTANCE.createDoorsObject();
        newObject.setObjectText("New object");
        newObject.setObjectHeading("");
        newObject.setObjectLevel(object.getObjectLevel());
        redo();
    }

    @Override
    public void redo() {
        object.getParent().getChildren().add(object.getParent().getChildren().indexOf(object) + 1, newObject);
    }

    @Override
    public void undo() {
        object.getParent().getChildren().remove(newObject);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }

}