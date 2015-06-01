package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class AttributeObjectPrecondition implements ObjectPrecondition {

    private final String attributeName;
    private final String attributeValue;

    public AttributeObjectPrecondition(final String attributeName, final String attributeValue) {
        super();
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    @Override
    public boolean checkPrecondition(final DoorsObject object, final RuleContext context) {
        return object.getAttributes().contains(attributeName) && object.getAttributes().get(attributeName).equals(attributeValue);
    }

}
