package de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing;

public class SmoothingTechniqueFactory<T> {

    private final SmoothingTechnique smoothingTechnique;

    private final double d;
    private final double k;

    public SmoothingTechniqueFactory(final SmoothingTechnique smoothingTechnique, final double d, final double k) {
        super();
        this.smoothingTechnique = smoothingTechnique;
        this.d = d;
        this.k = k;
    }

    public AbstractSmoothingTechnique<T> getInstance() {
        switch (smoothingTechnique) {
        case ABSOLUTE_DISCOUNTING:
            return new AbsoluteDiscountingSmoothing<>(d);
        case NO_SMOOTHING:
            return new NoSmoothing<>();
        case ADD_K_SMOOTHING:
            return new AddKSmoothing<>(k);
        case KATZ_BACKOFF:
            return new KatzBackoff<>(k, d);
        case KNESER_NEY_SMOOTHING:
            return new KneserNeySmoothing<>(d);
        case VERY_STUPID_BACKOFF:
            return new VeryStupidBackoff<>(k, d);
        default:
            throw new RuntimeException("cannot create smoothing technique " + smoothingTechnique);
        }
    }

}
