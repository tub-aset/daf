package de.jpwinkler.daf.doorsdb.tasks;

import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import java.util.function.Consumer;

public class AllModulesSource implements ModuleSource {

    public AllModulesSource() {
        super();
    }

    @Override
    public void run(final DoorsDBInterface databaseInterface, final Consumer<DoorsModule> consumer) {
        databaseInterface.getDB().accept(new DoorsTreeNodeVisitor() {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                consumer.accept(module);
            }

        });
    }

}
