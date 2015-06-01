package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerFactory;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;

public abstract class PredicateIssueRule extends AbstractRule {

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        final CodeBeamerModel cbm = (CodeBeamerModel) context.getRootModelObject();

        if (testObject(object, context)) {
            final Marker marker = new Marker(getMarkerType());

            final Issue issue = CodebeamerFactory.eINSTANCE.createIssue();
            issue.setSeverity(getSeverity(object));
            issue.setDescription(getDescription(object));
            issue.setSource(object);
            issue.setIssueType(getIssueType());
            marker.setAttribute(Marker.MODEL_OBJECT, issue);
            cbm.getIssues().add(issue);
            context.addMarker(object, marker);
        }
    }

    protected String getDescription(final DoorsObject object) {
        return String.format("Rule %s matched object %s", getClass().getSimpleName(), object.getObjectIdentifier());
    }

    protected abstract long getSeverity(DoorsObject object);

    protected abstract String getMarkerType();

    protected abstract String getIssueType();

    protected abstract boolean testObject(DoorsObject o, RuleContext context);
}
