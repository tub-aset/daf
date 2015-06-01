package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class ObjectTextAndHeadingRule extends PredicateIssueRule {

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_OBJECT_TEXT_AND_HEADING;
    }

    @Override
    protected String getMarkerType() {
        return "objecttext_and_objectheading";
    }

    @Override
    protected String getIssueType() {
        return "Object Text und Heading";
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        return o.getObjectText() != null && !o.getObjectText().isEmpty() && o.getObjectHeading() != null && !o.getObjectHeading().isEmpty();
    }

    @Override
    protected String getDescription(final DoorsObject object) {
        return "Objekt enth�lt sowohl �berschrift als auch Text.";
    }
}
