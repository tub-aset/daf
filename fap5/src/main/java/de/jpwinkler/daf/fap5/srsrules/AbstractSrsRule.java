package de.jpwinkler.daf.fap5.srsrules;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.AbstractRule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel;

public abstract class AbstractSrsRule extends AbstractRule {

    protected ComponentsSystemsModel getComponentsSystemsModel(final RuleContext context) {
        return context.getModelConstructor().getSingleParameter("cs");
    }

}
