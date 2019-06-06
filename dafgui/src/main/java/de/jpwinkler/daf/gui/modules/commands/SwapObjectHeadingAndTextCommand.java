package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.extensions.AbstractCommand;
import de.jpwinkler.daf.gui.extensions.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsObject;

public class SwapObjectHeadingAndTextCommand extends AbstractCommand {

    private final DoorsObject object;
    private String oldObjectHeading;
    private String oldObjectText;
    private String newObjectHeading;
    private String newObjectText;

    public SwapObjectHeadingAndTextCommand(final DoorsObject object) {
        this.object = object;
    }

    @Override
    public String getName() {
        return "Swap Object Heading/Text";
    }

    @Override
    public void apply() {
        oldObjectHeading = object.getObjectHeading();
        oldObjectText = object.getObjectText();
        newObjectHeading = object.getObjectText();
        newObjectText = object.getObjectHeading();
        redo();
    }

    @Override
    public void undo() {
        object.setObjectText(oldObjectText);
        object.setObjectHeading(oldObjectHeading);
    }

    @Override
    public void redo() {
        object.setObjectText(newObjectText);
        object.setObjectHeading(newObjectHeading);
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
