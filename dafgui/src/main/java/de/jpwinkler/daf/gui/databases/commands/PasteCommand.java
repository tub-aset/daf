package de.jpwinkler.daf.gui.databases.commands;

import de.jpwinkler.daf.gui.databases.DatabasePaneController;
import de.jpwinkler.daf.gui.extensions.AbstractCommand;
import de.jpwinkler.daf.gui.extensions.UpdateAction;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PasteCommand extends AbstractCommand {

    private final DoorsTreeNode parent;
    private List<DoorsTreeNode> copiedObjects;
    private final List<DoorsTreeNode> objectsToCopy;

    public PasteCommand(final DoorsTreeNode parent, final List<DoorsTreeNode> objectsToCopy) {
        this.parent = parent;
        this.objectsToCopy = new ArrayList<>(objectsToCopy);
    }

    @Override
    public boolean isApplicable() {
        return parent != null && !objectsToCopy.isEmpty();
    }

    @Override
    public String getName() {
        return "Paste Object";
    }

    @Override
    public void apply() {
        copiedObjects = objectsToCopy.stream()
                .map(o -> DoorsModelUtil.createCopy(o, parent))
                .collect(Collectors.toList());
        redo();
    }

    @Override
    public void redo() {
        parent.getChildren().addAll(copiedObjects);
    }

    @Override
    public void undo() {
        parent.getChildren().removeAll(copiedObjects);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return UpdateAction.of(DatabasePaneController.UpdateTreeItem(parent), DatabasePaneController.UpdateModulesView);
    }

}
