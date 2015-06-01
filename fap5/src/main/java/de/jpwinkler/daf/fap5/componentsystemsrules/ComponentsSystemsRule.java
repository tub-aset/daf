package de.jpwinkler.daf.fap5.componentsystemsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.MarkerUtil;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsFactory;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.componentssystems.Functionality;

public class ComponentsSystemsRule extends AbstractRule {

    @Override
    public void preTraverse(final DoorsObject object, final RuleContext context) {
        Marker marker = null;
        FunctionContributionTarget fcTarget = null;
        switch (object.getAttributes().get("Object Type")) {
        case "Component":
            marker = new Marker(ComponentsSystemsConstants.MARKER_COMPONENT);
            fcTarget = ComponentsSystemsFactory.eINSTANCE.createComponent();
            break;
        case "Logical Component":
            marker = new Marker(ComponentsSystemsConstants.MARKER_LOGICAL_COMPONENT);
            fcTarget = ComponentsSystemsFactory.eINSTANCE.createLogicalComponent();
            break;
        case "System":
            marker = new Marker(ComponentsSystemsConstants.MARKER_SYSTEM);
            fcTarget = ComponentsSystemsFactory.eINSTANCE.createSystem();
            break;
        case "Functionality":
            marker = new Marker(ComponentsSystemsConstants.MARKER_FUNCTIONALITY);
            fcTarget = ComponentsSystemsFactory.eINSTANCE.createFunctionality();
            final DoorsObject systemObject = MarkerUtil.findFirstParentWithMarker(object, context, ComponentsSystemsConstants.MARKER_SYSTEM);
            if (systemObject != null) {
                ((Functionality) fcTarget).setSystem((de.jpwinkler.daf.fap5.model.componentssystems.System) context.getMarker(systemObject, ComponentsSystemsConstants.MARKER_SYSTEM).getAttribute(Marker.MODEL_OBJECT));
            }
            break;
        default:
            return;
        }

        if (marker != null) {
            fcTarget.setName(object.getText());
            fcTarget.setAcronym(object.getAttributes().get("Acronym"));
            fcTarget.setDepartment(object.getAttributes().get("Department"));
            fcTarget.setResponsibility(object.getAttributes().get("Responsibility"));
            fcTarget.setSource(object);
            ((ComponentsSystemsModel) context.getRootModelObject()).getFunctionContributionTargets().add(fcTarget);

            marker.setAttribute(Marker.MODEL_OBJECT, fcTarget);
            context.addMarker(object, marker);
        }

    }

}
