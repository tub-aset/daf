package de.jpwinkler.daf.search;

import de.jpwinkler.daf.model.DoorsModule;

public class NotSearchExpression extends SearchExpression {

    private final SearchExpression nestedExpression;

    public NotSearchExpression(final SearchExpression nestedExpression) {
        super();
        this.nestedExpression = nestedExpression;
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return !nestedExpression.matches(module);
    }

}
