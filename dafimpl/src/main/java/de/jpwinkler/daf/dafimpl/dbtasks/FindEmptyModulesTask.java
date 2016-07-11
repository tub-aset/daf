package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.Counter;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class FindEmptyModulesTask {

    private static class Pass extends ObjectCSVPass {

        final Counter c = new Counter();

        @Override
        public void preprocessModule(final DBModule module) {
            c.set(0);
        }

        @Override
        protected void processObject(final DoorsObject object) {
            final String sourceID = object.getAttributes().get(Attributes.SOURCE_ID);
            if (!object.isHeading() && (sourceID == null || (!sourceID.startsWith("SB-") && !sourceID.startsWith("STLH-")))) {
                c.inc();
            }
        }

        @Override
        public void postprocessModule(final DBModule module) {
            if (c.get() == 0) {
                getDatabaseInterface().addTag(module, "content:empty");
                saveDatabase();
            } else if (c.get() < 5) {
                getDatabaseInterface().addTag(module, "content:almost_empty");
                saveDatabase();
            }

        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).buildAndRun();
    }

}
