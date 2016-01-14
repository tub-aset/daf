package de.jpwinkler.daf.csveditor.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

public class FlattenCommand extends AbstractCommand {

    public FlattenCommand(final DoorsModule module) {
        super(module);
    }

    private final List<DoorsObject> objectList = new ArrayList<>();

    private final Map<DoorsObject, DoorsTreeNode> oldParents = new HashMap<>();


    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public String getName() {
        return "Flatten";
    }

    @Override
    public void apply() {
        getModule().accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                objectList.add(object);
                oldParents.put(object, object.getParent());
                return true;
            }
        });
        redo();
    }

    @Override
    public void redo() {
        for (final DoorsObject object : objectList) {
            object.setParent(getModule());
        }
    }

    @Override
    public void undo() {
        for (final DoorsObject object : oldParents.keySet()) {
            object.setParent(oldParents.get(object));
        }
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.FIX_OBJECT_NUMBERS, UpdateAction.FIX_OBJECT_LEVELS, UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }

}
