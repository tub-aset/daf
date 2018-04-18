package de.jpwinkler.daf.doorsdb.tasks;

import java.io.IOException;
import java.util.List;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.search.DBSearchExpression;

public class ModuleTask extends DoorsDBTask {

    private final List<ModulePass> passes;

    private boolean saveDatabase = false;

    private final DBSearchExpression filter;

    private final List<ModuleSource> sources;

    public ModuleTask(final DoorsDBInterface databaseInterface, final List<ModulePass> passes, final List<ModuleSource> sources, final DBSearchExpression filter) {
        super(databaseInterface);
        this.sources = sources;
        this.passes = passes;
        this.filter = filter;
        if (sources.isEmpty()) {
            sources.add(new AllModulesSource());
        }
    }

    @Override
    public void execute() {
        preprocess();
        saveDatabase = false;
        passes.forEach(p -> {
            p.setDatabaseInterface(getDatabaseInterface());
            p.preprocess();
            sources.forEach(source -> source.run(getDatabaseInterface(), m -> {
                if (filter == null || filter.matches(m)) {
                    p.preprocessModule(m);
                    p.processModule(m);
                    p.postprocessModule(m);

                    if (p.isSaveDatabase()) {
                        saveDatabase = true;
                    }
                }
            }));
            p.postprocess();
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
