package de.jpwinkler.daf.fap5.codebeamerrules;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.MarkerTypeObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.NotObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.Precondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.RulesExecutedPrecondition;

public class RequirementRule extends AbstractRule {


    private static final List<String> VALID_OBJECT_TYPES = Arrays.asList("requirement", "predefinition", "s-send", "s-receive");

    @Override
    public Precondition getPrecondition() {
        return new RulesExecutedPrecondition(DeletedReqRule.class);
    }

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return new NotObjectPrecondition(new MarkerTypeObjectPrecondition(CodeBeamerConstants.MARKER_DELETED_REQ));
    }

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        if (VALID_OBJECT_TYPES.contains(object.getAttributes().get(CodeBeamerConstants.ATTRIBUTE_OBJECT_TYPE))) {
            final Marker marker = new Marker(CodeBeamerConstants.MARKER_REQUIREMENT);
            marker.setAttribute(CodeBeamerConstants.MARKER_REQUIREMENT_LENGTH, object.getText().length());
            context.addMarker(object, marker);
        }
    }

}
