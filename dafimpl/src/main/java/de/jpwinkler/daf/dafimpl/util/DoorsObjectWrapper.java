package de.jpwinkler.daf.dafimpl.util;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.reqinfclassifier.Example;

public class DoorsObjectWrapper implements Example {

    private final DoorsObject object;

    public DoorsObjectWrapper(final DoorsObject object) {
        super();
        this.object = object;
    }

    @Override
    public String getText() {
        return object.getText();
    }

    @Override
    public boolean isHeading() {
        return object.isHeading();
    }

    @Override
    public String getAttributeValue(final String attributeName) {
        return object.getAttributes().get(attributeName);
    }

    public DoorsObject getObject() {
        return object;
    }

    @Override
    public List<String> getAttributeNames() {
        return new ArrayList<>(object.getAttributes().keySet());
    }

    @Override
    public String getKey() {
        return object.getObjectIdentifier();
    }

}
