package de.jpwinkler.daf.dataprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public class CollectorSink extends DoorsModuleSink {

    private final List<DoorsModule> modules = new ArrayList<>();

    @Override
    protected void run(final DoorsModule module) {
        modules.add(module);
    }

    public List<DoorsModule> getModules() throws IOException {
        runAll();
        return modules;
    }

}
