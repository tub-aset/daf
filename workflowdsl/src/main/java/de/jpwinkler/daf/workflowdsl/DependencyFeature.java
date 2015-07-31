package de.jpwinkler.daf.workflowdsl;

import java.util.ArrayList;
import java.util.List;

public class DependencyFeature extends OperationFeature {

    private Step step;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    private final List<Variable> variables = new ArrayList<>();

    public List<Variable> getVariables() {
        return variables;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(final Step step) {
        this.step = step;
    }

}
