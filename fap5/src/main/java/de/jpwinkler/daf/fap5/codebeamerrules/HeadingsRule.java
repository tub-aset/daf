package de.jpwinkler.daf.fap5.codebeamerrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class HeadingsRule extends AbstractRule {

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        if (object.isHeading() && "heading".equals(object.getAttributes().get(CodeBeamerConstants.ATTRIBUTE_OBJECT_TYPE))) {
            context.addMarker(object, new Marker(CodeBeamerConstants.MARKER_HEADING));
        }
    }

}
