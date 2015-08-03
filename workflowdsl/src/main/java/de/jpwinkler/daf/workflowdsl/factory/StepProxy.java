package de.jpwinkler.daf.workflowdsl.factory;

import de.jpwinkler.daf.workflowdsl.Step;

public class StepProxy extends Step {

    private final String stepRef;

    public String getStepRef() {
        return stepRef;
    }

    public StepProxy(final String stepRef) {
        super();
        this.stepRef = stepRef;
    }

}
