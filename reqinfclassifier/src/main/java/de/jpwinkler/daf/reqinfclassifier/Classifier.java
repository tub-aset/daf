package de.jpwinkler.daf.reqinfclassifier;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public abstract class Classifier<E> {

    private final ClassifierContext context;

    public Classifier(final ClassifierContext context) {
        this.context = context;
    }

    public final E classify(final DoorsObject doorsObject) {
        return run(new DoorsObjectContext(doorsObject, context));
    }

    public final E classify(final DoorsObjectContext doorsObjectContext) {
        return run(doorsObjectContext);
    }

    protected abstract E run(final DoorsObjectContext context);

}
