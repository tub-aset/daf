package de.jpwinkler.daf.dataprocessing.featuregeneration;

public class CutoffFilter<F> extends FeatureFilter<F> {

    private final Integer cutoff;

    public CutoffFilter(final Integer cutoff) {
        super();
        this.cutoff = cutoff;
    }

    @Override
    public boolean test(final F feature) {
        return getFeatureVectorGenerator().getFeatureCounts().get(feature) > cutoff;
    }

}
