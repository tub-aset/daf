package de.jpwinkler.daf.doorsdb.search;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public class AndSearchExpression extends DBSearchExpression {

    private final List<DBSearchExpression> expressions;

    public AndSearchExpression(final DBSearchExpression e1, final DBSearchExpression e2) {
        super();
        expressions = new ArrayList<>();
        expressions.add(e1);
        expressions.add(e2);
    }

    public AndSearchExpression(final List<DBSearchExpression> expressions) {
        super();
        this.expressions = expressions;
    }

    @Override
    public boolean matches(final DBModule module) {
        return expressions.stream().allMatch(e -> e.matches(module));
    }

}
