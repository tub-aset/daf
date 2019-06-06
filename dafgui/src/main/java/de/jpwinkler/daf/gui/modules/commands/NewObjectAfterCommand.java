package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.extensions.AbstractCommand;
import de.jpwinkler.daf.gui.extensions.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsModule;
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
        newObject = DoorsModelUtil.createObject((DoorsModule) object.getParent(), "New object");
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
