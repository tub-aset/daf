package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class QuestionMarkRule extends PredicateIssueRule {

    @Override
    protected String getIssueType() {
        return "Platzhalter \"?\"";
    }

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_QUESTION_MARK;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_QUESTION_MARK;
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        return o.getText().contains("?");
    }

    @Override
    protected String getDescription(final DoorsObject object) {
        return "Objekt enth�lt ein Fragezeichen";
    }
}
