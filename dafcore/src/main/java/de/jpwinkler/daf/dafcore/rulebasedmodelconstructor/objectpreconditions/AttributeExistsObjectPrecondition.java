package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class AttributeExistsObjectPrecondition implements ObjectPrecondition {

    private final String attribute;

    public AttributeExistsObjectPrecondition(final String attribute) {
        super();
        this.attribute = attribute;
    }

    @Override
    public boolean checkPrecondition(final DoorsObject object, final RuleContext context) {

        return object.getAttributes().containsKey(attribute);

    }

}
