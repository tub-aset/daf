package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.model.srs.Precondition;
import de.jpwinkler.daf.fap5.model.srs.SrsFactory;

public class PreconditionRule extends FunctionalSpecificationElementRule<Precondition> {

    @Override
    protected Marker createMarker(final Precondition fsc) {
        return new Marker(SrsConstants.MARKER_PRECONDITION);
    }

    @Override
    protected Precondition createObject(final DoorsObject object, final RuleContext context) {
        final Precondition precondition = SrsFactory.eINSTANCE.createPrecondition();
        precondition.setName(object.getText());
        return precondition;
    }

    @Override
    protected String getFOObjectType() {
        return SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE_PRECONDITION;
    }

    @Override
    protected boolean isContainer() {
        return true;
    }

}
