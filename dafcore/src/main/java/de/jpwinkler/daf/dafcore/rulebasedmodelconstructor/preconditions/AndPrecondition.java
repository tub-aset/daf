package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions;

import java.util.List;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class AndPrecondition extends CompositePrecondition {

    public AndPrecondition(final Precondition... preconditions) {
        super(preconditions);
    }

    public AndPrecondition(final List<Precondition> preconditions) {
        super(preconditions);
    }

    @Override
    public boolean checkPrecondition(final RuleContext context) {
        for (final Precondition precondition : getPreconditions()) {
            if (!precondition.checkPrecondition(context)) {
                return false;
            }
        }
        return true;
    }

}
