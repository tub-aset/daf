package de.jpwinkler.daf.doorsdb.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public abstract class ModuleCSVTask extends ModuleTask {

    private boolean saveModule = false;
    private DoorsModule parsedModule;

    public ModuleCSVTask(final DoorsDBInterface databaseInterface) {
        super(databaseInterface);
    }

    public ModuleCSVTask(final DoorsDBInterface databaseInterface, final ModuleSource source) {
        super(databaseInterface, source);
    }

    @Override
    protected void processModule(final DBModule module) {
        try {
            saveModule = false;
            final File f = new File(module.getLatestVersion().getCsvLocation());
            parsedModule = new ModuleCSVParser().parseCSV(f);
            preprocessParsedModule();
            processParsedModule();
            postprocessParsedModule();
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

    protected void preprocessParsedModule() {
    }

    protected void postprocessParsedModule() {
    }

    protected abstract void processParsedModule();

    protected DoorsModule getParsedModule() {
        return parsedModule;
    }

    protected void saveModule() {
        saveModule = true;
    }

}
