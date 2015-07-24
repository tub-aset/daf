package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class CascadingObjectPrecondition implements ObjectPrecondition {

    private final ObjectPrecondition objectPrecondition;
    private final boolean matchSelf;

    public CascadingObjectPrecondition(final ObjectPrecondition objectPrecondition, final boolean matchSelf) {
        super();
        this.objectPrecondition = objectPrecondition;
        this.matchSelf = matchSelf;
    }

    @Override
    public boolean checkPrecondition(final DoorsObject object, final RuleContext context) {
        if (matchSelf && objectPrecondition.checkPrecondition(object, context)) {
            return true;
        } else if (object.getParent() instanceof DoorsObject && objectPrecondition.checkPrecondition((DoorsObject) object.getParent(), context)) {
            return true;
        } else if (object.getParent() instanceof DoorsObject) {
            return checkPrecondition((DoorsObject) object.getParent(), context);
        } else {
            return false;
        }
    }

}
