package de.jpwinkler.daf.csveditor.commands.object;

import de.jpwinkler.daf.csveditor.CommandStack.AbstractCommand;
import de.jpwinkler.daf.csveditor.commands.module.UpdateAction;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;

public class NewObjectBelowCommand extends AbstractCommand {

    private final DoorsTreeNode parent;
    private DoorsObject newObject;

    public NewObjectBelowCommand(final DoorsModule module, final DoorsTreeNode parent) {
        super(module);
        this.parent = parent == null ? module : (DoorsObject) parent;
    }

    @Override
    public String getName() {
        return "New Object Below";
    }

    @Override
    public void apply() {
        newObject = DoorsFactory.eINSTANCE.createDoorsObject();
        newObject.setObjectText("New object");
        newObject.setObjectHeading("");
        newObject.setObjectLevel(((parent instanceof DoorsObject) ? ((DoorsObject) parent).getObjectLevel() : 0) + 1);
        redo();
    }

    @Override
    public void undo() {
        parent.getChildren().remove(newObject);
    }

    @Override
    public void redo() {
        parent.getChildren().add(newObject);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW};
    }
}
