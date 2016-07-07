package de.jpwinkler.daf.doorsdb.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.util.CSVParseException;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public abstract class ModuleCSVPass extends ModulePass {

    private boolean saveModule;
    private DoorsModule parsedModule;
    private DBModule module;

    protected void preprocessParsedModule(final DoorsModule module) {
    }

    protected void postprocessParsedModule(final DoorsModule module) {
    }

    @Override
    protected void processModule(final DBModule module) {
        this.module = module;
        try {
            saveModule = false;
            final File f = new File(module.getLatestVersion().getCsvLocation());
            parsedModule = new ModuleCSVParser().parseCSV(f);
            preprocessParsedModule(parsedModule);
            processParsedModule(parsedModule);
            postprocessParsedModule(parsedModule);
            if (saveModule) {
                try (ModuleCSVWriter writer = new ModuleCSVWriter(new FileOutputStream(f))) {
                    writer.writeModule(parsedModule);
                }
            }
        } catch (IOException | CSVParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected abstract void processParsedModule(DoorsModule module);

    public DBModule getModule() {
        return module;
    }

    protected DoorsModule getParsedModule() {
        return parsedModule;
    }

    protected void saveModule() {
        saveModule = true;
    }

}
