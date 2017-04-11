package de.jpwinkler.daf.reqinfclassifier.multisentenceclassifier;

import java.util.List;

import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;

public class MultiSentenceClassificationResult extends ClassificationResult {

    private List<String> sentences;

    private List<ClassificationResult> results;

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(final List<String> sentences) {
        this.sentences = sentences;
    }

    public List<ClassificationResult> getResults() {
        return results;
    }

    public void setResults(final List<ClassificationResult> results) {
        this.results = results;
    }

}
