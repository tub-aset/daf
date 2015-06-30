package de.jpwinkler.daf.fap5gui.model;

public class DocumentSnapshot {

    private String documentName;
    private double currentFillLevel;
    private int numRequirements;
    private int numTodos;
    private double requirementsOpen;
    private double requirementsSpecified;
    private double requirementsFollowUp;
    private double requirementsFollowUpHashtags;
    private double requirementsAgreed;
    private int numErrorMissingObjectType;
    private int numErrorWrongHeading;
    private int numErrorInformationWithLink;
    private int numErrorRequirementWithoutLink;

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(final String documentName) {
        this.documentName = documentName;
    }

    public double getCurrentFillLevel() {
        return currentFillLevel;
    }

    public void setCurrentFillLevel(final double currentFillLevel) {
        this.currentFillLevel = currentFillLevel;
    }

    public int getNumRequirements() {
        return numRequirements;
    }

    public void setNumRequirements(final int numRequirements) {
        this.numRequirements = numRequirements;
    }

    public int getNumTodos() {
        return numTodos;
    }

    public void setNumTodos(final int numTodos) {
        this.numTodos = numTodos;
    }

    public double getRequirementsOpen() {
        return requirementsOpen;
    }

    public void setRequirementsOpen(final double requirementsOpen) {
        this.requirementsOpen = requirementsOpen;
    }

    public double getRequirementsSpecified() {
        return requirementsSpecified;
    }

    public void setRequirementsSpecified(final double requirementsSpecified) {
        this.requirementsSpecified = requirementsSpecified;
    }

    public double getRequirementsFollowUp() {
        return requirementsFollowUp;
    }

    public void setRequirementsFollowUp(final double requirementsFollowUp) {
        this.requirementsFollowUp = requirementsFollowUp;
    }

    public double getRequirementsFollowUpHashtags() {
        return requirementsFollowUpHashtags;
    }

    public void setRequirementsFollowUpHashtags(final double requirementsFollowUpHashtags) {
        this.requirementsFollowUpHashtags = requirementsFollowUpHashtags;
    }

    public double getRequirementsAgreed() {
        return requirementsAgreed;
    }

    public void setRequirementsAgreed(final double requirementsAgreed) {
        this.requirementsAgreed = requirementsAgreed;
    }

    public int getNumErrorMissingObjectType() {
        return numErrorMissingObjectType;
    }

    public void setNumErrorMissingObjectType(final int numErrorMissingObjectType) {
        this.numErrorMissingObjectType = numErrorMissingObjectType;
    }

    public int getNumErrorWrongHeading() {
        return numErrorWrongHeading;
    }

    public void setNumErrorWrongHeading(final int numErrorWrongHeading) {
        this.numErrorWrongHeading = numErrorWrongHeading;
    }

    public int getNumErrorInformationWithLink() {
        return numErrorInformationWithLink;
    }

    public void setNumErrorInformationWithLink(final int numErrorInformationWithLink) {
        this.numErrorInformationWithLink = numErrorInformationWithLink;
    }

    public int getNumErrorRequirementWithoutLink() {
        return numErrorRequirementWithoutLink;
    }

    public void setNumErrorRequirementWithoutLink(final int numErrorRequirementWithoutLink) {
        this.numErrorRequirementWithoutLink = numErrorRequirementWithoutLink;
    }

}
