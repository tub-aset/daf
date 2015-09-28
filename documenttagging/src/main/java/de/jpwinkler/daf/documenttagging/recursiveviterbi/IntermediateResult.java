package de.jpwinkler.daf.documenttagging.recursiveviterbi;

import java.util.HashMap;
import java.util.Map;

/**
 * This class stores results of the recursive viterbi algorithm. Each instance
 * contains multiple child state sequences and their associated probabilities
 * for all possible states of a single node within a document tree.
 *
 * @author jonwink
 *
 * @param <S>
 *            Type of the states
 */
public class IntermediateResult<S> {

    private final Map<S, Double> sequenceLogProbabilities = new HashMap<>();
    private final Map<S, S[]> stateSequences = new HashMap<>();

    /**
     * Stores a new result in this {@link IntermediateResult} instance.
     *
     * @param parentState
     *            the state for which a new child state sequence shall be added.
     * @param sequenceLogProbability
     *            log probability of the child sequence.
     * @param stateSequence
     *            the child sequence.
     */
    public void putResult(final S parentState, final double sequenceLogProbability, final S[] stateSequence) {
        sequenceLogProbabilities.put(parentState, sequenceLogProbability);
        stateSequences.put(parentState, stateSequence);
    }

    /**
     * Returns the log probability of the child state sequence for a given
     * state.
     *
     * @param parentState
     * @return
     */
    public double getSequenceLogProbability(final S parentState) {
        if (sequenceLogProbabilities.containsKey(parentState)) {
            return sequenceLogProbabilities.get(parentState);
        } else {
            return 0;
        }
    }

    /**
     * Returns the most probable child state sequence for a given state.
     *
     * @param parentState
     * @return
     */
    public S[] getStateSequence(final S parentState) {
        return stateSequences.get(parentState);
    }

}
