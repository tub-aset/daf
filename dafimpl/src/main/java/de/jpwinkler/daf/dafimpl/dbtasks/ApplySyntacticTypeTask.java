package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.search.HasTagsSearchExpression;
import de.jpwinkler.daf.doorsdb.tasks.AllModulesSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleCSVPass;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.syntacticclassifier.SyntacticClassifier;

public class ApplySyntacticTypeTask {

    private static class Pass extends ModuleCSVPass {

        protected static final String ATTRIBUTE_NAME = "__SyntacticType";
        private final SyntacticClassifier classifier = new SyntacticClassifier(ClassifierContext.getInstance());

        @Override
        protected void processParsedModule(final DoorsModule module) {
            // if (parsedModule.findAttributeDefinition(ATTRIBUTE_NAME) == null)
            // {
            // final AttributeDefinition attrDef =
            // CSVFactory.eINSTANCE.createAttributeDefinition();
            // attrDef.setName(ATTRIBUTE_NAME);
            // parsedModule.getAttributeDefinitions().add(attrDef);
            // } else {
            // return;
            // }
            final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
            module.accept(new DoorsTreeNodeVisitor() {
                @Override
                public boolean visitPreTraverse(final DoorsObject object) {
                    fixedThreadPool.submit(() -> classifier.classify(object));
                    // if (!object.getAttributes().containsKey(ATTRIBUTE_NAME))
                    // {
                    // object.getAttributes().put(ATTRIBUTE_NAME, );
                    // }
                    return true;
                }
            });
            fixedThreadPool.shutdown();
            try {
                fixedThreadPool.awaitTermination(1, TimeUnit.HOURS);
            } catch (final InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // saveModule();
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).withSource(new AllModulesSource()).withFilter(new HasTagsSearchExpression("lang:de")).buildAndRun();
    }
}
