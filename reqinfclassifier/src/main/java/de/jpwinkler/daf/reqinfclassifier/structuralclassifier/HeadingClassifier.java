package de.jpwinkler.daf.reqinfclassifier.structuralclassifier;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;

public class HeadingClassifier extends Classifier<String> {

    public HeadingClassifier(final ClassifierContext context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        if (context.getDoorsObject().isHeading()) {
            return "heading";
        } else {
            return null;
        }
    }

}
