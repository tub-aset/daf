package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.gui.commands.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsObject;

public class EditObjectAttributeCommand extends AbstractCommand {

    private final DoorsObject object;
    private final String attribute;

    private final String newValue;
    private String oldValue;

    public EditObjectAttributeCommand(final DoorsObject object, final String attribute, final String newValue) {
        this.object = object;
        this.attribute = attribute;
        this.newValue = newValue;

    }

    @Override
    public String getName() {
        return "Edit Attribute";
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }

    @Override
    public void apply() {
        if (attribute == null) {
            oldValue = object.getText();
        } else {
            oldValue = object.getAttributes().get(attribute);
        }
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
        return new UpdateAction[]{ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW};
    }

}
