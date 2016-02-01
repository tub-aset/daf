package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;

public class AcceptanceRule extends AbstractRule {

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return (object, context) -> object.getAttributes().containsKey("Acceptance Status") && context.getMarker(object, CodeBeamerConstants.MARKER_REQUIREMENT) != null;
    }

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        switch (object.getAttributes().get("Acceptance Status")) {
        case "":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_ACCEPTANCE_NONE));
            break;
        case "not agreed":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_ACCEPTANCE_NOT_AGREED));
            break;
        case "changed requirement":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_ACCEPTANCE_CHANGED_REQ));
            break;
        case "to clarify":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_ACCEPTANCE_TO_CLARIFY));
            break;
        case "partially agreed":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_ACCEPTANCE_PARTLY_AGREED));
            break;
        case "conflict":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_ACCEPTANCE_CONFLICT));
            break;
        case "agreed":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_ACCEPTANCE_AGREED));
            break;
        }
    }

}
