package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class MarkerTypeObjectPrecondition implements ObjectPrecondition {

    private final String markerType;

    public MarkerTypeObjectPrecondition(final String markerType) {
        super();
        this.markerType = markerType;
    }

    @Override
    public boolean checkPrecondition(final DoorsObject object, final RuleContext context) {
        return context.getMarker(object, markerType) != null;
    }

}
