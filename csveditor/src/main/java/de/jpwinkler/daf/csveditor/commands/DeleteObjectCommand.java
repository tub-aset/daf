package de.jpwinkler.daf.csveditor.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

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
        return new UpdateAction[] { UpdateAction.FIX_OBJECT_NUMBERS, UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }
}
