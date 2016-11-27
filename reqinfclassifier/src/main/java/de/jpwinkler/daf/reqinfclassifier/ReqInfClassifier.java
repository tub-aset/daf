package de.jpwinkler.daf.reqinfclassifier;

import de.jpwinkler.daf.reqinfclassifier.clusterclassifier.ClusterClassifier;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassifier;
import de.jpwinkler.daf.reqinfclassifier.headingclassifier.HeadingClassifier;
import de.jpwinkler.daf.reqinfclassifier.structuralclassifier.StructuralClassifier;
import de.jpwinkler.daf.reqinfclassifier.templateclassifier.TemplateClassifier;

public class ReqInfClassifier extends Classifier<ClassificationResult> {

    private final ClusterClassifier clusterClassifier;
    private final TemplateClassifier templateClassifier;
    private final StructuralClassifier structuralClassifier;
    private final ConvNetClassifier convNetClassifier;
    private final HeadingClassifier headingClassifier;

    public ReqInfClassifier(final ClassifierContext context, final String templateName) {
        super(context);
        headingClassifier = new HeadingClassifier(context);
        templateClassifier = new TemplateClassifier(context, templateName);
        clusterClassifier = new ClusterClassifier(context);
        structuralClassifier = new StructuralClassifier(context);
        convNetClassifier = new ConvNetClassifier(context);
    }

    @Override
    protected ClassificationResult run(final ExampleContext context) {

        ClassificationResult result;

        result = headingClassifier.classify(context);
        if (result != null) {
            return result;
        }

        result = templateClassifier.classify(context);
        if (result != null) {
            return result;
        }

        result = clusterClassifier.classify(context);
        if (result != null) {
            return result;
        }

        final String structuralType = structuralClassifier.classify(context);
        if (structuralType.contains("sentence")) {
            result = convNetClassifier.classify(context);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

}
