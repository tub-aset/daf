package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.db.DatabaseFactory;
import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;

public class NewObjectAfterCommand extends AbstractCommand {

    private final DatabaseFactory factory;
    private final DoorsObject object;
    private DoorsObject newObject;

    public NewObjectAfterCommand(DatabaseFactory factory, DoorsObject object) {
        this.factory = factory;
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
        newObject = factory.createObject((DoorsModule) object.getParent(), "New object");
        newObject.setParent(null);
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
        return new UpdateAction[]{ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW};
    }

}
