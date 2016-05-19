package de.jpwinkler.daf.doorsdb.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;

public abstract class DoorsDBTask {

    public static DoorsDBInterface getDefaultDatabase() throws FileNotFoundException, IOException {
        return DoorsDBInterface.createOrOpenDB(new File("C:/WORK/DoorsDB/db.doorsdbmodel"));
    }

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
