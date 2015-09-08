package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class SwapObjectHeadingAndTextCommand extends AbstractCommand {

    private final DoorsObject object;
    private final String oldObjectHeading;
    private final String oldObjectText;

    public SwapObjectHeadingAndTextCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object) {
        super(module, controller);
        this.object = object;
        oldObjectHeading = object.getObjectHeading();
        oldObjectText = object.getObjectText();
    }

    @Override
    public void apply() {
        if (object.isHeading()) {
            object.setObjectText(object.getObjectHeading());
            object.setObjectHeading("");
        } else {
            object.setObjectHeading(object.getObjectText());
            object.setObjectText("");
        }
    }

    @Override
    public void undo() {
        object.setObjectText(oldObjectText);
        object.setObjectHeading(oldObjectHeading);
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
