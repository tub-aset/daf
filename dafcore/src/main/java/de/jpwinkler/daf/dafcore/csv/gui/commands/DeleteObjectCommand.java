package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

public class DeleteObjectCommand extends AbstractCommand {

    private final DoorsObject object;
    private DoorsTreeNode parent;
    private int objectIndex;

    public DeleteObjectCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object) {
        super(module, controller);
        this.object = object;
    }

    @Override
    public void apply() {
        parent = object.getParent();
        objectIndex = parent.getChildren().indexOf(object);
        parent.getChildren().remove(object);
    }

    @Override
    public void undo() {
        parent.getChildren().add(objectIndex, object);
    }

    @Override
    public boolean isApplicable() {
        return object != null;
    }
}
