package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.Counter;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class CountPredefTask {

    private static class Pass extends ObjectCSVPass {
        final private Counter c = new Counter();
        final private Set<Integer> predef = new HashSet<>();
        final private Counter c1 = new Counter();
        final private Counter c2 = new Counter();

        final private Set<Integer> setAll = new HashSet<>();
        final private Counter cAll = new Counter();

        @Override
        protected void processObject(final DoorsObject object) {
            final String srcId = object.getAttributes().get("SourceID");
            final String ot = object.getAttributes().get("Object Type");
            if (object.isHeading() || ot == null || srcId == null || srcId.contains("SB-") || srcId.contains("STLH-")) {
                return;
            }
            if (!ot.equals("predefinition") && !ot.isEmpty()) {
                setAll.add(object.getText().hashCode());
                cAll.inc();
            }
            if (ot.equals("predefinition")) {
                predef.add(object.getText().hashCode());
                c.inc();
                ;
                if (object.getText().contains("Name, Vorname") || object.getText().contains("FOSS") ||
                        object.getText().contains("Last name, first name:") ||
                        (object.getText().contains("Auftragnehmer") && object.getText().contains("Auftraggeber"))) {
                    c1.inc();
                } else if (object.getText().contains("Range:") || object.getText().contains("Name:") || object.getText().contains("»keine«") || object.getText().contains("Spezifikation:") || object.getText().contains("Wertebereich:")) {
                    c2.inc();
                }
            }
        }

        @Override
        public void postprocess() {
            System.out.println("unique predefs: " + predef.size());
            System.out.println("total predefs: " + c.get());
            System.out.println("davon kopierte vorlagen: " + c1.get());
            System.out.println("davon keine predef: " + c2.get());
            System.out.println("unique objects: " + setAll.size());
            System.out.println("total objects: " + cAll.get());
        }
    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).buildAndRun();
    }

}
