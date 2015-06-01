package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.Precondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.preconditions.RulesExecutedPrecondition;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.MarkerUtil;
import de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer;
import de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationElement;

public abstract class FunctionalSpecificationElementRule<T extends FunctionalSpecificationElement> extends AbstractSrsRule {

    @Override
    public Precondition getPrecondition() {
        return new RulesExecutedPrecondition(VehicleFunctionRule.class);
    }

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        if (object.getAttributes().get(SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE).equals(getFOObjectType())) {
            final DoorsObject parentObject = MarkerUtil.findFirstParentWithMarker(object, context, SrsConstants.MARKER_FUNCTIONAL_SPECIFICATION_CONTAINER);
            if (parentObject != null) {
                final FunctionalSpecificationContainer parent = (FunctionalSpecificationContainer) context.getMarker(parentObject, SrsConstants.MARKER_FUNCTIONAL_SPECIFICATION_CONTAINER).getAttribute(Marker.MODEL_OBJECT);
                final T fsc = createObject(object, context);
                parent.getElements().add(fsc);

                context.addMarker(object, createMarker(fsc));
                if (isContainer()) {
                    final Marker marker = new Marker(SrsConstants.MARKER_FUNCTIONAL_SPECIFICATION_CONTAINER);
                    marker.setAttribute(Marker.MODEL_OBJECT, fsc);
                    context.addMarker(object, marker);
                }
            } else {
                context.addMarker(object, new Marker(SrsConstants.MARKER_FUNCTIONAL_SPECIFICATION_ELEMENT_WITHOUT_CONTAINER));
            }

        }

    }

    protected abstract Marker createMarker(final T fsc);

    protected abstract T createObject(final DoorsObject object, final RuleContext context);

    protected abstract String getFOObjectType();

    protected abstract boolean isContainer();

}
