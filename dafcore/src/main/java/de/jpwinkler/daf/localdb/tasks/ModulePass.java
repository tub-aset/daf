package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.DoorsDBInterface;
import de.jpwinkler.daf.model.DoorsModule;

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
