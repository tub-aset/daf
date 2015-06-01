package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.model.srs.Heading;
import de.jpwinkler.daf.fap5.model.srs.SrsFactory;

public class HeadingRule extends FunctionalSpecificationElementRule<Heading> {

    @Override
    protected Marker createMarker(final Heading fsc) {
        return new Marker(SrsConstants.MARKER_HEADING);
    }

    @Override
    protected Heading createObject(final DoorsObject object, final RuleContext context) {
        final Heading heading = SrsFactory.eINSTANCE.createHeading();
        heading.setName(object.getText());
        return heading;
    }

    @Override
    protected String getFOObjectType() {
        return SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE_HEADING;
    }

    @Override
    protected boolean isContainer() {
        return true;
    }

}
