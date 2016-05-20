package de.jpwinkler.daf.doorsdb.tasks;

import java.util.function.Consumer;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public interface ModuleSource {

    void run(DoorsDBInterface databaseInterface, Consumer<DBModule> consumer);

}
