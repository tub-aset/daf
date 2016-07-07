package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.jpwinkler.daf.dafcore.model.csv.AttributeDefinition;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.tasks.FolderSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ReqInfClassifier;
import de.jpwinkler.daf.reqinfclassifier.syntacticclassifier.SyntacticClassifier;

public class ClassifyModuleTask {

    private static class Pass extends ObjectCSVPass {

        private static final String CLASSIFIED_AS_ATTRIBUTE = "__classified_as";
        private static final String SYNTACTIC_TYPE_ATTRIBUTE = "__syntactic_type";

        private static ReqInfClassifier reqInfClassifier = new ReqInfClassifier(ClassifierContext.getInstance(), "stlh-6.3.1");
        private static SyntacticClassifier syntacticClassifier = new SyntacticClassifier(ClassifierContext.getInstance());

        @Override
        protected void preprocessParsedModule(final DoorsModule module) {
            if (module.findAttributeDefinition(CLASSIFIED_AS_ATTRIBUTE) == null) {
                final AttributeDefinition ad = CSVFactory.eINSTANCE.createAttributeDefinition();
                ad.setName(CLASSIFIED_AS_ATTRIBUTE);
                module.getAttributeDefinitions().add(ad);
            }
            if (module.findAttributeDefinition(SYNTACTIC_TYPE_ATTRIBUTE) == null) {
                final AttributeDefinition ad = CSVFactory.eINSTANCE.createAttributeDefinition();
                ad.setName(SYNTACTIC_TYPE_ATTRIBUTE);
                module.getAttributeDefinitions().add(ad);
            }
        }

        @Override
        protected void processObject(final DoorsObject object) {
            final String label = reqInfClassifier.classify(object);
            final String syntacticType = syntacticClassifier.classify(object);
            object.getAttributes().put(CLASSIFIED_AS_ATTRIBUTE, label);
            object.getAttributes().put(SYNTACTIC_TYPE_ATTRIBUTE, syntacticType);
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
