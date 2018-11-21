package de.jpwinkler.daf.doorsdb.tasks;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.search.DBSearchExpression;

public class ModuleTask extends DoorsDBTask {

    private final List<ModulePass> passes;

    private boolean saveDatabase = false;
    private final Object saveDatabaseLock = new Object();

    private boolean parallel = false;

    private final DBSearchExpression filter;

    private final List<ModuleSource> sources;

    public ModuleTask(final DoorsDBInterface databaseInterface, final List<ModulePass> passes, final List<ModuleSource> sources, final DBSearchExpression filter, final boolean parallel) {
        super(databaseInterface);
        this.sources = sources;
        this.passes = passes;
        this.filter = filter;
        this.parallel = parallel;
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
            if (parallel) {
                executePassParallel(p);
            } else {
                executePass(p);
            }
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

    private void executePassParallel(final ModulePass p) {
        final ExecutorService threadPool = Executors.newFixedThreadPool(8);
        sources.forEach(source -> source.run(getDatabaseInterface(), m -> {
            if (m.getLatestVersion() != null && (filter == null || filter.matches(m))) {
                threadPool.submit(() -> {
                    p.preprocessModule(m);
                    p.processModule(m);
                    p.postprocessModule(m);

                    if (p.isSaveDatabase()) {
                        synchronized (saveDatabaseLock) {
                            saveDatabase = true;
                        }
                    }
                });
            }
        }));
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.DAYS);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void executePass(final ModulePass p) {
        sources.forEach(source -> source.run(getDatabaseInterface(), m -> {
            if (m.getLatestVersion() != null && (filter == null || filter.matches(m))) {
                p.preprocessModule(m);
                p.processModule(m);
                p.postprocessModule(m);

                if (p.isSaveDatabase()) {
                    saveDatabase = true;
                }
            }
        }));
    }

    protected void preprocess() {
    }

    protected void postprocess() {
    }
}
