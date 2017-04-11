package de.jpwinkler.daf.reqinfclassifier;


public enum ClassificationReliability {

    /**
     * The classification provided by the classifier is correct.
     */
    DEFINITELY_CORRECT(3),

    /**
     * It is relatively safe to assume that the classification provided by the
     * classifier is correct.
     */
    MOST_LIKELY_CORRECT(2),

    /**
     * The classification provided by the classifier might be correct, but it
     * should not be relied upon too heavily.
     */
    MAYBE_CORRECT(1),

    /**
     * The classification provided by the classifier may or may not be correct,
     * since the provided input example does not provide any or only minimal
     * discriminative features.
     */
    AMBIGUOUS(0),

    /**
     * The reliability of the classification provided by the classifier is not
     * known.
     */
    UNKNOWN(-1);

    private int reliability;

    private ClassificationReliability(final int reliability) {
        this.reliability = reliability;
    }

    public boolean atLeast(final ClassificationReliability other) {
        return this.reliability >= other.reliability;
    }

}
