package de.jpwinkler.daf.documenttagging.maxent.features;

public class UniFeature extends Feature<Object> {

    public UniFeature(final String name) {
        super(name, true);
    }

    @Override
    public String toString() {
        return getName();
    }
}
