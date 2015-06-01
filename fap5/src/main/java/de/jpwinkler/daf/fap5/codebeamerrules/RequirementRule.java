package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class RequirementRule extends AbstractRule {

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        if ("requirement".equals(object.getAttributes().get("Object Type")) || "predefinition".equals(object.getAttributes().get("Object Type"))) {
            final Marker marker = new Marker(CodeBeamerConstants.MARKER_REQUIREMENT);
            marker.setAttribute(CodeBeamerConstants.MARKER_REQUIREMENT_LENGTH, object.getText().length());
            context.addMarker(object, marker);
        }
    }

}
