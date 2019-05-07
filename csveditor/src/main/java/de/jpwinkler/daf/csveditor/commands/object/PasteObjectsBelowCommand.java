package de.jpwinkler.daf.csveditor.commands.object;

import de.jpwinkler.daf.csveditor.CommandStack.AbstractCommand;
import de.jpwinkler.daf.csveditor.commands.module.UpdateAction;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class PasteObjectsBelowCommand extends AbstractCommand {

    private final DoorsObject reference;
    private List<DoorsObject> copiedObjects;
    private final List<DoorsObject> objectsToCopy;

    public PasteObjectsBelowCommand(final DoorsModule module, final DoorsObject reference, final List<DoorsObject> objectsToCopy) {
        super(module);
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
                .map(o -> (DoorsObject) DoorsFactory.eINSTANCE.createDoorsObject().copyFrom(o))
                .collect(Collectors.toList());
        redo();
    }

    @Override
    public void redo() {
        reference.getChildren().addAll(copiedObjects);
    }

    @Override
    public void undo() {
        reference.getChildren().removeAll(copiedObjects);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.FIX_OBJECT_LEVELS, UpdateAction.FIX_OBJECT_NUMBERS, UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }

}
