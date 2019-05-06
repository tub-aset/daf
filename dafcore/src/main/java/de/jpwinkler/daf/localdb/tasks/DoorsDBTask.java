package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.FileDatabaseInterface;

public abstract class DoorsDBTask {

    private final FileDatabaseInterface databaseInterface;

    public DoorsDBTask(final FileDatabaseInterface databaseInterface) {
        super();
        this.databaseInterface = databaseInterface;
    }

    public FileDatabaseInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public abstract void execute();

}
