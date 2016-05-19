package de.jpwinkler.daf.doorsdb.tasks;

import java.io.IOException;

import de.jpwinkler.daf.dafcore.util.Counter;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public abstract class ModuleTask extends DoorsDBTask {

    private boolean saveDatabase = false;

    private final ModuleSource source;

    private DBModule module = null;

    public ModuleTask(final DoorsDBInterface databaseInterface) {
        this(databaseInterface, new AllModulesSource(databaseInterface));
    }

    public ModuleTask(final DoorsDBInterface databaseInterface, final ModuleSource source) {
        super(databaseInterface);
        this.source = source;
    }

    @Override
    public void execute() {
        preprocess();
        final Counter moduleCounter = new Counter();
        source.run(m -> {
            module = m;
            preprocessModule();
            processModule(m);
            postprocessModule();
            moduleCounter.inc();
        });
        System.out.println(moduleCounter.get() + " modules processed.");
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

    protected void preprocessModule() {
    }

    protected void postprocessModule() {
    }

    protected DBModule getModule() {
        return module;
    }

    protected abstract void processModule(DBModule module);

    protected void saveDatabase() {
        saveDatabase = true;
    }
}
