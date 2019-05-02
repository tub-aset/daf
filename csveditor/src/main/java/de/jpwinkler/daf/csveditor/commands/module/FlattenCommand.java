package de.jpwinkler.daf.csveditor.commands.module;

import de.jpwinkler.daf.csveditor.CommandStack.AbstractCommand;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.doorscsv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;
import de.jpwinkler.daf.doorscsv.model.DoorsTreeNode;

public class FlattenCommand extends AbstractCommand {

    public FlattenCommand(final DoorsModule module) {
        super(module);
    }

    private final List<DoorsObject> objectList = new ArrayList<>();

    private final Map<DoorsObject, DoorsTreeNode> oldParents = new LinkedHashMap<>();


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
            object.getModule().getChildren().remove(object);
            object.getModule().getChildren().add(object);
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
