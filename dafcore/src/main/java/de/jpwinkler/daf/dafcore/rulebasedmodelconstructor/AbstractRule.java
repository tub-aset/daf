package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.DefaultObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.DefaultPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.Precondition;

public abstract class AbstractRule implements Rule {

    private final DefaultObjectPrecondition objectPrecondition = new DefaultObjectPrecondition();
    private final DefaultPrecondition precondition = new DefaultPrecondition(this);

    @Override
    public Precondition getPrecondition() {
        return precondition;
    }

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return objectPrecondition;
    }

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        // do nothing by default
    }

    @Override
    public void postTraverse(final DoorsObject object, final RuleContext context) {
        // do nothing by default
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public void prepare(final RuleContext context) {
        // do nothing by default
    }

    @Override
    public void finish(final RuleContext context) {
        // do nothing by default
    }
}
