package de.jpwinkler.daf.fap5;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Rule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleBasedModelConstructor;
import de.jpwinkler.daf.fap5.model.srs.SrsPackage;
import de.jpwinkler.daf.fap5.srsrules.DescriptionRule;
import de.jpwinkler.daf.fap5.srsrules.EndConditionRule;
import de.jpwinkler.daf.fap5.srsrules.FunctionContributionRule;
import de.jpwinkler.daf.fap5.srsrules.FunctionalDescriptionChapterRule;
import de.jpwinkler.daf.fap5.srsrules.HeadingRule;
import de.jpwinkler.daf.fap5.srsrules.PreconditionRule;
import de.jpwinkler.daf.fap5.srsrules.TriggerRule;
import de.jpwinkler.daf.fap5.srsrules.VehicleFunctionRule;

public class SrsModelConstructor extends RuleBasedModelConstructor {

    @Override
    protected ModelObject createRootModelObject() {
        return SrsPackage.eINSTANCE.getSrsFactory().createSRSModel();
    }

    @Override
    protected List<Class<? extends Rule>> getRules() {
        return Arrays.asList(VehicleFunctionRule.class, FunctionalDescriptionChapterRule.class,
                DescriptionRule.class,
                EndConditionRule.class,
                FunctionContributionRule.class,
                HeadingRule.class,
                PreconditionRule.class,
                TriggerRule.class);
    }

    @Override
    protected boolean probe(final DoorsModule module) {
        return true;
    }

}