package de.jpwinkler.daf.reqinfclassifier;

import de.jpwinkler.daf.reqinfclassifier.clusterclassifier.ClusterClassifier;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassifier;
import de.jpwinkler.daf.reqinfclassifier.structuralclassifier.StructuralClassifier;
import de.jpwinkler.daf.reqinfclassifier.templateclassifier.TemplateClassifier;

public class ReqInfClassifier extends Classifier<ClassificationResult> {

    private final ClusterClassifier clusterClassifier;
    private final TemplateClassifier templateClassifier;
    private final StructuralClassifier structuralClassifier;
    private final ConvNetClassifier convNetClassifier;

    public ReqInfClassifier(final ClassifierContext context, final String templateName) {
        super(context);
        templateClassifier = new TemplateClassifier(context, templateName);
        clusterClassifier = new ClusterClassifier(context);
        structuralClassifier = new StructuralClassifier(context);
        convNetClassifier = new ConvNetClassifier(context);
    }

    @Override
    protected ClassificationResult run(final ExampleContext context) {

        ClassificationResult result;

        if (context.getExample().isHeading()) {
            result = new ClassificationResult("heading", "heading");
        } else {
            result = templateClassifier.classify(context);
            if (result == null) {
                final String structuralType = structuralClassifier.classify(context);
                result = clusterClassifier.classify(context);
                if (result == null && structuralType.contains("sentence")) {
                    result = convNetClassifier.classify(context);
                }
            }
        }

        return result;
    }

}
