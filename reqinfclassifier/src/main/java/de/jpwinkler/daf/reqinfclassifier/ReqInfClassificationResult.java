package de.jpwinkler.daf.reqinfclassifier;

public class ReqInfClassificationResult {

    private String objectType;

    public ReqInfClassificationResult() {
        objectType = null;
    }

    public ReqInfClassificationResult(final String objectType) {
        this.objectType = objectType;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

}
