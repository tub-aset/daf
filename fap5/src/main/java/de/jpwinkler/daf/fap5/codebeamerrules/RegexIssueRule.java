package de.jpwinkler.daf.fap5.codebeamerrules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerFactory;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;

public abstract class RegexIssueRule extends AbstractRule {

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        final CodeBeamerModel cbm = (CodeBeamerModel) context.getRootModelObject();

        final Matcher matcher = getRegexPattern().matcher(getObjectText(object));

        while (matcher.find()) {
            final Marker marker = new Marker(getMarkerType());

            final Issue issue = CodebeamerFactory.eINSTANCE.createIssue();
            issue.setSeverity(getSeverity(object));
            issue.setDescription(getDescription(object, matcher.group()));
            issue.setIssueType(getIssueType());
            issue.setSource(object);
            marker.setAttribute(Marker.MODEL_OBJECT, issue);
            cbm.getIssues().add(issue);
            context.addMarker(object, marker);
        }
    }

    protected String getObjectText(final DoorsObject object) {
        return object.getText();
    }

    protected abstract String getIssueType();

    protected String getDescription(final DoorsObject object, final String patternInstance) {
        return String.format("Rule %s matched object %s, pattern found: %s", getClass().getSimpleName(), object.getObjectIdentifier(), patternInstance);
    }

    protected abstract long getSeverity(DoorsObject object);

    protected abstract String getMarkerType();

    protected abstract Pattern getRegexPattern();

}
