package de.jpwinkler.daf.reqinfclassifier.clusterclassifier;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.reqinfclassifier.ClassificationReliability;
import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.ClassifiedBy;

public class ClusterClassificationResult extends ClassificationResult {

    private final List<String> mostSimiliarExamples = new ArrayList<>();

    private final List<Double> exampleSimiliarities = new ArrayList<>();

    public ClusterClassificationResult() {
        super();
    }

    public ClusterClassificationResult(final String objectType, final ClassifiedBy classifiedBy, final ClassificationReliability reliability) {
        super(objectType, classifiedBy, reliability);
    }

    public List<String> getMostSimiliarExamples() {
        return mostSimiliarExamples;
    }

    public List<Double> getExampleSimiliarities() {
        return exampleSimiliarities;
    }

}
