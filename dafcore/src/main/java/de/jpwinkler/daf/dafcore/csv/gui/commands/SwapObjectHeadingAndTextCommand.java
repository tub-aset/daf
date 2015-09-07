package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class SwapObjectHeadingAndTextCommand extends AbstractCommand {

    private final DoorsObject object;

    public SwapObjectHeadingAndTextCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object) {
        super(module, controller);
        this.object = object;
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
        apply();
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }
}
