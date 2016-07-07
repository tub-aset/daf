package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aliasi.classify.ConfusionMatrix;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.tasks.ModuleListSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class EvalReqInfClassifierTask {

    private static final String[] ALLOWED_TYPES = new String[] { "heading", "requirement", "information" };

    private static final Pattern PATTERN = Pattern.compile("\\(([a-z]*)\\)([a-z]*)");

    private static class Pass extends ObjectCSVPass {

        private final ConfusionMatrix totalConfusionMatrix;
        private final ConfusionMatrix convnetConfusionMatrix;
        private final ConfusionMatrix clusterConfusionMatrix;
        private final ConfusionMatrix templateConfusionMatrix;

        public Pass() {
            totalConfusionMatrix = new ConfusionMatrix(ALLOWED_TYPES);
            convnetConfusionMatrix = new ConfusionMatrix(ALLOWED_TYPES);
            clusterConfusionMatrix = new ConfusionMatrix(ALLOWED_TYPES);
            templateConfusionMatrix = new ConfusionMatrix(ALLOWED_TYPES);
        }

        @Override
        protected void processObject(final DoorsObject object) {
            final String reference = object.getAttributes().get("Object Type");

            if (object.isHeading()) {
                return;
            }

            if (reference == null || reference.isEmpty()) {
                return;
            }

            final String classifiedOt = object.getAttributes().get("__classified_ot__");
            if (classifiedOt == null || classifiedOt.isEmpty()) {

                return;
            }

            final Matcher matcher = PATTERN.matcher(classifiedOt);
            if (matcher.matches()) {
                final String classifiedBy = matcher.group(1);
                final String response = matcher.group(2);

                totalConfusionMatrix.increment(reference, response);
                if ("convnet".equals(classifiedBy)) {
                    convnetConfusionMatrix.increment(reference, response);
                } else if ("template".equals(classifiedBy)) {
                    templateConfusionMatrix.increment(reference, response);
                } else if ("cluster".equals(classifiedBy)) {
                    clusterConfusionMatrix.increment(reference, response);
                } else if ("syntactic".equals(classifiedBy)) {

                } else {
                    System.out.println(classifiedBy);
                }
            } else {
                System.out.println("does not match!!! " + classifiedOt);
            }
        }

        @Override
        public void postprocess() {
            System.out.println("total conf matrix");
            System.out.println("--------------------");
            System.out.println(totalConfusionMatrix.toString());
            System.out.println();

            System.out.println("template conf matrix");
            System.out.println("--------------------");
            System.out.println(templateConfusionMatrix.toString());
            System.out.println();

            System.out.println("cluster conf matrix");
            System.out.println("--------------------");
            System.out.println(clusterConfusionMatrix.toString());
            System.out.println();

            System.out.println("convnet conf matrix");
            System.out.println("--------------------");
            System.out.println(convnetConfusionMatrix.toString());
            System.out.println();

            System.out.println(convnetConfusionMatrix.totalAccuracy());
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new ModuleListSource(Arrays.asList("/Body/B2.1 - Components/DM/DM_Star3_Fachinhalte"))).buildAndRun();
    }

}
