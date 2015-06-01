package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.Precondition;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerFactory;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;

public class EmptyDocumentRule extends AbstractRule {

    private boolean containsObjects;

    @Override
    public Precondition getPrecondition() {
        return context -> true;
    }

    @Override
    public void prepare(final RuleContext context) {
        containsObjects = false;
    }

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        containsObjects = true;
    }

    @Override
    public void finish(final RuleContext context) {
        if (!containsObjects) {
            final Issue issue = CodebeamerFactory.eINSTANCE.createIssue();
            issue.setSeverity(0);
            issue.setDescription("Dokument enth�lt keine Anforderungen");
            issue.setIssueType("Leeres Dokument");
            ((CodeBeamerModel) context.getRootModelObject()).getIssues().add(issue);
        }
    }

}
