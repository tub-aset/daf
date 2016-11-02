package de.jpwinkler.daf.datasetutil;

import java.util.Date;

public class DataSetRecord {

    private Date dateAdded;

    private String body;

    private String parentBody;

    private String previousBody;

    private String nextBody;

    private String originalObjectType;

    private String correctedObjectType;

    private String structualType;

    private boolean unknownStructuralType;

    private boolean ambiguousObjectType;

    private boolean combinedStructuralType;

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(final Date dateAdded) {
        this.dateAdded = dateAdded;
    }

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

    public String getCorrectedObjectType() {
        return correctedObjectType;
    }

    public void setCorrectedObjectType(final String correctedObjectType) {
        this.correctedObjectType = correctedObjectType;
    }

    public String getStructualType() {
        return structualType;
    }

    public void setStructualType(final String structualType) {
        this.structualType = structualType;
    }

    public boolean isUnknownStructuralType() {
        return unknownStructuralType;
    }

    public void setUnknownStructuralType(final boolean unknownStructuralType) {
        this.unknownStructuralType = unknownStructuralType;
    }

    public boolean isAmbiguousObjectType() {
        return ambiguousObjectType;
    }

    public void setAmbiguousObjectType(final boolean ambiguousObjectType) {
        this.ambiguousObjectType = ambiguousObjectType;
    }

    public boolean isCombinedStructuralType() {
        return combinedStructuralType;
    }

    public void setCombinedStructuralType(final boolean combinedStructuralType) {
        this.combinedStructuralType = combinedStructuralType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + ((nextBody == null) ? 0 : nextBody.hashCode());
        result = prime * result + ((parentBody == null) ? 0 : parentBody.hashCode());
        result = prime * result + ((previousBody == null) ? 0 : previousBody.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataSetRecord other = (DataSetRecord) obj;
        if (body == null) {
            if (other.body != null) {
                return false;
            }
        } else if (!body.equals(other.body)) {
            return false;
        }
        if (nextBody == null) {
            if (other.nextBody != null) {
                return false;
            }
        } else if (!nextBody.equals(other.nextBody)) {
            return false;
        }
        if (parentBody == null) {
            if (other.parentBody != null) {
                return false;
            }
        } else if (!parentBody.equals(other.parentBody)) {
            return false;
        }
        if (previousBody == null) {
            if (other.previousBody != null) {
                return false;
            }
        } else if (!previousBody.equals(other.previousBody)) {
            return false;
        }
        return true;
    }

}
