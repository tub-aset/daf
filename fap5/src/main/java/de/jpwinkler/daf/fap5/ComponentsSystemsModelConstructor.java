package de.jpwinkler.daf.fap5;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Rule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleBasedModelConstructor;
import de.jpwinkler.daf.fap5.componentsystemsrules.ComponentsSystemsRule;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;

public class ComponentsSystemsModelConstructor extends RuleBasedModelConstructor {

    @Override
    protected ModelObject createRootModelObject() {
        return ComponentsSystemsPackage.eINSTANCE.getComponentsSystemsFactory().createComponentsSystemsModel();
    }

    @Override
    public boolean probe(final DoorsModule module) {
        return true;
    }

    @Override
    public List<Class<? extends Rule>> getRules() {
        return Arrays.asList(ComponentsSystemsRule.class);
    }
}
