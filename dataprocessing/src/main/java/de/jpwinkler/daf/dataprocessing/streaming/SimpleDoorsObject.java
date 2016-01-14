package de.jpwinkler.daf.dataprocessing.streaming;

import java.util.HashMap;
import java.util.Map;

public class SimpleDoorsObject {

    private final Map<String, String> attributes = new HashMap<>();

    public String getAttribute(final String attributeName) {
        return attributes.get(attributeName);
    }

    public void setAttribute(final String attributeName, final String attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    public boolean hasAttribute(final String attributeName) {
        return attributes.containsKey(attributeName);
    }

    public String getObjectIdentifier() {
        return attributes.get("Object Identifier");
    }

    public void setObjectIdentifier(final String newObjectIdentifier) {
        attributes.put("Object Identifier", newObjectIdentifier);
    }

    public int getObjectLevel() {
        return Integer.parseInt(attributes.get("Object Level"));
    }

    public void setObjectLevel(final int newObjectLevel) {
        attributes.put("Object Level", String.valueOf(newObjectLevel));
    }

    public String getObjectNumber() {
        return attributes.get("Object Number");
    }

    public void setObjectNumber(final String newObjectNumber) {
        attributes.put("Object Number", newObjectNumber);
    }

    public int getAbsoluteNumber() {
        return Integer.parseInt(attributes.get("Absolute Number"));
    }

    public void setAbsoluteNumber(final int newAbsoluteNumber) {
        attributes.put("Absolute Number", String.valueOf(newAbsoluteNumber));
    }

    public String getObjectText() {
        return attributes.get("Object Text");
    }

    public void setObjectText(final String newObjectText) {
        attributes.put("Object Text", newObjectText);
    }

    public String getObjectShortText() {
        return attributes.get("Object Short Text");
    }

    public void setObjectShortText(final String newObjectShortText) {
        attributes.put("Object Short Text", newObjectShortText);
    }

    public String getObjectHeading() {
        return attributes.get("Object Heading");
    }

    public void setObjectHeading(final String newObjectHeading) {
        attributes.put("Object Heading", newObjectHeading);
    }

    public boolean isHeading() {
        return !getObjectHeading().isEmpty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
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
        final SimpleDoorsObject other = (SimpleDoorsObject) obj;
        if (attributes == null) {
            if (other.attributes != null) {
                return false;
            }
        } else if (!attributes.equals(other.attributes)) {
            return false;
        }
        return true;
    }

}
