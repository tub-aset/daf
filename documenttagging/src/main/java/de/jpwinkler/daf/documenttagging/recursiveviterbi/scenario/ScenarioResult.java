package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ScenarioResult {

    private final Map<TreeNode, Integer> states = new HashMap<>();

    private BigDecimal probability;

    private double executionTime;

    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(final double executionTime) {
        this.executionTime = executionTime;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(final BigDecimal probability) {
        this.probability = probability;
    }

    public int getState(final TreeNode node) {
        if (states.containsKey(node)) {
            return states.get(node);
        } else {
            return 0;
        }
    }

    public void setState(final TreeNode node, final int state) {
        states.put(node, state);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((probability == null) ? 0 : probability.hashCode());
        result = prime * result + ((states == null) ? 0 : states.hashCode());
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
        final ScenarioResult other = (ScenarioResult) obj;
        if (probability == null) {
            if (other.probability != null) {
                return false;
            }
        } else if (!probability.equals(other.probability)) {
            return false;
        }
        if (states == null) {
            if (other.states != null) {
                return false;
            }
        } else if (!states.equals(other.states)) {
            return false;
        }
        return true;
    }

}
