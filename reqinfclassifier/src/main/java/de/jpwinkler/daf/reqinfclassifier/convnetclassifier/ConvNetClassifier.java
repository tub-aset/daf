package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;

public class ConvNetClassifier extends Classifier<String> {

    public ConvNetClassifier(final ClassifierContext context) {
        super(context);
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        return "(convnet)not_implemented";
    }

}
