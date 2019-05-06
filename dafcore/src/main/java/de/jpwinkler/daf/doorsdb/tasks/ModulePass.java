package de.jpwinkler.daf.doorsdb.tasks;

import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;

public abstract class ModulePass {

    private boolean saveDatabase;

    private DoorsDBInterface databaseInterface;

    public void preprocess() {
    }

    public void preprocessModule(final DoorsModule module) {
    }

    protected abstract void processModule(DoorsModule module);

    public void postprocessModule(final DoorsModule module) {
    }

    public void postprocess() {
    }

    public synchronized boolean isSaveDatabase() {
        return saveDatabase;
    }

    public synchronized void saveDatabase() {
        saveDatabase = true;
    }

    public DoorsDBInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public void setDatabaseInterface(final DoorsDBInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

}
