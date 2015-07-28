package de.jpwinkler.daf.documenttagging.maxent.features;

public abstract class Feature<X> {

    public Feature() {
    }

    public Feature(final String name, final X value) {
        super();
        this.name = name;
        this.value = value;
    }

    private String name;

    private X value;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public X getValue() {
        return value;
    }

    public void setValue(final X value) {
        this.value = value;
    }

}
