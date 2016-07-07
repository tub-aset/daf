package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.syntacticclassifier.SyntacticClassifier;

public class MakeCNNDataSetTask {

    private static class Pass extends ObjectCSVPass {
        private int inf = 0;
        private int req = 0;

        private final Set<String> writtenObjects = new HashSet<>();
        private final PrintWriter writerTrain;
        private final PrintWriter writerTest;
        private final Random r = new Random(24367);
        private final ClassifierContext classifierContext = ClassifierContext.getInstance();
        private final SyntacticClassifier syntacticClassifier = new SyntacticClassifier(classifierContext);
        private final PrintWriter writerW2V;

        public Pass() throws FileNotFoundException {
            writerTrain = new PrintWriter(new FileOutputStream("train.txt"));
            writerTest = new PrintWriter(new FileOutputStream("test.txt"));
            writerW2V = new PrintWriter(new FileOutputStream("w2v.txt"));
        }

        @Override
        protected void processObject(final DoorsObject object) {
            String text = classifierContext.preprocess(object.getText(), " ");
            text = text.toLowerCase();
            text = text.replace("\n", " ");
            text = text.replace("\r", " ");
            text = text.replace("\t", " ");
            while (text.contains("  ")) {
                text = text.replace("  ", " ");
            }
            // if (writtenObjects.contains(text)) {
            // return;
            // }
            if (object.isHeading()) {
                return;
            }
            final String srcId = object.getAttributes().get("SourceID");
            // if (srcId == null || srcId.startsWith("STLH-") ||
            // srcId.startsWith("SB-")) {
            // return;
            // }
            final String syntacticType = syntacticClassifier.classify(object);
            if (syntacticType == null || !syntacticType.contains("sentence")) {
                return;
            }
            final String ot = object.getAttributes().get("Object Type Corrected");

            writtenObjects.add(text);
            final PrintWriter w = r.nextFloat() > 0.9 ? writerTest : writerTrain;
            if ("information".equals(ot)) {
                inf++;
                w.write("information " + text + "\n");
                writerW2V.write(text + "\n");
            }
            if ("requirement".equals(ot)) {
                req++;
                w.write("requirement " + text + "\n");
                writerW2V.write(text + "\n");
            }
        }

        @Override
        public void postprocess() {
            System.out.println(inf);
            System.out.println(req);
            System.out.println(inf + req);
            writerTest.close();
            writerTrain.close();
            writerW2V.close();
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new FolderSource("/Body")).withFilter(new HasTagsSearchExpression("case_study")).buildAndRun();
    }


}
