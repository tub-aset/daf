package de.jpwinkler.daf.dafimpl.maintenance;

import java.io.IOException;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.tasks.ModulePass;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.libs.doorsbridge.DoorsException;

public class UpdateAllModulesTask {

    private static class Pass extends ModulePass {

        @Override
        protected void processModule(final DBModule module) {
            try {
                System.out.print(module.getFullName() + "... ");
                final boolean updated = getDatabaseInterface().updateModule(module);
                System.out.println(updated ? "added new version." : "up to date.");
            } catch (DoorsException | IOException e) {
                getDatabaseInterface().addTag(module, "UpdateError: " + e.getMessage());
                e.printStackTrace();
                try {
                    getDatabaseInterface().saveDB();
                } catch (final IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

    }

    public static void main(final String[] args) throws IOException {
        new ModuleTaskBuilder().withPass(new Pass()).buildAndRun();
    }

}
