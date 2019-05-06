package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.FileDatabaseInterface;
import de.jpwinkler.daf.model.DoorsModule;

public abstract class ModulePass {

    private boolean saveDatabase;

    private FileDatabaseInterface databaseInterface;

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

    public FileDatabaseInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public void setDatabaseInterface(final FileDatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

}
