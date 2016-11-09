package de.jpwinkler.daf.datasetutil;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private String body;
    private String parentBody;
    private String previousBody;
    private String nextBody;
    private final List<String> childBodies = new ArrayList<>();

    private String id;
    private String parentId;
    private String previousId;
    private String nextId;

    private String originalObjectType;

    private String convNetClassification;
    private Double convNetReq;
    private Double convNetInf;

    private String similiarityAnalysisObjectType;
    private String similiarityAnalysisStructuralType;
    private double greatestSimiliarity;

    private String clusterAnalysisObjectType;

    private String structuralType;

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getParentBody() {
        return parentBody;
    }

    public void setParentBody(final String parentBody) {
        this.parentBody = parentBody;
    }

    public String getPreviousBody() {
        return previousBody;
    }

    public void setPreviousBody(final String previousBody) {
        this.previousBody = previousBody;
    }

    public String getNextBody() {
        return nextBody;
    }

    public void setNextBody(final String nextBody) {
        this.nextBody = nextBody;
    }

    public String getOriginalObjectType() {
        return originalObjectType;
    }

    public void setOriginalObjectType(final String originalObjectType) {
        this.originalObjectType = originalObjectType;
    }

    public String getConvNetClassification() {
        return convNetClassification;
    }

    public void setConvNetClassification(final String convNetClassification) {
        this.convNetClassification = convNetClassification;
    }

    public Double getConvNetReq() {
        return convNetReq;
    }

    public void setConvNetReq(final Double convNetReq) {
        this.convNetReq = convNetReq;
    }

    public Double getConvNetInf() {
        return convNetInf;
    }

    public void setConvNetInf(final Double convNetInf) {
        this.convNetInf = convNetInf;
    }

    public String getSimiliarityAnalysisObjectType() {
        return similiarityAnalysisObjectType;
    }

    public void setSimiliarityAnalysisObjectType(final String similiarityAnalysisObjectType) {
        this.similiarityAnalysisObjectType = similiarityAnalysisObjectType;
    }

    public double getGreatestSimiliarity() {
        return greatestSimiliarity;
    }

    public void setGreatestSimiliarity(final double greatestSimiliarity) {
        this.greatestSimiliarity = greatestSimiliarity;
    }

    public String getClusterAnalysisObjectType() {
        return clusterAnalysisObjectType;
    }

    public void setClusterAnalysisObjectType(final String clusterAnalysisObjectType) {
        this.clusterAnalysisObjectType = clusterAnalysisObjectType;
    }

    public String getStructuralType() {
        return structuralType;
    }

    public void setStructuralType(final String structuralType) {
        this.structuralType = structuralType;
    }

    public List<String> getChildBodies() {
        return childBodies;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(final String parentId) {
        this.parentId = parentId;
    }

    public String getPreviousId() {
        return previousId;
    }

    public void setPreviousId(final String previousId) {
        this.previousId = previousId;
    }

    public String getNextId() {
        return nextId;
    }

    public void setNextId(final String nextId) {
        this.nextId = nextId;
    }

    public String getSimiliarityAnalysisStructuralType() {
        return similiarityAnalysisStructuralType;
    }

    public void setSimiliarityAnalysisStructuralType(final String similiarityAnalysisStructuralType) {
        this.similiarityAnalysisStructuralType = similiarityAnalysisStructuralType;
    }

}
