package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.AttributeExistsObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.MarkerUtil;

public class IllegalFOObjectTypeRule extends PredicateIssueRule {

    private String description;

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return new AttributeExistsObjectPrecondition(CodeBeamerConstants.ATTRIBUTE_FO_OBJECT_TYPE);
    }

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_ILLEGAL_FO_OBJECT_TYPE;
    }

    @Override
    protected String getMarkerType() {
        return "illegal_fo_object_type";
    }

    @Override
    protected String getIssueType() {
        return "Ung�ltige FO_Object_Type-Verwendung";
    }

    @Override
    protected String getDescription(final DoorsObject object) {
        return description;
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        final String foObjectType = o.getAttributes().get(CodeBeamerConstants.ATTRIBUTE_FO_OBJECT_TYPE);

        final DoorsObject vehicleFunctionObject = MarkerUtil.findFirstParentWithAttributeValue(o, context, CodeBeamerConstants.ATTRIBUTE_FO_OBJECT_TYPE, "Vehicle function");
        final DoorsObject subfunctionObject = MarkerUtil.findFirstParentWithAttributeValue(o, context, CodeBeamerConstants.ATTRIBUTE_FO_OBJECT_TYPE, "Subfunction");
        if ("Function contribution".equals(foObjectType) && vehicleFunctionObject == null && subfunctionObject == null) {
            description = "Funktionsbeitrag ohne Fahrzeugfunktion";
            return true;
        } else if ("Trigger".equals(foObjectType) && vehicleFunctionObject == null && subfunctionObject == null) {
            description = "Ausl�ser ohne Fahrzeugfunktion";
            return true;
        } else if ("Precondition".equals(foObjectType) && vehicleFunctionObject == null && subfunctionObject == null) {
            description = "Vorbedingung ohne Fahrzeugfunktion";
            return true;
        } else if ("End condition".equals(foObjectType) && vehicleFunctionObject == null && subfunctionObject == null) {
            description = "Endbedingung ohne Fahrzeugfunktion";
            return true;
        } else {
            return false;
        }

    }

}
