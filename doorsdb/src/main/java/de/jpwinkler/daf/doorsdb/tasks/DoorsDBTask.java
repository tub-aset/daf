package de.jpwinkler.daf.doorsdb.tasks;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;

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
