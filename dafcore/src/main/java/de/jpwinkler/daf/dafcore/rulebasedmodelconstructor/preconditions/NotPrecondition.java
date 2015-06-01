package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class NotPrecondition implements Precondition {

    private final Precondition precondition;

    public NotPrecondition(Precondition precondition) {
        super();
        this.precondition = precondition;
    }

    public Precondition getPrecondition() {
        return precondition;
    }

    @Override
    public boolean checkPrecondition(RuleContext context) {
        return !precondition.checkPrecondition(context);
    }

}
