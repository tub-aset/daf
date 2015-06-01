package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.Precondition;

public interface Rule {

    Precondition getPrecondition();

    ObjectPrecondition getObjectPrecondition();

    void prepare(RuleContext context);

    void preTraverse(DoorsObject object, RuleContext context);

    void postTraverse(DoorsObject object, RuleContext context);

    void finish(RuleContext context);
}
