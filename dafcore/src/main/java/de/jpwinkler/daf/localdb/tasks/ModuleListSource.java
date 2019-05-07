package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.DatabaseInterface;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.List;
import java.util.function.Consumer;

public class ModuleListSource implements ModuleSource {

    private final List<String> moduleNames;

    public ModuleListSource(final List<String> moduleNames) {
        super();
        this.moduleNames = moduleNames;
    }

    @Override
    public void run(final DatabaseInterface databaseInterface, final Consumer<DoorsModule> consumer) {
        moduleNames.forEach(m -> {
            final DoorsTreeNode module = databaseInterface.getNode(m);
            if (module != null && (module instanceof DoorsModule)) {
                consumer.accept((DoorsModule) module);
            } else {
                System.out.println(m + " not found");
            }
        });
    }

}
