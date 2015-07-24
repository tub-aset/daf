package de.jpwinkler.daf.recursiveviterbi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRecursiveViterbiAlgorithm<T, S> {

    private Map<T, AbstractIntermediateResult<S>> results;
    private Map<T, S> result;
    private int nodeCount;
    private int processedNodes;
    private ProgressMonitor progressMonitor;

    protected abstract List<S> getStates();

    protected abstract List<T> getChildren(T node);

    protected abstract double getProbability(T node, S state, S parentState, S previousState);

    protected abstract S[] createArray(int size);

    public Map<T, S> recursiveViterbi(final T tree) {

        prepare(tree);

        nodeCount = countNodes(tree);
        processedNodes = 0;

        results = new HashMap<>();
        result = new HashMap<>();

        final AbstractIntermediateResult<S> solve = solve(tree);

        double max = Double.NEGATIVE_INFINITY;
        S firstState = null;
        for (final S state : getStates()) {
            final double p = solve.getSequenceLogProbability(state) + Math.log(getProbability(tree, state, null, null));
            if (p > max) {
                max = p;
                firstState = state;
            }
        }

        tagTree(tree, firstState);

        return result;
    }

    private int countNodes(final T tree) {
        return getChildren(tree).stream().mapToInt(t -> countNodes(t)).sum() + 1;
    }

    protected void prepare(final T tree) {
    }

    private void tagTree(final T treeNode, final S state) {
        result.put(treeNode, state);
        if (!getChildren(treeNode).isEmpty()) {
            final AbstractIntermediateResult<S> result = results.get(treeNode);
            final S[] childSequence = result.getStateSequence(state);
            for (int i = 0; i < getChildren(treeNode).size(); i++) {
                final T child = getChildren(treeNode).get(i);
                tagTree(child, childSequence[i]);
            }
        }
    }

    private AbstractIntermediateResult<S> solve(final T node) {

        if (results.containsKey(node)) {
            return results.get(node);
        }

        final AbstractIntermediateResult<S> result = new AbstractIntermediateResult<>();

        final int stateCount = getStates().size();
        final int childCount = getChildren(node).size();

        for (final S parentState : getStates()) {

            if (!getChildren(node).isEmpty()) {
                final double[][] trellis = new double[childCount][stateCount];
                final int[][] backPointers = new int[childCount][stateCount];

                AbstractIntermediateResult<S> r = solve(getChildren(node).get(0));
                int iState = 0;
                for (final S state : getStates()) {
                    trellis[0][iState] = Math.log(getProbability(getChildren(node).get(0), state, parentState, null)) + r.getSequenceLogProbability(state);
                    backPointers[0][iState] = 0;
                    iState++;
                }

                for (int iChild = 1; iChild < getChildren(node).size(); iChild++) {
                    final T child = getChildren(node).get(iChild);
                    r = solve(child);
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

                final S[] stateSequence = createArray(childCount);
                double sequenceLogProbability = Double.NEGATIVE_INFINITY;

                for (int lastState = 0; lastState < stateCount; lastState++) {
                    if (trellis[childCount - 1][lastState] > sequenceLogProbability) {
                        sequenceLogProbability = trellis[childCount - 1][lastState];
                        stateSequence[childCount - 1] = getStates().get(lastState);
                    }
                }

                for (int iTreeNode = childCount - 1; iTreeNode > 0; iTreeNode--) {
                    stateSequence[iTreeNode - 1] = getStates().get(backPointers[iTreeNode][getStates().indexOf(stateSequence[iTreeNode])]);
                }

                result.putResult(parentState, sequenceLogProbability, stateSequence);

            } else {
                result.putResult(parentState, 0, createArray(0));
            }

        }

        if (childCount > 0) {
            processedNodes += childCount;
            if (progressMonitor != null) {
                progressMonitor.onProgress(processedNodes, nodeCount);
            }
        }

        results.put(node, result);

        return result;

    }

    public void setProgressMonitor(final ProgressMonitor progressMonitor) {
        this.progressMonitor = progressMonitor;
    }

}
