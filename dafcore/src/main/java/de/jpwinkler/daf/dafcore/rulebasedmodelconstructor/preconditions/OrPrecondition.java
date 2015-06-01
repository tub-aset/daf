package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions;

import java.util.List;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class OrPrecondition extends CompositePrecondition {

    public OrPrecondition(final List<Precondition> preconditions) {
        super(preconditions);
    }

    public OrPrecondition(final Precondition... preconditions) {
        super(preconditions);
    }

    @Override
    public boolean checkPrecondition(final RuleContext context) {
        for (final Precondition precondition : getPreconditions()) {
            if (precondition.checkPrecondition(context)) {
                return true;
            }
        }
        return false;
    }

}
