package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.DatabaseInterface;

public abstract class DoorsDatabaseTask {

    private final DatabaseInterface databaseInterface;

    public DoorsDatabaseTask(final DatabaseInterface databaseInterface) {
        super();
        this.databaseInterface = databaseInterface;
    }

    public DatabaseInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public abstract void execute();

}
