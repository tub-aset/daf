package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.DatabaseInterface;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.function.Consumer;

public class AllModulesSource implements ModuleSource {

    public AllModulesSource() {
        super();
    }

    @Override
    public void run(final DatabaseInterface databaseInterface, final Consumer<DoorsModule> consumer) {
        databaseInterface.getDatabaseObject().getRoot().accept(new DoorsTreeNodeVisitor() {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                consumer.accept(module);
            }

        });
    }

}
