package de.jpwinkler.daf.doorsdb.search;

import de.jpwinkler.daf.doorscsv.model.DoorsModule;

public class AttributeSearchExpression extends DBSearchExpression {

    private final String attributeName;
    private final String attributeValue;

    public AttributeSearchExpression(final String attributeName, final String attributeValue) {
        super();
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return module.getLatestVersion() != null && module.getLatestVersion().getAttributes().containsKey(attributeName) && module.getLatestVersion().getAttributes().get(attributeName).contains(attributeValue);
    }

}
