package de.jpwinkler.daf.doorsdb.tasks;

import java.util.List;
import java.util.function.Consumer;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public class ModuleListSource implements ModuleSource {

    private final DoorsDBInterface databaseInterface;
    private final List<String> moduleNames;

    public ModuleListSource(final DoorsDBInterface databaseInterface, final List<String> moduleNames) {
        super();
        this.databaseInterface = databaseInterface;
        this.moduleNames = moduleNames;
    }

    @Override
    public void run(final Consumer<DBModule> consumer) {
        moduleNames.forEach(m -> {
            final DBModule module = databaseInterface.getModule(m);
            if (module != null) {
                consumer.accept(module);
            } else {
                System.out.println(m + " not found");
            }
        });
    }

}
