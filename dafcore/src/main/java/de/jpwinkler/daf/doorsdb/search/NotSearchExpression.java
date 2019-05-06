package de.jpwinkler.daf.doorsdb.search;

import de.jpwinkler.daf.model.DoorsModule;

public class NotSearchExpression extends DBSearchExpression {

    private final DBSearchExpression nestedExpression;

    public NotSearchExpression(final DBSearchExpression nestedExpression) {
        super();
        this.nestedExpression = nestedExpression;
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return !nestedExpression.matches(module);
    }

}
