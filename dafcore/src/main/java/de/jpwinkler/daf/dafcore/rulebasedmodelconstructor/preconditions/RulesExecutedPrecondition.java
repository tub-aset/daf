package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Rule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class RulesExecutedPrecondition implements Precondition {

    private final List<Class<? extends Rule>> ruleClasses;

    @SafeVarargs
    public RulesExecutedPrecondition(final Class<? extends Rule>... ruleClasses) {
        super();
        this.ruleClasses = Arrays.asList(ruleClasses);
    }

    public List<Class<? extends Rule>> getRuleClasses() {
        return ruleClasses;
    }

    @Override
    public boolean checkPrecondition(final RuleContext context) {
        // TODO: not optimal, performance-wise
        for (final Class<? extends Rule> ruleClass : ruleClasses) {
            boolean instanceFound = false;
            for (final Rule completedRule : context.getCompletedRules()) {
                if (completedRule.getClass().equals(ruleClass)) {
                    instanceFound = true;
                }
            }
            if (!instanceFound) {
                return false;
            }
        }
        return true;
    }

}
