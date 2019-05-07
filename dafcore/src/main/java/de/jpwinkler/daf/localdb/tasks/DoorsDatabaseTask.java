package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.FileDatabaseInterface;

public abstract class DoorsDatabaseTask {

    private final FileDatabaseInterface databaseInterface;

    public DoorsDatabaseTask(final FileDatabaseInterface databaseInterface) {
        super();
        this.databaseInterface = databaseInterface;
    }

    public FileDatabaseInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public abstract void execute();

}
