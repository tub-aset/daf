package de.jpwinkler.daf.dafimpl.maintenance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.tasks.ModulePass;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;

public class AddTemplateVersionTagsTask {

    private static class Pass extends ModulePass {

        private static final Pattern TEMPLATE_VERSION_PATTERN = Pattern.compile("[0-9.]*");

        @Override
        public void processModule(final DBModule module) {
            final String tempVer = module.getLatestVersion().getAttributes().get("Template Version");
            if (tempVer != null && TEMPLATE_VERSION_PATTERN.matcher(tempVer).matches()) {
                System.out.println(tempVer);
                getDatabaseInterface().addTag(module, "template:" + tempVer);
                saveDatabase();
            } else {
                System.out.println("Template Version missing!");
            }
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).buildAndRun();
    }
}
