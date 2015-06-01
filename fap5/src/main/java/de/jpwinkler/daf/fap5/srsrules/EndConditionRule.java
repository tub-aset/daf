package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.model.srs.EndCondition;
import de.jpwinkler.daf.fap5.model.srs.SrsFactory;

public class EndConditionRule extends FunctionalSpecificationElementRule<EndCondition> {

    @Override
    protected Marker createMarker(final EndCondition fsc) {
        return new Marker(SrsConstants.MARKER_END_CONDITION);
    }

    @Override
    protected EndCondition createObject(final DoorsObject object, final RuleContext context) {
        final EndCondition endCondition = SrsFactory.eINSTANCE.createEndCondition();
        endCondition.setName(object.getText());
        return endCondition;
    }

    @Override
    protected String getFOObjectType() {
        return SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE_END_CONDITION;
    }

    @Override
    protected boolean isContainer() {
        return true;
    }

}
