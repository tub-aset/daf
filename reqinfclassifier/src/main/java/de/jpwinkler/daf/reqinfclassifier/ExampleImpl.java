package de.jpwinkler.daf.reqinfclassifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleImpl implements Example {

    private String text;

    private boolean isHeading;

    private String key;

    private final Map<String, String> attributes = new HashMap<>();

    public ExampleImpl() {
        super();
    }

    public ExampleImpl(final String text) {
        super();
        this.text = text;
    }

    public ExampleImpl(final Example other) {
        this.text = other.getText();

        this.isHeading = other.isHeading();

        this.key = other.getKey();

        for (final String attributeName : other.getAttributeNames()) {
            setAttributeValue(attributeName, other.getAttributeValue(attributeName));
        }
    }

    public void setKey(final String key) {
        this.key = key;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public boolean isHeading() {
        return isHeading;
    }

    public void setHeading(final boolean isHeading) {
        this.isHeading = isHeading;
    }

    @Override
    public String getAttributeValue(final String attributeName) {
        return attributes.get(attributeName);
    }

    public void setAttributeValue(final String attributeName, final String attributeValue) {
        if (attributeValue != null) {
            attributes.put(attributeName, attributeValue);
        } else {
            attributes.remove(attributeName);
        }
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public List<String> getAttributeNames() {
        return new ArrayList<>(attributes.keySet());
    }

}
