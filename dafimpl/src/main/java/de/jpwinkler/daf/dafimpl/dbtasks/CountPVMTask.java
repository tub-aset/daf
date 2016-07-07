package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class CountPVMTask {

    private static class Pass extends ObjectCSVPass {
        final Set<Integer> positive = new HashSet<>();
        final Set<Integer> negative = new HashSet<>();

        @Override
        protected void processObject(final DoorsObject object) {
            final String srcId = object.getAttributes().get("SourceID");
            if (object.isHeading() || srcId == null || srcId.contains("SB-") || object.getAttributes().get("SourceID").contains("STLH-")) {
                return;
            }
            final String pvm = object.getAttributes().get("Potential Verification Method").toLowerCase();
            if (pvm != null && pvm.contains("develop")) {
                positive.add(object.getText().hashCode());
            } else if (pvm != null && !pvm.contains("t.b.d")) {
                negative.add(object.getText().hashCode());
            }
        }

        @Override
        public void postprocess() {
            System.out.println(positive.size());
            System.out.println(negative.size());
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).buildAndRun();
    }

}
