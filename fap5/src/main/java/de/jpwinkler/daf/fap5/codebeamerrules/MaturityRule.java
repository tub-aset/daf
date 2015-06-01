package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;

public class MaturityRule extends AbstractRule {

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return (object, context) -> object.getAttributes().containsKey("Maturity") && context.getMarker(object, CodeBeamerConstants.MARKER_REQUIREMENT) != null;
    }

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {

        switch (object.getAttributes().get("Maturity")) {
        case "open":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_MATURITY_OPEN));
            break;
        case "specified":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_MATURITY_SPECIFIED));
            break;
        case "follow_up":
            if (object.getAttributes().get("comment") != null && object.getAttributes().get("comment").contains("###")) {
                context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_MATURITY_FOLLOW_UP_HASHTAGS));
            } else {
                context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_MATURITY_FOLLOW_UP));
            }
            break;
        case "agreed":
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_MATURITY_AGREED));
            break;
        }
    }

}
