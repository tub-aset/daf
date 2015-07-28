package de.jpwinkler.daf.documenttagging.doors;

import de.jpwinkler.daf.documenttagging.DocumentElementTag;

public class DoorsObjectTag implements DocumentElementTag {

    private String partOfDocumentTag;

    public DoorsObjectTag() {
    }

    public DoorsObjectTag(final String partOfDocumentTag) {
        super();
        this.partOfDocumentTag = partOfDocumentTag;
    }

    public String getPartOfDocumentTag() {
        return partOfDocumentTag;
    }

    public void setPartOfDocumentTag(final String partOfDocumentTag) {
        this.partOfDocumentTag = partOfDocumentTag;
    }

}
