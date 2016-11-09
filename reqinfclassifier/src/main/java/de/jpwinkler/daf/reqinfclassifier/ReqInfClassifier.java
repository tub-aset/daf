package de.jpwinkler.daf.reqinfclassifier;

import de.jpwinkler.daf.reqinfclassifier.clusterclassifier.ClusterClassifier;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassifier;
import de.jpwinkler.daf.reqinfclassifier.structuralclassifier.StructuralClassifier;
import de.jpwinkler.daf.reqinfclassifier.templateclassifier.TemplateClassifier;

public class ReqInfClassifier extends Classifier<String> {

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
    protected String run(final DoorsObjectContext context) {

        if (context.getDoorsObject().isHeading()) {
            return "(syntactic)heading";
        }

        String label = null;

        label = templateClassifier.classify(context);
        if (label != null) {
            return "(template)" + label;
        }

        final String structuralType = structuralClassifier.classify(context);
        label = clusterClassifier.classify(context);
        if (label != null) {
            return "(cluster)" + label;
        }

        if (structuralType.contains("sentence")) {
            label = convNetClassifier.classify(context);
            if (label != null) {
                return "(convnet)" + label;
            }
        }

        return null;
    }

}
