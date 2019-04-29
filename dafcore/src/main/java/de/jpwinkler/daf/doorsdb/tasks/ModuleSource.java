package de.jpwinkler.daf.doorsdb.tasks;

import java.util.function.Consumer;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.model.DBModule;

public interface ModuleSource {

    void run(DoorsDBInterface databaseInterface, Consumer<DBModule> consumer);

}
