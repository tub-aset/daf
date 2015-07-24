package de.jpwinkler.daf.recursiveviterbi.scenario;

import java.math.BigDecimal;
import java.util.Arrays;

public class ProbabilityModel {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + stateCount;
        result = prime * result + observationCount;
        result = prime * result + Arrays.hashCode(observationProbabilities);
        result = prime * result + Arrays.hashCode(transitionProbabilities);
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
        final ProbabilityModel other = (ProbabilityModel) obj;
        if (stateCount != other.stateCount) {
            return false;
        }
        if (observationCount != other.observationCount) {
            return false;
        }
        if (!Arrays.deepEquals(observationProbabilities, other.observationProbabilities)) {
            return false;
        }
        if (!Arrays.deepEquals(transitionProbabilities, other.transitionProbabilities)) {
            return false;
        }
        return true;
    }

    // parent, predecessor, state
    private final BigDecimal[][][] transitionProbabilities;
    private final int stateCount;
    private final int observationCount;

    // state, observation
    private final BigDecimal[][] observationProbabilities;

    public ProbabilityModel(final int stateCount, final int observationCount) {
        super();
        transitionProbabilities = new BigDecimal[stateCount + 1][stateCount + 1][stateCount];
        observationProbabilities = new BigDecimal[stateCount][observationCount];
        this.stateCount = stateCount;
        this.observationCount = observationCount;
    }

    public BigDecimal getTransitionProbability(final int parent, final int predecessor, final int state) {
        return transitionProbabilities[parent][predecessor][state - 1];
    }

    public void setTransitionProbability(final int parent, final int predecessor, final int state, final BigDecimal probability) {
        transitionProbabilities[parent][predecessor][state - 1] = probability;
    }

    public void setTransitionProbabilities(final int parent, final int predecessor, final BigDecimal[] probabilities) {
        if (probabilities.length != stateCount) {
            throw new RuntimeException();
        }
        for (int i = 0; i < probabilities.length; i++) {
            transitionProbabilities[parent][predecessor][i] = probabilities[i];
        }
    }

    public BigDecimal getObservationProbability(final int state, final int observation) {
        return observationProbabilities[state - 1][observation];
    }

    public void setObservationProbability(final int state, final int observation, final BigDecimal probability) {
        observationProbabilities[state - 1][observation] = probability;
    }

    public void setObservationProbabilities(final int state, final BigDecimal[] probabilities) {
        if (probabilities.length != observationCount) {
            throw new RuntimeException();
        }

        for (int i = 0; i < probabilities.length; i++) {
            observationProbabilities[state - 1][i] = probabilities[i];
        }
    }

    public int getStateCount() {
        return stateCount;
    }

    public int getObservationCount() {
        return observationCount;
    }
}
