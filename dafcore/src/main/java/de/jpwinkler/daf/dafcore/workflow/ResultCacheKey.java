package de.jpwinkler.daf.dafcore.workflow;

import java.util.Map;

import de.jpwinkler.daf.workflowdsl.Step;

public class ResultCacheKey {

    private Step step;

    private Map<String, Object> variables;

    public ResultCacheKey(final Step step, final Map<String, Object> variables) {
        super();
        this.step = step;
        this.variables = variables;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(final Step step) {
        this.step = step;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(final Map<String, Object> variables) {
        this.variables = variables;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((step == null) ? 0 : step.hashCode());
        result = prime * result + ((variables == null) ? 0 : variables.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultCacheKey other = (ResultCacheKey) obj;
        if (step == null) {
            if (other.step != null) {
                return false;
            }
        } else if (!step.equals(other.step)) {
            return false;
        }
        if (variables == null) {
            if (other.variables != null) {
                return false;
            }
        } else if (!variables.equals(other.variables)) {
            return false;
        }
        return true;
    }

}
