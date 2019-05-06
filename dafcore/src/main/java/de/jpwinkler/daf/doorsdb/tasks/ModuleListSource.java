package de.jpwinkler.daf.doorsdb.tasks;

import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import java.util.List;
import java.util.function.Consumer;

public class ModuleListSource implements ModuleSource {

    private final List<String> moduleNames;

    public ModuleListSource(final List<String> moduleNames) {
        super();
        this.moduleNames = moduleNames;
    }

    @Override
    public void run(final DoorsDBInterface databaseInterface, final Consumer<DoorsModule> consumer) {
        moduleNames.forEach(m -> {
            final DoorsModule module = databaseInterface.getModule(m);
            if (module != null) {
                consumer.accept(module);
            } else {
                System.out.println(m + " not found");
            }
        });
    }

}
