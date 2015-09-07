package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;

public class PromoteObjectCommand extends AbstractCommand {

    private final DoorsObject object;

    public PromoteObjectCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object) {
        super(module, controller);
        this.object = object;
    }

    @Override
    public boolean isApplicable() {
        return object.getParent() != null && object.getParent().getParent() != null && DoorsModuleUtil.getNextObject(object) == null;
    }

    @Override
    public void apply() {
        object.getParent().getParent().getChildren().add(object.getParent().getParent().getChildren().indexOf(object.getParent()) + 1, object);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
