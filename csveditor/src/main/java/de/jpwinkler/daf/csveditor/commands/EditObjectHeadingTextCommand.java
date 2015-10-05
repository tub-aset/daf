package de.jpwinkler.daf.csveditor.commands;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class EditObjectHeadingTextCommand extends AbstractCommand {

    private final DoorsObject object;
    private final String newValue;
    private String oldObjectText;
    private String oldObjectHeading;

    public EditObjectHeadingTextCommand(final DoorsModule module, final DoorsObject object, final String newValue) {
        super(module);
        this.object = object;
        this.newValue = newValue;
    }

    @Override
    public String getName() {
        return "Edit Object Text/Heading";
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        oldObjectHeading = object.getObjectHeading();
        oldObjectText = object.getObjectText();
        redo();
    }

    @Override
    public void undo() {
        object.setObjectText(oldObjectText);
        object.setObjectHeading(oldObjectHeading);
    }

    @Override
    public void redo() {
        if (object.isHeading()) {
            object.setObjectHeading(newValue);
        } else {
            object.setObjectText(newValue);
        }
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }

}
