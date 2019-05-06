package de.jpwinkler.daf.search;

import de.jpwinkler.daf.model.DoorsModule;

public class OrSearchExpression extends SearchExpression {

    private final SearchExpression e1;
    private final SearchExpression e2;

    public OrSearchExpression(final SearchExpression e1, final SearchExpression e2) {
        super();
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return e1.matches(module) || e2.matches(module);
    }

}
