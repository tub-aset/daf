package de.jpwinkler.daf.doorsdb.search;

import de.jpwinkler.daf.doorsdb.model.DBModule;

public class OrSearchExpression extends DBSearchExpression {

    private final DBSearchExpression e1;
    private final DBSearchExpression e2;

    public OrSearchExpression(final DBSearchExpression e1, final DBSearchExpression e2) {
        super();
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public boolean matches(final DBModule module) {
        return e1.matches(module) || e2.matches(module);
    }

}
