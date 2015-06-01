package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.model.srs.Description;
import de.jpwinkler.daf.fap5.model.srs.SrsFactory;

public class DescriptionRule extends FunctionalSpecificationElementRule<Description> {

    @Override
    protected Marker createMarker(final Description fsc) {
        return new Marker(SrsConstants.MARKER_DESCRIPTION);
    }

    @Override
    protected Description createObject(final DoorsObject object, final RuleContext context) {
        final Description description = SrsFactory.eINSTANCE.createDescription();
        description.setText(object.getText());
        return description;
    }

    @Override
    protected String getFOObjectType() {
        return SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE_DESCRIPTION;
    }

    @Override
    protected boolean isContainer() {
        return false;
    }

}
