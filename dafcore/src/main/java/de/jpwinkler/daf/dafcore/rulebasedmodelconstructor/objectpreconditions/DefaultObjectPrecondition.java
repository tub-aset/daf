package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class DefaultObjectPrecondition implements ObjectPrecondition {

    @Override
    public boolean checkPrecondition(DoorsObject object, RuleContext context) {
        return true;
    }

}
