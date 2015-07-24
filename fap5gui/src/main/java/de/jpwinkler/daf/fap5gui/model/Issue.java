package de.jpwinkler.daf.fap5gui.model;

public class Issue {

    private String objectId;
    private int objectAbsoluteNumber;
    private String absoluteModuleName;

    public int getObjectAbsoluteNumber() {
        return objectAbsoluteNumber;
    }

    public void setObjectAbsoluteNumber(final int objectAbsoluteNumber) {
        this.objectAbsoluteNumber = objectAbsoluteNumber;
    }

    private String objectNumber;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(final String objectId) {
        this.objectId = objectId;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(final String objectNumber) {
        this.objectNumber = objectNumber;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    public void setIssueMessage(final String issueMessage) {
        this.issueMessage = issueMessage;
    }

    public String getAbsoluteModuleName() {
        return absoluteModuleName;
    }

    public void setAbsoluteModuleName(final String absoluteModuleName) {
        this.absoluteModuleName = absoluteModuleName;
    }

    private String issueMessage;

}
