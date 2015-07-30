package de.jpwinkler.daf.documenttagging.maxent.features;

public class StringFeature extends Feature<String> {

    public StringFeature() {
        super();
    }

    public StringFeature(final String name, final String value) {
        super(name, value);
    }

    @Override
    public String toString() {
        return getName() + "=" + getValue();
    }
}
