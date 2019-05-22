package de.jpwinkler.daf.filter.modules;

import de.jpwinkler.daf.model.DoorsModule;

public class AttributeSearchExpression extends SearchExpression {

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
