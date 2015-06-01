package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class FunctionalDescriptionChapterRule extends AbstractRule {

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        if (object.isHeading() && ("Function Description".equals(object.getText()) || "Funktionsbeschreibung".equals(object.getText()))) {
            context.addMarker(object, new Marker(SrsConstants.MARKER_FUNCTIONAL_DESCRIPTION_CHAPTER));
        }
    }

}
