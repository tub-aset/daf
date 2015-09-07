package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class EditObjectHeadingTextCommand extends AbstractCommand {

    private final DoorsObject object;
    private final String newValue;
    private String oldObjectText;
    private String oldObjectHeading;

    public EditObjectHeadingTextCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object, final String newValue) {
        super(module, controller);
        this.object = object;
        this.newValue = newValue;
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        oldObjectHeading = object.getObjectHeading();
        oldObjectText = object.getObjectText();
        if (object.isHeading()) {
            object.setObjectHeading(newValue);
        } else {
            object.setObjectText(newValue);
        }
    }

    @Override
    public void undo() {
        object.setObjectText(oldObjectText);
        object.setObjectHeading(oldObjectHeading);
    }

}
