package de.jpwinkler.daf.gui.databases.commands;

import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EditAttributesCommand extends AttributesCommand {

    public EditAttributesCommand(String key, String newValue, DoorsTreeNode treeNode) {
        super(Collections.singletonMap(key, newValue).entrySet(), treeNode);

    }

    public EditAttributesCommand(List<Map.Entry<String, String>> attributes, DoorsTreeNode treeNode) {
        super(attributes, treeNode);
    }

    @Override
    public String getName() {
        return "Edit Attributes";
    }

    @Override
    public void apply() {
        super.apply();
        redo();
    }

    @Override
    public void redo() {
        super.attributes.stream()
                .forEach(e -> super.treeNode.getAttributes().put(e.getKey(), e.getValue()));
    }

    @Override
    public void undo() {
        super.oldAttributes
                .forEach(e -> super.treeNode.getAttributes().put(e.getKey(), e.getValue()));
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateAttributesView);
    }

}
