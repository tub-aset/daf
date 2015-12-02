package de.jpwinkler.daf.dataprocessing.utils;

import java.io.IOException;

import de.jpwinkler.daf.dafcore.csv.ModuleWriter;
import de.jpwinkler.daf.dafcore.csv.SimpleModuleWriter;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dataprocessing.DoorsModuleSink;

public class ModuleWriterSink extends DoorsModuleSink {

    ModuleWriter writer = new SimpleModuleWriter(System.out);

    @Override
    protected void run(final DoorsModule module) {
        try {
            writer.writeModule(module);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
