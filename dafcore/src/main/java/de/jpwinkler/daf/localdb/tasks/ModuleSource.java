package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.DoorsDBInterface;
import de.jpwinkler.daf.model.DoorsModule;
import java.util.function.Consumer;

public interface ModuleSource {

    void run(DoorsDBInterface databaseInterface, Consumer<DoorsModule> consumer);

}
