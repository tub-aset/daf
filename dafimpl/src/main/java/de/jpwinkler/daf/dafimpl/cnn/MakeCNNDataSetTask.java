package de.jpwinkler.daf.dafimpl.cnn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.AllModulesSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.structuralclassifier.StructuralClassifier;

public class MakeCNNDataSetTask {

    private static class Pass extends ObjectCSVPass {
        private int inf = 0;
        private int req = 0;

        private final Set<String> writtenObjects = new HashSet<>();
        private final PrintWriter writer;
        private final ClassifierContext classifierContext = ClassifierContext.getInstance();
        private final StructuralClassifier structuralClassifier = new StructuralClassifier(classifierContext);

        public Pass() throws FileNotFoundException {
            writer = new PrintWriter(new FileOutputStream("temp/cnn_dataset.txt"));
        }

        @Override
        public void postprocessModule(final DBModule module) {
            System.out.println(module.getFullName());
        }

        @Override
        protected void processObject(final DoorsObject object) {
            if (object.isHeading()) {
                return;
            }
            final String srcId = object.getAttributes().get(Attributes.SOURCE_ID);
            if (srcId == null || srcId.startsWith("STLH-") || srcId.startsWith("SB-")) {
                return;
            }
            final String structuralType = structuralClassifier.classify(object);
            if (structuralType == null || !structuralType.contains("sentence")) {
                return;
            }

            String text = classifierContext.preprocess(object.getText(), " ");
            text = text.toLowerCase();
            text = text.replace("\n", " ");
            text = text.replace("\r", " ");
            text = text.replace("\t", " ");
            while (text.contains("  ")) {
                text = text.replace("  ", " ");
            }

            if (writtenObjects.contains(text)) {
                return;
            }
            writtenObjects.add(text);

            final String ot = object.getAttributes().get(Attributes.OBJECT_TYPE_ORIGINAL);
            if ("information".equals(ot)) {
                inf++;
                writer.write("information " + text + "\n");
            }
            if ("requirement".equals(ot)) {
                req++;
                writer.write("requirement " + text + "\n");
            }
        }

        @Override
        public void postprocess() {
            System.out.println(inf);
            System.out.println(req);
            System.out.println(inf + req);
            writer.close();
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new AllModulesSource()).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
