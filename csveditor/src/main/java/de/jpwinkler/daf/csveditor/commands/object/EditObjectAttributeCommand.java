package de.jpwinkler.daf.csveditor.commands.object;

import de.jpwinkler.daf.csveditor.AbstractCommand;
import de.jpwinkler.daf.csveditor.commands.module.UpdateAction;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public class EditObjectAttributeCommand extends AbstractCommand {

    private final DoorsObject object;
    private final String attribute;

    private final String newValue;
    private String oldValue;

    public EditObjectAttributeCommand(final DoorsModule module, final DoorsObject object, final String attribute, final String newValue) {
        super(module);
        this.object = object;
        this.attribute = attribute;
        this.newValue = newValue;

    }

    @Override
    public String getName() {
        return "Edit Attribute " + attribute;
    }

    @Override
    public boolean isApplicable() {
        return object != null && attribute != null;
    }

    @Override
    public void apply() {
        oldValue = object.getAttributes().get(attribute);
        redo();
    }

    @Override
    public void undo() {
        if (attribute == null) {
            object.setText(oldValue);
        } else {
            object.getAttributes().put(attribute, oldValue);
        }
    }

    @Override
    public void redo() {
        if (attribute == null) {
            object.setText(newValue);
        } else {
            object.getAttributes().put(attribute, newValue);
        }
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW};
    }

}
