package de.jpwinkler.daf.dataprocessing.featuregeneration;

public abstract class FeatureFilter<F> {

    private FeatureVectorGenerator<?, F> featureVectorGenerator;

    public abstract boolean test(final F feature);

    public void setFeatureVectorGenerator(final FeatureVectorGenerator<?, F> featureVectorGenerator) {
        this.featureVectorGenerator = featureVectorGenerator;

    }

    protected FeatureVectorGenerator<?, F> getFeatureVectorGenerator() {
        return featureVectorGenerator;
    }

}
