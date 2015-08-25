package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.MarkerTypeObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.NotObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.Precondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.RulesExecutedPrecondition;

public class EmptyHeadingsRule extends PredicateIssueRule {

    @Override
    public Precondition getPrecondition() {
        return new RulesExecutedPrecondition(HeadingAsRequirementRule.class);
    }

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return new NotObjectPrecondition(new MarkerTypeObjectPrecondition(CodeBeamerConstants.MARKER_HEADING_AS_REQUIREMENT));
    }

    @Override
    protected long getSeverity(final DoorsObject object) {
        if (object.getObjectLevel() <= CodeBeamerConstants.SEVERITY_EMPTY_HEADING.length) {
            return CodeBeamerConstants.SEVERITY_EMPTY_HEADING[object.getObjectLevel() - 1];
        } else {
            return 0;
        }
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_EMPTY_HEADING;
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        return o.isHeading() && o.getChildren().isEmpty();
    }

    @Override
    protected String getDescription(final DoorsObject object) {
        return "Objekt ist eine �berschrift ohne untergeordnete Objekte";
    }

    @Override
    protected String getIssueType() {
        return "Leere �berschrift";
    }
}
