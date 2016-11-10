package de.jpwinkler.daf.reqinfclassifier;

import java.util.ArrayList;
import java.util.List;

public class SequenceClassifier<O> extends Classifier<O> {

    private final List<Classifier<O>> classifiers = new ArrayList<>();
    private final O defaultOutput;

    public SequenceClassifier(final ClassifierContext context, final O defaultOutput) {
        super(context);
        this.defaultOutput = defaultOutput;
    }

    public List<Classifier<O>> getClassifiers() {
        return classifiers;
    }

    @Override
    protected O run(final ExampleContext context) {
        for (final Classifier<O> classifier : classifiers) {
            final O c = classifier.run(context);
            if (c != null) {
                return c;
            }
        }
        return defaultOutput;
    }

}
