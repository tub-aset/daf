package de.jpwinkler.daf.dataprocessing.featuregeneration;

import java.util.HashSet;
import java.util.Set;

public abstract class FeatureGenerator<E, F> {

    private final Set<F> features = new HashSet<>();

    protected final void emitFeature(final F feature) {
        this.features.add(feature);
    }

    public final Set<F> getFeatures(final E element) {
        features.clear();
        runGenerator(element);
        return new HashSet<>(features);
    }

    protected abstract void runGenerator(E element);

}
