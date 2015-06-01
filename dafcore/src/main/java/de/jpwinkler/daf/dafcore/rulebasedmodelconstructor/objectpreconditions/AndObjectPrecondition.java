package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions;

import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class AndObjectPrecondition extends CompositeObjectPrecondition {

    public AndObjectPrecondition(final ObjectPrecondition... objectPreconditions) {
        super(objectPreconditions);
    }

    public AndObjectPrecondition(final List<ObjectPrecondition> objectPreconditions) {
        super(objectPreconditions);
    }

    @Override
    public boolean checkPrecondition(final DoorsObject object, final RuleContext context) {
        for (final ObjectPrecondition objectPrecondition : getObjectPreconditions()) {
            if (!objectPrecondition.checkPrecondition(object, context)) {
                return false;
            }
        }
        return true;
    }

}
