package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.FileDatabaseInterface;
import de.jpwinkler.daf.model.DoorsModule;
import java.util.function.Consumer;

public interface ModuleSource {

    void run(FileDatabaseInterface databaseInterface, Consumer<DoorsModule> consumer);

}
