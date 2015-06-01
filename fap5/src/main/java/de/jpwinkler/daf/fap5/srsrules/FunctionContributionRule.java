package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;
import de.jpwinkler.daf.fap5.model.srs.SrsFactory;

public class FunctionContributionRule extends FunctionalSpecificationElementRule<FunctionContribution> {

    private FunctionContributionTarget findFunctionContributionTarget(final RuleContext context, final String componentSystem) {
        for (final FunctionContributionTarget fct : getComponentsSystemsModel(context).getFunctionContributionTargets()) {
            if (componentSystem.startsWith(fct.getName())) {
                return fct;
            }
        }
        return null;
    }

    @Override
    protected Marker createMarker(final FunctionContribution fsc) {
        return new Marker(fsc.getTarget() != null ? SrsConstants.MARKER_FUNCTION_CONTRIBUTION : SrsConstants.MARKER_FUNCTION_CONTRIBUTION_WITHOUT_TARGET);
    }

    @Override
    protected FunctionContribution createObject(final DoorsObject object, final RuleContext context) {
        final FunctionContribution fc = SrsFactory.eINSTANCE.createFunctionContribution();
        fc.setText(object.getText());
        fc.setTarget(findFunctionContributionTarget(context, object.getAttributes().get(SrsConstants.ATTRIBUTE_COMPONENTS_SYSTEMS)));
        return fc;
    }

    @Override
    protected String getFOObjectType() {
        return SrsConstants.ATTRIBUTE_FO_OBJECT_TYPE_FUNCTION_CONTRIBUTION;
    }

    @Override
    protected boolean isContainer() {
        return false;
    }

}
