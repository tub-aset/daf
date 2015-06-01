package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.AttributeExistsObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;

public class HeadingAsRequirementRule extends PredicateIssueRule {

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return new AttributeExistsObjectPrecondition(CodeBeamerConstants.ATTRIBUTE_OBJECT_TYPE);
    }

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_HEADING_AS_REQUIREMENT;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_HEADING_AS_REQUIREMENT;
    }

    @Override
    protected String getIssueType() {
        return "\"Heading\" falsch attributiert";
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        return o.isHeading() && "requirement".equals(o.getAttributes().get(CodeBeamerConstants.ATTRIBUTE_OBJECT_TYPE));
    }

    @Override
    protected String getDescription(final DoorsObject object) {
        return "Objekt ist eine Anforderung in Form einer �berschrift.";
    }

}
