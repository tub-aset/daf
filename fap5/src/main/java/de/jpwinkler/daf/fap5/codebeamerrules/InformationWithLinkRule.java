package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;

public class InformationWithLinkRule extends PredicateIssueRule {

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return (object, context) -> context.getMarker(object, CodeBeamerConstants.MARKER_INFORMATION) != null;
    }

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_INFORMATION_WITH_LINK;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_INFORMATION_WITH_LINK;
    }

    @Override
    protected String getIssueType() {
        return "\"Information\" mit Link";
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        return !(o.getOutgoingLinks().isEmpty() && o.getIncomingLinks().isEmpty());
    }
}
