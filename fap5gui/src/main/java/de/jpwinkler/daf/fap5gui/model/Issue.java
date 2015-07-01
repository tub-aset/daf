package de.jpwinkler.daf.fap5gui.model;

public class Issue {

    private String objectId;
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
    private String issueMessage;

}
