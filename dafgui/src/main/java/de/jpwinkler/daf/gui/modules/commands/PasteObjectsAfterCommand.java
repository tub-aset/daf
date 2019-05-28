package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.CommandStack.AbstractCommand;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PasteObjectsAfterCommand extends AbstractCommand {

    private final DoorsObject reference;
    private List<DoorsObject> copiedObjects;
    private final List<DoorsObject> objectsToCopy;

    public PasteObjectsAfterCommand(final DoorsObject reference, final List<DoorsObject> objectsToCopy) {
        this.reference = reference;
        this.objectsToCopy = new ArrayList<>(objectsToCopy);
    }

    @Override
    public boolean isApplicable() {
        return reference != null && !objectsToCopy.isEmpty();
    }

    @Override
    public String getName() {
        return "Copy Object";
    }

    @Override
    public void apply() {
        copiedObjects = objectsToCopy.stream()
                .map(o -> (DoorsObject) DoorsModelUtil.createCopy(o, reference.getParent()))
                .collect(Collectors.toList());
        redo();
    }

    @Override
    public void redo() {
        reference.getParent().getChildren().addAll(reference.getParent().getChildren().indexOf(reference) + 1, copiedObjects);
    }

    @Override
    public void undo() {
        reference.getParent().getChildren().removeAll(copiedObjects);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }

}
