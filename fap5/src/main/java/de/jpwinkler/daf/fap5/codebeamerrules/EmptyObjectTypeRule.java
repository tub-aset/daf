package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class EmptyObjectTypeRule extends PredicateIssueRule {

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_EMPTY_OBJECT_TYPE;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_EMPTY_OBJECT_TYPE;
    }

    @Override
    protected String getIssueType() {
        return "Fehlende Attributierung";
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        return o.getAttributes().get(CodeBeamerConstants.ATTRIBUTE_OBJECT_TYPE).isEmpty();
    }

    @Override
    protected String getDescription(final DoorsObject object) {
        return "Objekt Type ist nicht gesetzt.";
    }

}
