package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class contain the results of an algorithm execution.
 *
 * @author jonwink
 *
 */
public class ScenarioResult {

    private final Map<TreeNode, Integer> states = new HashMap<>();

    private BigDecimal probability;

    private double executionTime;

    /**
     * Returns the time needed to execute the algorithm, in milliseconds.
     *
     * @return
     */
    public double getExecutionTime() {
        return executionTime;
    }

    /**
     * Sets the time needed to execute the algorithm, in milliseconds.
     *
     * @param executionTime
     */
    public void setExecutionTime(final double executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * Returns the probability of the tag sequence.
     *
     * @return
     */
    public BigDecimal getProbability() {
        return probability;
    }

    /**
     * Sets the probability of the tag sequence.
     *
     * @param probability
     */
    public void setProbability(final BigDecimal probability) {
        this.probability = probability;
    }

    /**
     * Returns the state for the node <code>node</code>
     *
     * @param node
     * @return
     */
    public int getState(final TreeNode node) {
        if (states.containsKey(node)) {
            return states.get(node);
        } else {
            return 0;
        }
    }

    /**
     * Sets the state for the node <code>node</code>.
     * 
     * @param node
     * @param state
     */
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
