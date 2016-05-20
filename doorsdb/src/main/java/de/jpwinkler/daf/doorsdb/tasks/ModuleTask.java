package de.jpwinkler.daf.doorsdb.tasks;

import java.io.IOException;
import java.util.List;

import de.jpwinkler.daf.dafcore.util.Counter;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.search.DBSearchExpression;

public class ModuleTask extends DoorsDBTask {

    private final ModuleSource source;

    private final List<ModulePass> passes;

    private boolean saveDatabase = false;

    private final DBSearchExpression filter;

    public ModuleTask(final DoorsDBInterface databaseInterface, final List<ModulePass> passes, final ModuleSource source, final DBSearchExpression filter) {
        super(databaseInterface);
        this.source = source;
        this.passes = passes;
        this.filter = filter;
    }

    @Override
    public void execute() {
        preprocess();
        saveDatabase = false;
        passes.forEach(p -> {
            final Counter moduleCounter = new Counter();
            p.setDatabaseInterface(getDatabaseInterface());
            p.preprocess();
            source.run(getDatabaseInterface(), m -> {
                if (filter == null || filter.matches(m)) {
                    p.preprocessModule(m);
                    p.processModule(m);
                    p.postprocessModule(m);

                    if (p.isSaveDatabase()) {
                        saveDatabase = true;
                    }
                }
            });
            p.postprocess();
            System.out.println(moduleCounter.get() + " modules processed.");
        });
        postprocess();
        if (saveDatabase) {
            try {
                getDatabaseInterface().saveDB();
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    protected void preprocess() {
    }

    protected void postprocess() {
    }
}
