package de.jpwinkler.daf.documenttagging.recursiveviterbi.algorithms;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.Scenario;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.ScenarioResult;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.TreeNode;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.util.BigDecimals;

/**
 * This class is the base class for algorithm implementations that tag the tree
 * of a {@link Scenario} with states. This is useful for comparing results and
 * execution times of different algorithm implementations.
 *
 * @author jonwink
 *
 */
public abstract class AbstractAlgorithm {

    private Map<TreeNode, Integer> currentStates;

    private Scenario scenario;

    private ScenarioResult result;

    /**
     * Calculates and returns the total state probability of <code>node</code>
     * using the states currently stored in <code>currentStates</code>.
     *
     * @param node
     * @return
     */
    protected BigDecimal calcProbability(final TreeNode node) {

        BigDecimal result = BigDecimals.ONE;

        for (int i = 0; i < node.getChildren().size(); i++) {
            final TreeNode currentNode = node.getChildren().get(i);

            final int prevState = (i > 0) ? getCurrentStates().get(currentNode.getPrevious()) : 0;
            final int parentState = getCurrentStates().get(currentNode.getParent()) != null ? getCurrentStates().get(currentNode.getParent()) : 0;
            final int currentState = getCurrentStates().get(currentNode);
            result = result.multiply(getScenario().getProbabilityModel().getTransitionProbability(parentState, prevState, currentState), BigDecimals.CONTEXT);
            result = result.multiply(getScenario().getProbabilityModel().getObservationProbability(currentState, currentNode.getObservation()), BigDecimals.CONTEXT);
            result = result.multiply(calcProbability(currentNode), BigDecimals.CONTEXT);
        }

        return result;
    }

    /**
     * A shortcut for calculating the total state probability of the scenario's
     * tree.
     *
     * @return
     */
    protected BigDecimal calcProbability() {
        return calcProbability(getScenario().getTree());
    }

    /**
     * Executes the algorithm on the given scenario.
     *
     * @param scenario
     * @return
     */
    public final ScenarioResult run(final Scenario scenario) {
        result = new ScenarioResult();
        currentStates = new HashMap<>();
        this.scenario = scenario;

        final long t1 = System.nanoTime();
        solve();
        final long t2 = System.nanoTime();

        result.setExecutionTime((t2 - t1) / 1000000.0);

        return result;
    }

    /**
     * Implementing classes provide their algorithm logic in this method.
     * Implementing classes must store states in the <code>currentStates</code>
     * map and call <code>saveResult</code> to declare the states stored in the
     * <code>currentStates</code> map as the result.
     */
    protected abstract void solve();

    /**
     * Returns the map storing the current states.
     *
     * @return
     */
    public Map<TreeNode, Integer> getCurrentStates() {
        return currentStates;
    }

    /**
     * Returns the scenario.
     */
    public Scenario getScenario() {
        return scenario;
    }

    /**
     * Stores a copy of the states stored in the <code>currentStates</code> as
     * the result and computes the result's probability. Further modifications
     * to the <code>currentStates</code> map do not alter the saved result.
     */
    protected void saveResult() {
        for (final Entry<TreeNode, Integer> e : getCurrentStates().entrySet()) {
            if (e.getValue() != null) {
                result.setState(e.getKey(), e.getValue());
            }
        }
        result.setProbability(calcProbability());
    }

}
