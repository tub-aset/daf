package de.jpwinkler.daf.dafimpl.evaluation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import com.aliasi.classify.ConfusionMatrix;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.tasks.ModuleListSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class EvaluateStructuralTypeTask {

    private static class Pass extends ObjectCSVPass {
        private final ConfusionMatrix confusionMatrix;

        public Pass() {
            confusionMatrix = new ConfusionMatrix(new String[] { "heading", "sentence", "itemization", "definition", "other", "enumeration", "math_expression", "identifier" });
        }

        @Override
        protected void processObject(final DoorsObject object) {
            final String reference = object.getAttributes().get(Attributes.STRUCTURAL_TYPE_CORRECT);
            final String response = object.getAttributes().get(Attributes.STRUCTURAL_TYPE_PREDICTED);

            confusionMatrix.increment(reference, response);
        }

        @Override
        public void postprocess() {
            System.out.println(confusionMatrix);
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new ModuleListSource(Arrays.asList("/Body/B2.1 - Components/DM/DM_Star3_Fachinhalte"))).buildAndRun();
    }

}
