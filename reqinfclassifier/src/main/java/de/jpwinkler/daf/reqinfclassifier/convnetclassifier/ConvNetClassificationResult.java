package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;

public class ConvNetClassificationResult extends ClassificationResult {

    private final Map<String, Double> probabilities = new HashMap<>();

    private List<Double> influenceVector = new ArrayList<>();

    private double outputDifference;

    private double fractionOfKnownWords;

    public ConvNetClassificationResult() {
        setClassifier("convnet");
    }

    public Map<String, Double> getProbabilities() {
        return probabilities;
    }

    public List<Double> getInfluenceVector() {
        return influenceVector;
    }

    public void setInfluenceVector(final List<Double> influenceVector) {
        this.influenceVector = influenceVector;
    }

    public double getOutputDifference() {
        return outputDifference;
    }

    public void setOutputDifference(final double outputDifference) {
        this.outputDifference = outputDifference;
    }

    public double getFractionOfKnownWords() {
        return fractionOfKnownWords;
    }

    public void setFractionOfKnownWords(final double fractionOfKnownWords) {
        this.fractionOfKnownWords = fractionOfKnownWords;
    }

}
