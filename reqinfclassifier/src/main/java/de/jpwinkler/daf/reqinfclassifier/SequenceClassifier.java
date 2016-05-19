package de.jpwinkler.daf.reqinfclassifier;

import java.util.ArrayList;
import java.util.List;

public class SequenceClassifier<E> extends Classifier<E> {

    private final List<Classifier<E>> classifiers = new ArrayList<>();

    public SequenceClassifier(final ClassifierContext context) {
        super(context);
    }

    public List<Classifier<E>> getClassifiers() {
        return classifiers;
    }

    @Override
    protected E run(final DoorsObjectContext context) {
        for (final Classifier<E> classifier : classifiers) {
            final E c = classifier.run(context);
            if (c != null) {
                return c;
            }
        }
        return null;
    }

}
