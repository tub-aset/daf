package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.CascadingObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.MarkerTypeObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.ObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.objectpreconditions.OrObjectPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.MarkerTypePrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.NotPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.Precondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.RulesExecutedPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.MarkerUtil;
import de.jpwinkler.daf.fap5.model.srs.SRSModel;
import de.jpwinkler.daf.fap5.model.srs.SrsFactory;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunction;

public class VehicleFunctionRule extends AbstractRule {

    @Override
    public Precondition getPrecondition() {
        return new RulesExecutedPrecondition(FunctionalDescriptionChapterRule.class);
    }

    @Override
    public ObjectPrecondition getObjectPrecondition() {
        // TODO: caching preconditions or putting them in static objects might
        // greatly improve performance
        return new OrObjectPrecondition(
                new CascadingObjectPrecondition(new MarkerTypeObjectPrecondition(SrsConstants.MARKER_FUNCTIONAL_DESCRIPTION_CHAPTER), false),
                new NotPrecondition(new MarkerTypePrecondition(SrsConstants.MARKER_FUNCTIONAL_DESCRIPTION_CHAPTER)));
    }

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        final boolean isVehicleFunction = object.getAttributes().get(SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE).equals(SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE_VEHICLE_FUNCTION);
        final boolean isSubFunction = object.getAttributes().get(SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE).equals(SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE_SUBFUNCTION);
        if ((isVehicleFunction || isSubFunction) && object.isHeading()) {
            final VehicleFunction vf = SrsFactory.eINSTANCE.createVehicleFunction();
            vf.setName(object.getText());
            vf.setSource(object);
            final Marker marker = new Marker(SrsConstants.MARKER_VEHICLE_FUNCTION);
            marker.setAttribute(Marker.MODEL_OBJECT, vf);
            context.addMarker(object, marker);

            final DoorsObject parent = MarkerUtil.findFirstParentWithMarker(object, context, SrsConstants.MARKER_VEHICLE_FUNCTION);
            if (parent != null) {
                final VehicleFunction parentVf = (VehicleFunction) context.getMarker(parent, SrsConstants.MARKER_VEHICLE_FUNCTION).getAttribute(Marker.MODEL_OBJECT);
                parentVf.getSubFunctions().add(vf);
                context.addMarker(object, new Marker(SrsConstants.MARKER_SUBFUNCTION));
            } else {
                ((SRSModel) context.getRootModelObject()).getVehicleFunctions().add(vf);
            }
            final Marker marker2 = new Marker(SrsConstants.MARKER_FUNCTIONAL_SPECIFICATION_CONTAINER);
            marker2.setAttribute(Marker.MODEL_OBJECT, vf);
            context.addMarker(object, marker2);
        }
    }

}
