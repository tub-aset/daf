package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import java.util.HashMap;
import java.util.Map;

import de.jpwinkler.daf.reqinfclassifier.ClassificationReliability;
import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.ClassifiedBy;

public class ConvNetClassificationResult extends ClassificationResult {

	private final Map<String, Double> probabilities = new HashMap<>();

	private SentenceMarkup sentenceMarkup;

	private double outputDifference;

	private double fractionOfKnownWords;

	public ConvNetClassificationResult() {
		super();
	}

	public ConvNetClassificationResult(final String objectType, final ClassifiedBy classifiedBy, final ClassificationReliability reliability) {
		super(objectType, classifiedBy, reliability);
	}

	public Map<String, Double> getProbabilities() {
		return probabilities;
	}

	public double getHighestProbability() {
		return probabilities.values().stream().mapToDouble(d -> d).max().getAsDouble();
	}

	public SentenceMarkup getSentenceMarkup() {
		return sentenceMarkup;
	}

	public void setSentenceMarkup(final SentenceMarkup sentenceMarkup) {
		this.sentenceMarkup = sentenceMarkup;
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
