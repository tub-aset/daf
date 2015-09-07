package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class EditObjectAttributeCommand extends AbstractCommand {

    private final DoorsObject object;
    private final String attribute;

    private final String newValue;
    private String oldValue;

    public EditObjectAttributeCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object, final String attribute, final String newValue) {
        super(module, controller);
        this.object = object;
        this.attribute = attribute;
        this.newValue = newValue;
    }

    @Override
    public boolean isApplicable() {
        return object != null && attribute != null;
    }

    @Override
    public void apply() {
        oldValue = object.getAttributes().get(attribute);
        object.getAttributes().put(attribute, newValue);
    }

    @Override
    public void undo() {
        object.getAttributes().put(attribute, oldValue);
    }

}
