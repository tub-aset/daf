package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.DoorsDBInterface;

public abstract class DoorsDBTask {

    private final DoorsDBInterface databaseInterface;

    public DoorsDBTask(final DoorsDBInterface databaseInterface) {
        super();
        this.databaseInterface = databaseInterface;
    }

    public DoorsDBInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public abstract void execute();

}
