package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class MarkerTypePrecondition implements Precondition {

    private final String markerType;

    public MarkerTypePrecondition(final String markerType) {
        super();
        this.markerType = markerType;
    }

    @Override
    public boolean checkPrecondition(final RuleContext context) {
        return !context.getMarkedObjects(markerType).isEmpty();
    }

}
