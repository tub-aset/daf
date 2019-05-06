package de.jpwinkler.daf.search;

import de.jpwinkler.daf.model.DoorsModule;
import java.util.ArrayList;
import java.util.List;

public class AndSearchExpression extends SearchExpression {

    private final List<SearchExpression> expressions;

    public AndSearchExpression(final SearchExpression e1, final SearchExpression e2) {
        super();
        expressions = new ArrayList<>();
        expressions.add(e1);
        expressions.add(e2);
    }

    public AndSearchExpression(final List<SearchExpression> expressions) {
        super();
        this.expressions = expressions;
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return expressions.stream().allMatch(e -> e.matches(module));
    }

}
