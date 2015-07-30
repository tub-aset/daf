package de.jpwinkler.daf.documenttagging.maxent.features;

public class BooleanFeature extends Feature<Boolean> {

    public BooleanFeature() {
        super();
    }

    public BooleanFeature(final String name, final Boolean value) {
        super(name, value);
    }

    @Override
    public String toString() {
        return getName() + "=" + (getValue() ? "true" : "false");
    }

}
