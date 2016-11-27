package de.jpwinkler.daf.reqinfclassifier.clusterclassifier;

import java.util.Set;

public class Cluster {

    private String label;
    private Set<String> examples;

    public Cluster() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Cluster(final String label, final Set<String> examples) {
        super();
        this.label = label;
        this.examples = examples;
    }

    public Set<String> getExamples() {
        return examples;
    }

    public String getLabel() {
        return label;
    }

    public void setExamples(final Set<String> examples) {
        this.examples = examples;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

}