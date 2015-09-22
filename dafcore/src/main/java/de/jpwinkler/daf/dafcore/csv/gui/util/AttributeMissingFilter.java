package de.jpwinkler.daf.dafcore.csv.gui.util;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class AttributeMissingFilter implements DoorsObjectFilter {

    private final String attribute;

    public AttributeMissingFilter(final String attribute) {
        super();
        this.attribute = attribute;
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        final String s = object.getAttributes().get(attribute);
        return s == null || s.isEmpty();
    }

}
