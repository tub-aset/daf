package de.jpwinkler.daf.reqinfclassifier;

import de.jpwinkler.daf.reqinfclassifier.clusterclassifier.ClusterClassifier;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassifier;
import de.jpwinkler.daf.reqinfclassifier.structuralclassifier.StructuralClassifier;
import de.jpwinkler.daf.reqinfclassifier.templateclassifier.TemplateClassifier;

public class ReqInfClassifier extends Classifier<ReqInfClassificationResult> {

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
    protected ReqInfClassificationResult run(final ExampleContext context) {

        final ReqInfClassificationResult result = new ReqInfClassificationResult();

        if (context.getExample().isHeading()) {
            result.setObjectType("heading");
        } else {
            String label = templateClassifier.classify(context);
            if (label != null) {
                result.setObjectType(label);
            } else {
                final String structuralType = structuralClassifier.classify(context);
                label = clusterClassifier.classify(context);
                if (label != null) {
                    result.setObjectType(label);
                } else if (structuralType.contains("sentence")) {
                    final ConvNetClassificationResult convNetResult = convNetClassifier.classify(context);
                    result.setObjectType(convNetResult.getObjectType());
                }

            }
        }

        return result;
    }

}
