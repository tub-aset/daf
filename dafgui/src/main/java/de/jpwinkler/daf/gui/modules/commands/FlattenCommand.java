package de.jpwinkler.daf.gui.modules.commands;

import de.jpwinkler.daf.gui.CommandStack.AbstractCommand;
import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.modules.ModulePaneController.ModuleUpdateAction;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FlattenCommand extends AbstractCommand {

    private final DoorsModule module;

    public FlattenCommand(final DoorsModule module) {
        this.module = module;
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
        module.accept(new DoorsTreeNodeVisitor<>(DoorsObject.class) {
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
            object.getParent().getChildren().remove(object);
            object.getParent().getChildren().add(object);
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
        return new UpdateAction[] { ModuleUpdateAction.FIX_OBJECT_NUMBERS, ModuleUpdateAction.FIX_OBJECT_LEVELS, ModuleUpdateAction.UPDATE_CONTENT_VIEW, ModuleUpdateAction.UPDATE_OUTLINE_VIEW };
    }

}
