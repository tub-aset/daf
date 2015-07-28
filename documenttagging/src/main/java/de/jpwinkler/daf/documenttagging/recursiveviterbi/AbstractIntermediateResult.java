package de.jpwinkler.daf.documenttagging.recursiveviterbi;

import java.util.HashMap;
import java.util.Map;

public class AbstractIntermediateResult<S> {

    private final Map<S, Double> sequenceLogProbabilities = new HashMap<>();
    private final Map<S, S[]> stateSequences = new HashMap<>();

    public void putResult(final S parentState, final double sequenceLogProbability, final S[] stateSequence) {
        sequenceLogProbabilities.put(parentState, sequenceLogProbability);
        stateSequences.put(parentState, stateSequence);
    }

    public double getSequenceLogProbability(final S parentState) {
        if (sequenceLogProbabilities.containsKey(parentState)) {
            return sequenceLogProbabilities.get(parentState);
        } else {
            return 0;
        }
    }

    public S[] getStateSequence(final S parentState) {
        return stateSequences.get(parentState);
    }

}
