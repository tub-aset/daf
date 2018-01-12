package de.jpwinkler.daf.csveditor.otclassification;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.reqinfclassifier.Example;

public class DoorsObjectExample implements Example {

    private final DoorsObject object;

    public DoorsObjectExample(final DoorsObject object) {
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
    public List<String> getAttributeNames() {
        return new ArrayList<>(object.getAttributes().keySet());
    }

    @Override
    public String getAttributeValue(final String attributeName) {
        return object.getAttributes().get(attributeName);
    }

    @Override
    public String getKey() {
        return object.getObjectIdentifier();
    }

    @Override
    public String toString() {
        return object.getObjectIdentifier();
    }

}
