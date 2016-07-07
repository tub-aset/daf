package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class GuessObjectTypePresenceTask {

    private static class Pass extends ObjectCSVPass {

        private List<DoorsObject> objectsWithoutOT;
        private List<DoorsObject> objectsWithOT;

        @Override
        public void preprocessModule(final DBModule module) {
            objectsWithoutOT = new ArrayList<>();
            objectsWithOT = new ArrayList<>();
        }

        @Override
        protected void processObject(final DoorsObject object) {
            if (object.getAttributes().containsKey("SourceID") && (object.getAttributes().get("SourceID").startsWith("STLH-") || object.getAttributes().get("SourceID").startsWith("SB-"))) {
                return;
            }
            if (!object.getAttributes().containsKey("Object Type") || object.getAttributes().get("Object Type").isEmpty()) {
                objectsWithoutOT.add(object);
            } else {
                objectsWithOT.add(object);
            }
        }

        @Override
        public void postprocessModule(final DBModule module) {
            if ((double) objectsWithoutOT.size() / (objectsWithOT.size() + objectsWithoutOT.size()) > 0.8) {
                System.out.println(getModule().getFullName() + " has no OT info");
                getDatabaseInterface().addTag(getModule(), "quality:no_objecttype");
            } else {
                System.out.println(getModule().getFullName() + " LOOKS GOOD!");
            }
            saveDatabase();
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).buildAndRun();
    }

}
