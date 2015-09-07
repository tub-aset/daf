package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;

public class DemoteObjectCommand extends AbstractCommand {

    private final DoorsObject object;

    public DemoteObjectCommand(final DoorsModule module, final CSVViewerTabController controller, final DoorsObject object) {
        super(module, controller);
        this.object = object;
    }

    @Override
    public void apply() {
        DoorsModuleUtil.getPreviousObject(object).getChildren().add(object);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isApplicable() {
        return object != null && DoorsModuleUtil.getPreviousObject(object) != null;
    }

}
