package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class CommentRule extends PredicateIssueRule {

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_COMMENT;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_COMMENT;
    }

    @Override
    protected String getIssueType() {
        return "Kommentar";
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        return o.getAttributes().containsKey(CodeBeamerConstants.ATTRIBUTE_COMMENT) && !o.getAttributes().get(CodeBeamerConstants.ATTRIBUTE_COMMENT).isEmpty();
    }

    @Override
    protected String getDescription(final DoorsObject object) {
        return "Objekt enth�lt ein Kommentar.";
    }
}
