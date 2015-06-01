package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;

public interface Precondition extends ObjectPrecondition {

    boolean checkPrecondition(RuleContext context);

    @Override
    default boolean checkPrecondition(final DoorsObject object, final RuleContext context) {
        return checkPrecondition(context);
    }

}
