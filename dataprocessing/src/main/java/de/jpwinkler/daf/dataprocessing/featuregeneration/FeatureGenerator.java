package de.jpwinkler.daf.dataprocessing.featuregeneration;

import java.util.Map;

public abstract class FeatureGenerator<E, F> {

    private Map<F, Integer> features;

    public final void emitFeature(final F feature) {
        if (features.containsKey(feature)) {
            features.put(feature, features.get(feature) + 1);
        } else {
            features.put(feature, 1);
        }
    }

    public final void getFeatures(final E element, final Map<F, Integer> features) {
        this.features = features;
        runGenerator(element);
    }

    protected abstract void runGenerator(E element);

}
