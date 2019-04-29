package de.jpwinkler.daf.csveditor.massedit;

import de.jpwinkler.daf.doorscsv.model.DoorsObject;

public class SetAttributeOperation implements MassEditOperation {

    private String attributeName;

    private String attributeValue;

    @Override
    public void apply(final DoorsObject object) {
        object.getAttributes().put(attributeName, attributeValue);
    }

    public SetAttributeOperation(final String attributeName, final String attributeValue) {
        super();
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(final String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(final String attributeValue) {
        this.attributeValue = attributeValue;
    }

}
