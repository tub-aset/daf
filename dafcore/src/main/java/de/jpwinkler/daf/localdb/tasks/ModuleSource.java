package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.DatabaseInterface;
import de.jpwinkler.daf.model.DoorsModule;
import java.util.function.Consumer;

public interface ModuleSource {

    void run(DatabaseInterface databaseInterface, Consumer<DoorsModule> consumer);

}
