package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class NotObjectPrecondition implements ObjectPrecondition {

    private final ObjectPrecondition objectPrecondition;

    public NotObjectPrecondition(final ObjectPrecondition objectPrecondition) {
        super();
        this.objectPrecondition = objectPrecondition;
    }

    public ObjectPrecondition getObjectPrecondition() {
        return objectPrecondition;
    }

    @Override
    public boolean checkPrecondition(final DoorsObject object, final RuleContext context) {
        return !objectPrecondition.checkPrecondition(object, context);
    }

}
