package de.jpwinkler.daf.reqinfclassifier.structuralclassifier;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;

public class HeadingClassifier extends Classifier<String> {

    public HeadingClassifier(final ClassifierContext context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected String run(final ExampleContext context) {
        if (context.getExample().isHeading()) {
            return "heading";
        } else {
            return null;
        }
    }

}
