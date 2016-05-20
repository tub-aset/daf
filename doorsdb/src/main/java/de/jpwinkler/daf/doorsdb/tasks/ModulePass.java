package de.jpwinkler.daf.doorsdb.tasks;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public abstract class ModulePass {

    private boolean saveDatabase;

    private DoorsDBInterface databaseInterface;

    public void preprocess() {
    }

    public void preprocessModule(final DBModule module) {
    }

    protected abstract void processModule(DBModule module);

    public void postprocessModule(final DBModule module) {
    }

    public void postprocess() {
    }

    public boolean isSaveDatabase() {
        return saveDatabase;
    }

    public void saveDatabase() {
        saveDatabase = true;
    }

    public DoorsDBInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public void setDatabaseInterface(final DoorsDBInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

}
