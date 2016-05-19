package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

public class NounPopularity {

    private String noun;
    private double distribution;
    private int occurences;

    public double getDistribution() {
        return distribution;
    }

    public void setDistribution(final double distribution) {
        this.distribution = distribution;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(final String noun) {
        this.noun = noun;
    }

    public int getOccurences() {
        return occurences;
    }

    public void setOccurences(final int occurences) {
        this.occurences = occurences;
    }

    public NounPopularity(final String noun, final double distribution, final int occurences) {
        super();
        this.noun = noun;
        this.distribution = distribution;
        this.occurences = occurences;
    }

    public NounPopularity() {
    }

}
