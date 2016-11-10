package de.jpwinkler.daf.reqinfclassifier.structuralclassifier;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;

public class BooleanExpressionClassifier extends Classifier<String> {

    public BooleanExpressionClassifier(final ClassifierContext context) {
        super(context);
    }

    @Override
    protected String run(final ExampleContext context) {
        return null;
    }

}
