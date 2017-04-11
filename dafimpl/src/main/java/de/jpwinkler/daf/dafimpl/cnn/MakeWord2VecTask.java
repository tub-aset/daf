package de.jpwinkler.daf.dafimpl.cnn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafimpl.util.DoorsObjectWrapper;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.AllModulesSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.structuralclassifier.StructuralClassifier;

public class MakeWord2VecTask {

    private static class Pass extends ObjectCSVPass {

        private final Set<String> writtenObjects = new HashSet<>();
        private final ClassifierContext classifierContext = ClassifierContext.getInstance();
        private final StructuralClassifier structuralClassifier = new StructuralClassifier(classifierContext);
        private final PrintWriter writerW2V;

        public Pass() throws FileNotFoundException {
            writerW2V = new PrintWriter(new FileOutputStream("temp/w2v.txt"));
        }

        @Override
        protected void processObject(final DoorsObject object) {
            final String text = StringUtils.join(classifierContext.convNetPreprocess(object.getText()), ' ');
            // if (writtenObjects.contains(text)) {
            // return;
            // }
            final String structuralType = structuralClassifier.classify(new DoorsObjectWrapper(object));
            if (!object.isHeading() && (structuralType == null || !structuralType.contains("sentence"))) {
                return;
            }

            // writtenObjects.add(text);
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
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new AllModulesSource()).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }

}
