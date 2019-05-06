package de.jpwinkler.daf.doorsdb.tasks;

import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import java.util.function.Consumer;

public interface ModuleSource {

    void run(DoorsDBInterface databaseInterface, Consumer<DoorsModule> consumer);

}
