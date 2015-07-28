package de.jpwinkler.daf.documenttagging.recursiveviterbi.algorithms;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.Scenario;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.ScenarioResult;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.TreeNode;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.util.BigDecimals;

public abstract class AbstractAlgorithm {

    private Map<TreeNode, Integer> currentStates;

    private Scenario scenario;

    private ScenarioResult result;

    protected BigDecimal calcProbability(final TreeNode node) {

        BigDecimal result = BigDecimals.ONE;

        for (int i = 0; i < node.getChildren().size(); i++) {
            final TreeNode currentNode = node.getChildren().get(i);

            final int prevState = (i > 0) ? getCurrentStates().get(currentNode.getPrevious()) : 0;
            final int parentState = getCurrentStates().get(currentNode.getParent());
            final int currentState = getCurrentStates().get(currentNode);
            result = result.multiply(getScenario().getProbabilityModel().getTransitionProbability(parentState, prevState, currentState), BigDecimals.CONTEXT);
            result = result.multiply(getScenario().getProbabilityModel().getObservationProbability(currentState, currentNode.getObservation()), BigDecimals.CONTEXT);
            result = result.multiply(calcProbability(currentNode), BigDecimals.CONTEXT);
        }

        return result;
    }

    protected BigDecimal calcProbability() {
        return getScenario().getProbabilityModel().getTransitionProbability(0, 0, getCurrentStates().get(getScenario().getTree())).multiply(getScenario().getProbabilityModel().getObservationProbability(getCurrentStates().get(getScenario().getTree()), getScenario().getTree().getObservation()), BigDecimals.CONTEXT).multiply(calcProbability(getScenario().getTree()), BigDecimals.CONTEXT);
    }

    public final ScenarioResult run(final Scenario scenario) {
        result = new ScenarioResult();
        currentStates = new HashMap<>();
        this.scenario = scenario;

        final long t1 = System.nanoTime();
        solve();
        final long t2 = System.nanoTime();

        result.setExecutionTime((t2 - t1) / 1000000.0);

        return getResult();
    }

    protected abstract void solve();

    protected ScenarioResult getResult() {
        return result;
    }

    public Map<TreeNode, Integer> getCurrentStates() {
        return currentStates;
    }

    public Scenario getScenario() {
        return scenario;
    }

    protected void saveResult() {
        for (final Entry<TreeNode, Integer> e : getCurrentStates().entrySet()) {
            getResult().setState(e.getKey(), e.getValue());
        }
        getResult().setProbability(calcProbability());
    }

}
