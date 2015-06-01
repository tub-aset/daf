package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util;

import java.util.List;

import de.jpwinkler.daf.dafcore.csv.DoorsModuleVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Rule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class RuleApplicationVisitor implements DoorsModuleVisitor {

    private final List<Rule> rules;
    private final RuleContext context;

    public RuleApplicationVisitor(final List<Rule> rules, final RuleContext context) {
        super();
        this.rules = rules;
        this.context = context;
    }

    @Override
    public boolean visitPreTraverse(final DoorsObject object) {
        for (final Rule rule : rules) {
            if (rule.getObjectPrecondition().checkPrecondition(object, context)) {
                rule.preTraverse(object, context);
            }
        }
        return true;
    }

    @Override
    public void visitPostTraverse(final DoorsObject object) {
        for (final Rule rule : rules) {
            if (rule.getObjectPrecondition().checkPrecondition(object, context)) {
                rule.postTraverse(object, context);
            }
        }
    }

}
