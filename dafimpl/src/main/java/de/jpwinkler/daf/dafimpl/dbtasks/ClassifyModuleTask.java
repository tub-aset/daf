package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.DoorsModuleUtil;
import de.jpwinkler.daf.dafimpl.Attributes;
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ReqInfClassifier;
import de.jpwinkler.daf.reqinfclassifier.structuralclassifier.StructuralClassifier;

public class ClassifyModuleTask {

    private static class Pass extends ObjectCSVPass {

        private final ReqInfClassifier reqInfClassifier = new ReqInfClassifier(ClassifierContext.getInstance(), "stlh-6.3.1");
        private final StructuralClassifier structuralClassifier = new StructuralClassifier(ClassifierContext.getInstance());

        @Override
        protected void preprocessParsedModule(final DoorsModule module) {
            DoorsModuleUtil.ensureAttributeDefinition(module, Attributes.OBJECT_TYPE_PREDICTED);
            DoorsModuleUtil.ensureAttributeDefinition(module, Attributes.STRUCTURAL_TYPE_PREDICTED);
        }

        @Override
        protected void processObject(final DoorsObject object) {
            object.getAttributes().put(Attributes.OBJECT_TYPE_PREDICTED, reqInfClassifier.classify(object));
            object.getAttributes().put(Attributes.STRUCTURAL_TYPE_PREDICTED, structuralClassifier.classify(object));
        }

        @Override
        protected void postprocessParsedModule(final DoorsModule module) {
            saveModule();
        }
    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new FolderSource("/Body/B2.1 - Components/DM")).buildAndRun();
    }


}
