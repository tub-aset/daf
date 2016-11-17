package de.jpwinkler.daf.reqinfclassifier;

public class ClassificationResult {

    private String objectType;
    private ClassifiedBy classifiedBy;
    private ClassificationReliability reliability;
    

    

	public ClassificationResult() {
		super();
	}

	public ClassificationResult(String objectType, ClassifiedBy classifiedBy,
			ClassificationReliability reliability) {
		super();
		this.objectType = objectType;
		this.classifiedBy = classifiedBy;
		this.reliability = reliability;
	}

	public ClassifiedBy getClassifiedBy() {
		return classifiedBy;
	}

	public void setClassifiedBy(ClassifiedBy classifiedBy) {
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

	public void setReliability(ClassificationReliability reliability) {
		this.reliability = reliability;
	}
    
    
    
}
