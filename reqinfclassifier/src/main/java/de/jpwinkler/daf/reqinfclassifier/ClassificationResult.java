package de.jpwinkler.daf.reqinfclassifier;

public class ClassificationResult {

    private String objectType;
    private ClassifiedBy classifiedBy;
    private ClassificationReliability reliability;

    public ClassificationResult() {
        super();
    }

    public ClassificationResult(final String objectType, final ClassifiedBy classifiedBy, final ClassificationReliability reliability) {
        super();
        this.objectType = objectType;
        this.classifiedBy = classifiedBy;
        this.reliability = reliability;
    }

    public ClassifiedBy getClassifiedBy() {
        return classifiedBy;
    }

    public void setClassifiedBy(final ClassifiedBy classifiedBy) {
        this.classifiedBy = classifiedBy;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public ClassificationReliability getReliability() {
        return reliability;
    }

    public void setReliability(final ClassificationReliability reliability) {
        this.reliability = reliability;
    }

}
