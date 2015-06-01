package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.model.srs.SrsFactory;
import de.jpwinkler.daf.fap5.model.srs.Trigger;

public class TriggerRule extends FunctionalSpecificationElementRule<Trigger> {

    @Override
    protected Marker createMarker(final Trigger fsc) {
        return new Marker(SrsConstants.MARKER_TRIGGER);
    }

    @Override
    protected Trigger createObject(final DoorsObject object, final RuleContext context) {
        final Trigger trigger = SrsFactory.eINSTANCE.createTrigger();
        trigger.setName(object.getText());
        return trigger;
    }

    @Override
    protected String getFOObjectType() {
        return SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE_TRIGGER;
    }

    @Override
    protected boolean isContainer() {
        return true;
    }
}
