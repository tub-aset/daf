package de.jpwinkler.daf.gui.commands.module;

import de.jpwinkler.daf.gui.CommandStack.AbstractCommand;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SplitLinesCommand extends AbstractCommand {

    private final Map<DoorsObject, List<DoorsObject>> newObjects = new HashMap<>();

    public SplitLinesCommand(final DoorsModule module) {
        super(module);
    }

    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public String getName() {
        return "Split lines";
    }

    @Override
    public void apply() {
        getModule().accept(new DoorsTreeNodeVisitor<>(DoorsObject.class) {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                if (object.getText() != null) {
                    final String[] lines = object.getText().split("\n");
                    if (lines.length > 1) {
                        object.setText(lines[0]);
                        for (int i = lines.length - 1; i > 0; i--) {
                            newObjectAfter(object, i, lines[i]);
                        }
                    }
                }

                return true;
            }

        });
        redo();
    }

    @Override
    public void redo() {
        for (final Entry<DoorsObject, List<DoorsObject>> e : newObjects.entrySet()) {
            e.getKey().getParent().getChildren().addAll(e.getKey().getParent().getChildren().indexOf(e.getKey()), e.getValue());
        }
    }

    private void newObjectAfter(final DoorsObject object, final int i, final String string) {
        final DoorsObject newObject = DoorsFactory.eINSTANCE.createDoorsObject();
        for (final Entry<String, String> attribute : object.getAttributes().entrySet()) {
            newObject.getAttributes().put(attribute.getKey(), attribute.getValue());
        }
        newObject.setObjectIdentifier(object.getObjectIdentifier() + "-" + i);
        newObject.setText(string);

        if (!newObjects.containsKey(object)) {
            newObjects.put(object, new ArrayList<>());
        }
        newObjects.get(object).add(newObject);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canUndo() {
        return false;
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[]{UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW};
    }

}
