package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.csv.ModuleCSVParser;
import de.jpwinkler.daf.csv.ModuleCSVWriter;
import de.jpwinkler.daf.model.DoorsModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class ModuleCSVPass extends ModulePass {

    private boolean saveModule;
    private DoorsModule parsedModule;
    private DoorsModule module;

    protected void preprocessParsedModule(final DoorsModule module) {
    }

    protected void postprocessParsedModule(final DoorsModule module) {
    }

    @Override
    protected void processModule(final DoorsModule module) {
        this.module = module;
        try {
            saveModule = false;
            final File f = getDatabaseInterface().getCSVLocation(module.getLatestVersion()).toFile();
            parsedModule = new ModuleCSVParser().parseCSV(f);
            preprocessParsedModule(parsedModule);
            processParsedModule(parsedModule);
            postprocessParsedModule(parsedModule);
            if (saveModule) {
                try (ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(f))) {
                    writer.writeModule(parsedModule);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected abstract void processParsedModule(DoorsModule module);

    public DoorsModule getModule() {
        return module;
    }

    protected DoorsModule getParsedModule() {
        return parsedModule;
    }

    protected void saveModule() {
        saveModule = true;
    }

}
