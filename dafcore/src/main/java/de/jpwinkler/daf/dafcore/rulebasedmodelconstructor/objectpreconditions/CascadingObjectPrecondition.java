package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class CascadingObjectPrecondition implements ObjectPrecondition {

    private final ObjectPrecondition objectPrecondition;
    private final boolean matchSelf;

    public CascadingObjectPrecondition(ObjectPrecondition objectPrecondition, boolean matchSelf) {
        super();
        this.objectPrecondition = objectPrecondition;
        this.matchSelf = matchSelf;
    }

    @Override
    public boolean checkPrecondition(DoorsObject object, RuleContext context) {
        if (matchSelf && objectPrecondition.checkPrecondition(object, context)) {
            return true;
        } else if (object.getParent() != null && objectPrecondition.checkPrecondition(object.getParent(), context)) {
            return true;
        } else if (object.getParent() != null) {
            return checkPrecondition(object.getParent(), context);
        } else {
            return false;
        }
    }

}
