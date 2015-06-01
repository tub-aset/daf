package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.AndObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.AndPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.Precondition;

public class CompositeRule implements Rule {

    private final List<Rule> rules;

    public CompositeRule(final Rule... rules) {
        this.rules = Arrays.asList(rules);
    }

    @Override
    public Precondition getPrecondition() {
        return new AndPrecondition(rules.stream().map(Rule::getPrecondition).collect(Collectors.toList()));
    }

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return new AndObjectPrecondition(rules.stream().map(Rule::getObjectPrecondition).collect(Collectors.toList()));
    }

    @Override
    public void prepare(final RuleContext context) {
        for (final Rule rule : rules) {
            rule.prepare(context);
        }
    }

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        for (final Rule rule : rules) {
            rule.preTraverse(object, context);
        }
    }

    @Override
    public void postTraverse(final DoorsObject object, final RuleContext context) {
        for (final Rule rule : rules) {
            rule.postTraverse(object, context);
        }
    }

    @Override
    public void finish(final RuleContext context) {
        for (final Rule rule : rules) {
            rule.finish(context);
        }
    }

}
