package de.jpwinkler.daf.dafcore.csv.gui.commands;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

public class UnwrapChildrenCommand extends AbstractCommand {

    private final DoorsObject object;
    private int objectIndex;
    private final List<DoorsTreeNode> children = new ArrayList<>();
    private DoorsTreeNode parent;

    public UnwrapChildrenCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object) {
        super(module, controller);
        this.object = object;
    }

    @Override
    public void apply() {
        children.addAll(object.getChildren());
        parent = object.getParent();
        objectIndex = parent.getChildren().indexOf(object);

        parent.getChildren().addAll(objectIndex, children);
        parent.getChildren().remove(object);
    }

    @Override
    public void undo() {
        object.getChildren().addAll(children);
        parent.getChildren().add(objectIndex, object);
    }

    @Override
    public boolean isApplicable() {
        return object != null && !object.getChildren().isEmpty();
    }

}
