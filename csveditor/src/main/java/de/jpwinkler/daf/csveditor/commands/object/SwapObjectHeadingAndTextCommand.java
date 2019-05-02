package de.jpwinkler.daf.csveditor.commands.object;

import de.jpwinkler.daf.csveditor.AbstractCommand;
import de.jpwinkler.daf.csveditor.commands.module.UpdateAction;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public class SwapObjectHeadingAndTextCommand extends AbstractCommand {

    private final DoorsObject object;
    private String oldObjectHeading;
    private String oldObjectText;
    private String newObjectHeading;
    private String newObjectText;

    public SwapObjectHeadingAndTextCommand(final DoorsModule module, final DoorsObject object) {
        super(module);
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
        return new UpdateAction[] { UpdateAction.FIX_OBJECT_LEVELS, UpdateAction.FIX_OBJECT_NUMBERS, UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
