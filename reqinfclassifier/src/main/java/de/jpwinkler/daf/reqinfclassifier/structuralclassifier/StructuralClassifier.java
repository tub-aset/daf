package de.jpwinkler.daf.reqinfclassifier.structuralclassifier;

import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.SequenceClassifier;

public class StructuralClassifier extends SequenceClassifier<String> {

    public StructuralClassifier(final ClassifierContext context) {
        super(context);
        getClassifiers().add(new HeadingClassifier(context));
        getClassifiers().add(new IdentifierClassifier(context));
        getClassifiers().add(new ItemizationClassifier(context));
        getClassifiers().add(new EnumerationClassifier(context));
        getClassifiers().add(new MultiLineDefinitionClassifier(context));
        getClassifiers().add(new DefinitionClassifier(context));
        getClassifiers().add(new MathExpressionClassifier(context));
        getClassifiers().add(new BooleanExpressionClassifier(context));
        getClassifiers().add(new SentenceClassifier(context));
    }

}
