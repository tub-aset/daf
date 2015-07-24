package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Rule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class DefaultPrecondition extends DoorsTreeNodeVisitor implements Precondition {

    private final Rule rule;

    private RuleContext context;
    private boolean foundObject;

    public DefaultPrecondition(final Rule rule) {
        super();
        this.rule = rule;
    }

    @Override
    public boolean checkPrecondition(final RuleContext context) {
        foundObject = false;
        this.context = context;
        this.context.getModule().accept(this);
        return foundObject;
    }

    @Override
    public boolean visitPreTraverse(final DoorsObject object) {
        if (rule.getObjectPrecondition().checkPrecondition(object, context)) {
            foundObject = true;
        }
        // Return false when a matching object was found to stop processing
        // child objects.
        return !foundObject;
    }

}
