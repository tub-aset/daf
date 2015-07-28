package de.jpwinkler.daf.documenttagging.maxent;

public class MaxentDataElement {

    private String outcome;
    private String[] features;

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(final String outcome) {
        this.outcome = outcome;
    }

    public String[] getFeatures() {
        return features;
    }

    public MaxentDataElement(final String outcome, final String[] features) {
        super();
        this.outcome = outcome;
        this.features = features;
    }

    public void setFeatures(final String[] features) {
        this.features = features;
    }

}
