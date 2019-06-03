package de.jpwinkler.daf.gui.databases.commands;

import de.jpwinkler.daf.gui.UpdateAction;
import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RenameAttributesCommand extends AttributesCommand {

    public RenameAttributesCommand(String oldKey, String newKey, DoorsTreeNode treeNode) {
        super(Collections.singletonMap(oldKey, newKey).entrySet(), treeNode);

    }

    public RenameAttributesCommand(List<Map.Entry<String, String>> attributes, DoorsTreeNode treeNode) {
        super(attributes, treeNode);
    }

    @Override
    public String getName() {
        return "Rename Attributes";
    }

    @Override
    public void redo() {
        super.attributes.stream()
                .forEach(e -> {
                    String value = super.treeNode.getAttributes().remove(e.getKey());
                    super.treeNode.getAttributes().put(e.getValue(), value);
                });
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateAttributesView);
    }

}
