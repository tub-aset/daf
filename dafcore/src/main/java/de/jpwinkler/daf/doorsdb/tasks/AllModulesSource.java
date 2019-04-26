package de.jpwinkler.daf.doorsdb.tasks;

import java.util.function.Consumer;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.DoorsDBVisitor;

public class AllModulesSource implements ModuleSource {

    public AllModulesSource() {
        super();
    }

    @Override
    public void run(final DoorsDBInterface databaseInterface, final Consumer<DBModule> consumer) {
        databaseInterface.getDB().accept(new DoorsDBVisitor() {

            @Override
            public void visit(final DBModule module) {
                consumer.accept(module);
            }

            @Override
            public void visit(final DBFolder folder) {
            }
        });
    }

}
