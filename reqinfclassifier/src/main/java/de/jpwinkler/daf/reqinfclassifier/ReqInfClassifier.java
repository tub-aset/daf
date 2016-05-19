package de.jpwinkler.daf.reqinfclassifier;

import de.jpwinkler.daf.reqinfclassifier.clusterclassifier.ClusterClassifier;
import de.jpwinkler.daf.reqinfclassifier.convnetclassifier.ConvNetClassifier;
import de.jpwinkler.daf.reqinfclassifier.syntacticclassifier.SyntacticClassifier;
import de.jpwinkler.daf.reqinfclassifier.templateclassifier.TemplateClassifier;

public class ReqInfClassifier extends Classifier<String> {

    private final ClusterClassifier clusterClassifier;
    private final TemplateClassifier templateClassifier;
    private final SyntacticClassifier syntacticClassifier;
    private final ConvNetClassifier convNetClassifier;

    public ReqInfClassifier(final ClassifierContext context, final String templateName) {
        super(context);
        templateClassifier = new TemplateClassifier(context, templateName);
        clusterClassifier = new ClusterClassifier(context);
        syntacticClassifier = new SyntacticClassifier(context);
        convNetClassifier = new ConvNetClassifier(context);
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        final String syntacticType = syntacticClassifier.classify(context);

        if (syntacticType.equals("heading")) {
            return "(syntactic)heading";
        }

        String label = null;

        label = templateClassifier.classify(context);
        if (label != null) {
            return "(template)" + label;
        }

        label = clusterClassifier.classify(context);
        if (label != null) {
            return "(cluster)" + label;
        }


        if (syntacticType.contains("sentence")) {
            label = convNetClassifier.classify(context);
            if (label != null) {
                return "(convnet)" + label;
            }
        }

        return null;
    }

}
