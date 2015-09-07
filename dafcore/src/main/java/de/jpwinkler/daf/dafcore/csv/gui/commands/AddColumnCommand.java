package de.jpwinkler.daf.dafcore.csv.gui.commands;

import de.jpwinkler.daf.dafcore.csv.gui.CSVViewerTabController;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public class AddColumnCommand extends AbstractCommand {

    private final String newColumnName;

    public AddColumnCommand(final DoorsModule module, final CSVViewerTabController controller, final String newColumnName) {
        super(module, controller);
        this.newColumnName = newColumnName;
    }

    @Override
    public void apply() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isApplicable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
