package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.gui.CommandStack.AbstractCommand;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteObjectCommand extends AbstractCommand {

    private final List<DoorsObject> objects;
    private final Map<DoorsObject, DoorsTreeNode> parents = new HashMap<>();
    private final Map<DoorsObject, Integer> objectIndices = new HashMap<>();

    public DeleteObjectCommand(final DoorsModule module, final List<DoorsObject> objects) {
        super(module);
        this.objects = new ArrayList<>(objects);
    }

    @Override
    public String getName() {
        return "Delete Object";
    }

    @Override
    public void apply() {
        for (final DoorsObject object : objects) {
            parents.put(object, object.getParent());
            objectIndices.put(object, object.getParent().getChildren().indexOf(object));
        }
        redo();
    }

    @Override
    public void undo() {
        for (final DoorsObject object : objects) {
            parents.get(object).getChildren().add(objectIndices.get(object), object);
        }
    }

    @Override
    public void redo() {
        for (final DoorsObject object : objects) {
            parents.get(object).getChildren().remove(object);
        }
    }

    @Override
    public boolean isApplicable() {
        return objects.stream().allMatch(o -> o != null && o.getParent() != null);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
