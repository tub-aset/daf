package de.jpwinkler.daf.documenttagging.recursiveviterbi.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.jpwinkler.daf.documenttagging.recursiveviterbi.AbstractRecursiveViterbiAlgorithm;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.TreeNode;

/**
 * An algorithm using recursive viterbi to determine the most probable states
 * for a scenario.
 *
 * @author jonwink
 *
 */
public class RecursiveViterbiAlgorithm extends AbstractAlgorithm {

    final List<Integer> states = new ArrayList<>();

    private final AbstractRecursiveViterbiAlgorithm<TreeNode, Integer> alg = new AbstractRecursiveViterbiAlgorithm<TreeNode, Integer>() {

        @Override
        protected List<Integer> getStates() {
            return states;
        }

        @Override
        protected double getProbability(final TreeNode node, final Integer state, final Integer parentState, final Integer previousState) {
            return getScenario().getProbabilityModel().getStateObservationProbability(node.getObservation(), state).doubleValue() *
                    getScenario().getProbabilityModel().getTransitionProbability(parentState != null ? parentState : 0, previousState != null ? previousState : 0, state).doubleValue() /
                    getScenario().getProbabilityModel().getStateProbability(state).doubleValue();
        }

        @Override
        protected List<TreeNode> getChildren(final TreeNode node) {
            return node.getChildren();
        }

        @Override
        protected Integer[] createArray(final int size) {
            return new Integer[size];
        }
    };

    @Override
    protected void solve() {

        states.clear();

        for (int i = 1; i <= getScenario().getProbabilityModel().getStateCount(); i++) {
            states.add(i);
        }

        final Map<TreeNode, Integer> result = alg.recursiveViterbi(getScenario().getTree());

        for (final Entry<TreeNode, Integer> e : result.entrySet()) {
            getCurrentStates().put(e.getKey(), e.getValue());
        }

        saveResult();

    }

}
