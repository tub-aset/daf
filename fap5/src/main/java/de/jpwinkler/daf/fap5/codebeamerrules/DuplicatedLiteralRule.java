package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;

public class DuplicatedLiteralRule extends PredicateIssueRule {

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        return (object, context) -> object.getParent() instanceof DoorsObject && !object.getText().trim().isEmpty();
    }

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_DUPLICATED_LITERAL;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_DUPLICATED_LITERAL;
    }

    @Override
    protected String getIssueType() {
        return "Dupliziertes Literal";
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        return ((DoorsObject) o.getParent()).getText().replace(" ", "").contains(o.getText().trim()) && o.getParent().getObjects().size() == 1;
    }

    @Override
    protected String getDescription(final DoorsObject object) {
        return "�bergeordnetes Objekt enth�lt ebenfalls den Text '" + object.getText() + "'.";
    }
}
