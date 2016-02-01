package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class DeletedReqRule extends AbstractRule {

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        if ("deleted requirement".equals(object.getAttributes().get("Acceptance Status")) || "deleted".equals(object.getAttributes().get("cdmChangeStatus"))) {
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_DELETED_REQ));
        }
    }

}
