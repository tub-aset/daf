package de.jpwinkler.daf.workflowdsl.factory;

import de.jpwinkler.daf.workflowdsl.Step;

public class StepProxy extends Step {

    private String stepRef;

    public String getStepRef() {
        return stepRef;
    }

    public void setStepRef(final String stepRef) {
        this.stepRef = stepRef;
    }

    public StepProxy(final String stepRef) {
        super();
        this.stepRef = stepRef;
    }

}
