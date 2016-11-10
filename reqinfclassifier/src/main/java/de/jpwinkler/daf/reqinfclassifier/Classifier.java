package de.jpwinkler.daf.reqinfclassifier;

public abstract class Classifier<O> {

    private final ClassifierContext context;

    public Classifier(final ClassifierContext context) {
        this.context = context;
    }

    public final O classify(final Example example) {
        return run(new ExampleContext(example, context));
    }

    public final O classify(final ExampleContext exampleContext) {
        return run(exampleContext);
    }

    protected abstract O run(final ExampleContext exampleContext);

}
