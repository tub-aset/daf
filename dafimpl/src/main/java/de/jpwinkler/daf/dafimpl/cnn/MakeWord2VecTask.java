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
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.structuralclassifier.StructuralClassifier;

public class MakeWord2VecTask {

    private static class Pass extends ObjectCSVPass {

        private final Set<String> writtenObjects = new HashSet<>();
        private final ClassifierContext classifierContext = ClassifierContext.getInstance();
        private final StructuralClassifier syntacticClassifier = new StructuralClassifier(classifierContext);
        private final PrintWriter writerW2V;

        public Pass() throws FileNotFoundException {
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
            if (writtenObjects.contains(text)) {
                return;
            }
            if (object.isHeading()) {
                return;
            }
            final String srcId = object.getAttributes().get(Attributes.SOURCE_ID);
            if (srcId == null || srcId.startsWith("STLH-") || srcId.startsWith("SB-")) {
                return;
            }
            final String syntacticType = syntacticClassifier.classify(object);
            if (syntacticType == null || !syntacticType.contains("sentence")) {
                return;
            }

            writtenObjects.add(text);
            writerW2V.write(text + "\n");
        }

        @Override
        public void preprocessModule(final DBModule module) {
            System.out.println(module.getFullName());
        }

        @Override
        public void postprocessModule(final DBModule module) {
            System.out.println(writtenObjects.size());
        }

        @Override
        public void postprocess() {
            writerW2V.close();
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new FolderSource("/Body")).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
