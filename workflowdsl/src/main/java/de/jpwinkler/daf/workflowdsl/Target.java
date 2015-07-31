package de.jpwinkler.daf.workflowdsl;

public class Target extends WorkflowElement {

    private Step step;

    public Step getStep() {
        return step;
    }

    public void setStep(final Step step) {
        this.step = step;
    }

}
