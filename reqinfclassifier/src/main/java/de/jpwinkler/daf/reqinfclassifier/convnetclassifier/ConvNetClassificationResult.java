package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import java.util.HashMap;
import java.util.Map;

public class ConvNetClassificationResult {

    private String objectType;

    private final Map<String, Double> probabilities;

    public ConvNetClassificationResult() {
        this(null, new HashMap<>());
    }

    public ConvNetClassificationResult(final String objectType) {
        this(objectType, new HashMap<>());
    }

    public ConvNetClassificationResult(final String objectType, final Map<String, Double> probabilities) {
        super();
        this.objectType = objectType;
        this.probabilities = probabilities;
    }

    public String getObjectType() {
        return objectType;
    }

    public Map<String, Double> getProbabilities() {
        return probabilities;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

}
