package de.jpwinkler.daf.documenttagging.recursiveviterbi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Abstract implementation of the recursive viterbi algorithm. This class
 * contains the core logic of the algorithm only. Extending classes must provide
 * a learning mechanism and an operation to calculate actual probabilities.
 * </p>
 *
 * <p>
 * For any given tree and any given set of tags, recursive viterbi determines
 * the most probable mapping from tree nodes to tags.
 * </p>
 *
 * <p>
 * This class internally uses log probabilities to deal with very small
 * probabilities that would otherwise go beyond the smallest possible double
 * value.
 * </p>
 *
 * @author JONWINK
 *
 * @param <T>
 *            Type of the nodes in the tree.
 * @param <S>
 *            Type of the states.
 */
public abstract class AbstractRecursiveViterbiAlgorithm<T, S> {

    private Map<T, AbstractIntermediateResult<S>> results;
    private Map<T, S> result;
    private int nodeCount;
    private int processedNodes;
    private ProgressMonitor progressMonitor;

    /**
     * Returns a list of all states.
     *
     * @return a list containing all states.
     */
    protected abstract List<S> getStates();

    /**
     * Returns the child nodes of a tree node.
     *
     * @param node
     *            The node whose children shall be returned.
     * @return a list containing all children of node <code>node</code>. The
     *         list may be empty if there are no child nodes. Must not be null.
     */
    protected abstract List<T> getChildren(T node);

    /**
     * Returns the probability that the node <code>node</code> has the state
     * <code>state</code>, given that the parent of <code>node</code> has state
     * <code>parentState<code>node</code> and the predecessor of
     * <code>node</code> has state <code>previousState</code>.
     *
     * @param node
     * @param state
     * @param parentState
     * @param previousState
     * @return
     */
    protected abstract double getProbability(T node, S state, S parentState, S previousState);

    /**
     * Implementing classes must return an empty array of size <code>size</code>
     *
     * @param size
     * @return an array of length <code>size</code>
     */
    protected abstract S[] createArray(int size);

    /**
     * Runs the recursive viterbi algorithm on tree <code>tree</code>.
     *
     * @param tree
     *            The tree to be tagged.
     * @return A map containing the most probable state for each node in the
     *         tree.
     */
    public Map<T, S> recursiveViterbi(final T tree) {

        nodeCount = countNodes(tree);
        processedNodes = 0;

        results = new HashMap<>();
        result = new HashMap<>();

        // final AbstractIntermediateResult<S> solve = solve(tree, true);
        solve(tree, true);

        // double max = Double.NEGATIVE_INFINITY;
        // S firstState = null;
        // for (final S state : getStates()) {
        // final double p = solve.getSequenceLogProbability(state) +
        // Math.log(getProbability(tree, state, null, null));
        // if (p > max) {
        // max = p;
        // firstState = state;
        // }
        // }

        tagTree(tree, null);

        return result;
    }

    private int countNodes(final T tree) {
        return getChildren(tree).stream().mapToInt(t -> countNodes(t)).sum() + 1;
    }

    private void tagTree(final T treeNode, final S state) {
        if (state != null) {
            result.put(treeNode, state);
        }
        if (!getChildren(treeNode).isEmpty()) {
            final AbstractIntermediateResult<S> result = results.get(treeNode);
            final S[] childSequence = result.getStateSequence(state);
            for (int i = 0; i < getChildren(treeNode).size(); i++) {
                final T child = getChildren(treeNode).get(i);
                tagTree(child, childSequence[i]);
            }
        }
    }

    private AbstractIntermediateResult<S> solve(final T node, final boolean isParentState) {

        if (results.containsKey(node)) {
            // This is what makes recursive viterbi feasible. We assume that for
            // any two independent executions of solve on two nodes the result
            // will be the same if the input nodes were the same.
            return results.get(node);
        }

        final AbstractIntermediateResult<S> result = new AbstractIntermediateResult<>();

        final List<S> states = isParentState ? Arrays.asList((S) null) : getStates();

        for (final S parentState : states) {

            if (!getChildren(node).isEmpty()) {
                final double[][] trellis = new double[getChildren(node).size()][getStates().size()];
                final int[][] backPointers = new int[getChildren(node).size()][getStates().size()];

                AbstractIntermediateResult<S> r = solve(getChildren(node).get(0), false);
                int iState = 0;
                for (final S state : getStates()) {
                    trellis[0][iState] = Math.log(getProbability(getChildren(node).get(0), state, parentState, null)) + r.getSequenceLogProbability(state);
                    backPointers[0][iState] = 0;
                    iState++;
                }

                for (int iChild = 1; iChild < getChildren(node).size(); iChild++) {
                    final T child = getChildren(node).get(iChild);
                    r = solve(child, false);
                    iState = 0;
                    for (final S state : getStates()) {
                        trellis[iChild][iState] = Double.NEGATIVE_INFINITY;
                        int iPreviousState = 0;
                        for (final S previousState : getStates()) {
                            final double p = trellis[iChild - 1][iPreviousState] + Math.log(getProbability(child, state, parentState, previousState)) + r.getSequenceLogProbability(state);
                            if (p > trellis[iChild][iState]) {
                                trellis[iChild][iState] = p;
                                backPointers[iChild][iState] = iPreviousState;
                            }
                            iPreviousState++;
                        }
                        iState++;
                    }

                }

                final S[] stateSequence = createArray(getChildren(node).size());
                double sequenceLogProbability = Double.NEGATIVE_INFINITY;

                for (int lastState = 0; lastState < getStates().size(); lastState++) {
                    if (trellis[getChildren(node).size() - 1][lastState] > sequenceLogProbability) {
                        sequenceLogProbability = trellis[getChildren(node).size() - 1][lastState];
                        stateSequence[getChildren(node).size() - 1] = getStates().get(lastState);
                    }
                }

                if (sequenceLogProbability > Double.NEGATIVE_INFINITY) {
                    for (int iTreeNode = getChildren(node).size() - 1; iTreeNode > 0; iTreeNode--) {
                        stateSequence[iTreeNode - 1] = getStates().get(backPointers[iTreeNode][getStates().indexOf(stateSequence[iTreeNode])]);
                    }
                    result.putResult(parentState, sequenceLogProbability, stateSequence);
                } else {
                    result.putResult(parentState, sequenceLogProbability, null);
                }

            } else {
                result.putResult(parentState, 0, createArray(0));
            }

        }

        if (getChildren(node).size() > 0) {
            processedNodes += getChildren(node).size();
            if (progressMonitor != null) {
                progressMonitor.onProgress(processedNodes, nodeCount);
            }
        }

        results.put(node, result);

        return result;

    }

    /**
     * Sets a progress monitor.
     *
     * @param progressMonitor
     *            A progress monitor. Submit null to remove previously set
     *            progress monitor.
     */
    public void setProgressMonitor(final ProgressMonitor progressMonitor) {
        this.progressMonitor = progressMonitor;
    }

}
