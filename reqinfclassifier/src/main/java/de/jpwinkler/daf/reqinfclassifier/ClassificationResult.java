package de.jpwinkler.daf.reqinfclassifier;

public class ClassificationResult {

    private String objectType;
    private String classifier;

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(final String classifier) {
        this.classifier = classifier;
    }

    public ClassificationResult() {
        super();
    }

    public ClassificationResult(final String objectType, final String classifier) {
        super();
        this.objectType = objectType;
        this.classifier = classifier;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

}
