package de.jpwinkler.daf.dafimpl.analysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class CountPVMTask {

    private static class Pass extends ObjectCSVPass {
        final Set<Integer> positive = new HashSet<>();
        final Set<Integer> negative = new HashSet<>();

        @Override
        protected void processObject(final DoorsObject object) {
            final String srcId = object.getAttributes().get(Attributes.SOURCE_ID);
            if (object.isHeading() || srcId == null || srcId.contains("SB-") || srcId.contains("STLH-")) {
                return;
            }
            final String pvm = object.getAttributes().get(Attributes.POTENTIAL_VERIFICATION_METHOD).toLowerCase();
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
