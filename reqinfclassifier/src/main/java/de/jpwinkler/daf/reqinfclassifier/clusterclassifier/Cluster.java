package de.jpwinkler.daf.reqinfclassifier.clusterclassifier;

import java.util.Map;
import java.util.Set;

import de.jpwinkler.daf.reqinfclassifier.utils.Counter;

public class Cluster {

    private String label;
    private Set<String> examples;
    private double sourceDistribution;
    private double labelDistribution;
    private Map<String, Counter> typeMap;

    public Cluster() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Cluster(final String label, final Set<String> examples, final double sourceDistribution, final double labelDistribution) {
        super();
        this.label = label;
        this.examples = examples;
        this.sourceDistribution = sourceDistribution;
        this.labelDistribution = labelDistribution;
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

    public double getSourceDistribution() {
        return sourceDistribution;
    }

    public void setSourceDistribution(final double sourceDistribution) {
        this.sourceDistribution = sourceDistribution;
    }

    public double getLabelDistribution() {
        return labelDistribution;
    }

    public void setLabelDistribution(final double labelDistribution) {
        this.labelDistribution = labelDistribution;
    }

    public Map<String, Counter> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(final Map<String, Counter> typeMap) {
        this.typeMap = typeMap;
    }

}