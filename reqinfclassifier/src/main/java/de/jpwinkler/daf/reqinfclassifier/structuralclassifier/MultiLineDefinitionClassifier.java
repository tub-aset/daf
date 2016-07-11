package de.jpwinkler.daf.reqinfclassifier.structuralclassifier;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;

public class MultiLineDefinitionClassifier extends Classifier<String> {

    public MultiLineDefinitionClassifier(final ClassifierContext context) {
        super(context);
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        if (context.getLines().size() >= 2 && context.getLines().get(0).endsWith(":")) {
            return "multiline_definition";
        } else {
            return null;
        }
    }

}
