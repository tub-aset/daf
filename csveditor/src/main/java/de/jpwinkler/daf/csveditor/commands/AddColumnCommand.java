package de.jpwinkler.daf.csveditor.commands;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public class AddColumnCommand extends AbstractCommand {

    private final String newColumnName;

    public AddColumnCommand(final DoorsModule module, final String newColumnName) {
        super(module);
        this.newColumnName = newColumnName;
    }

    @Override
    public String getName() {
        return "";
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
    public void redo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isApplicable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
