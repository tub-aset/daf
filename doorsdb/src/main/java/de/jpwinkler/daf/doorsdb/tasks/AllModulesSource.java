package de.jpwinkler.daf.doorsdb.tasks;

import java.util.function.Consumer;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.search.DBSearchExpression;
import de.jpwinkler.daf.doorsdb.util.DoorsDBVisitor;

public class AllModulesSource implements ModuleSource {

    private final DoorsDBInterface databaseInterface;
    private DBSearchExpression filter = null;

    public AllModulesSource(final DoorsDBInterface databaseInterface) {
        super();
        this.databaseInterface = databaseInterface;
    }

    public AllModulesSource(final DoorsDBInterface databaseInterface, final DBSearchExpression filter) {
        super();
        this.databaseInterface = databaseInterface;
        this.filter = filter;
    }

    public void setFilter(final DBSearchExpression filter) {
        this.filter = filter;
    }

    @Override
    public void run(final Consumer<DBModule> consumer) {
        databaseInterface.getDB().accept(new DoorsDBVisitor() {

            @Override
            public void visit(final DBModule module) {
                if (filter == null || filter.matches(module)) {
                    System.out.println(module.getFullName());
                    consumer.accept(module);
                }
            }

            @Override
            public void visit(final DBFolder folder) {
            }
        });
    }

}
