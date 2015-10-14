package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario;

import java.math.BigDecimal;
import java.util.Arrays;

public class ProbabilityModel {



    // parent, predecessor, state
    private final BigDecimal[][][] transitionProbabilities;
    private final int stateCount;
    private final int observationCount;

    // observation, state
    private final BigDecimal[][] stateObservationProbabilities;

    private final BigDecimal[] stateProbabilities;

    public ProbabilityModel(final int stateCount, final int observationCount) {
        super();
        transitionProbabilities = new BigDecimal[stateCount + 1][stateCount + 1][stateCount];
        stateObservationProbabilities = new BigDecimal[observationCount][stateCount];
        stateProbabilities = new BigDecimal[stateCount];
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

    public BigDecimal getStateObservationProbability(final int observation, final int state) {
        return stateObservationProbabilities[observation][state - 1];
    }

    public void setStateObservationProbability(final int observation, final int state, final BigDecimal probability) {
        stateObservationProbabilities[observation][state - 1] = probability;
    }

    public void setStateObservationProbabilities(final int observation, final BigDecimal[] probabilities) {
        if (probabilities.length != stateCount) {
            throw new RuntimeException();
        }

        for (int i = 0; i < probabilities.length; i++) {
            stateObservationProbabilities[observation][i] = probabilities[i];
        }
    }

    public BigDecimal getStateProbability(final int state) {
        return stateProbabilities[state - 1];
    }

    public void setStateProbability(final int state, final BigDecimal probability) {
        stateProbabilities[state - 1] = probability;
    }

    public void setStateProbabilities(final BigDecimal[] probabilities) {
        if (!(probabilities.length == stateCount)) {
            throw new RuntimeException();
        }

        for (int i = 0; i < probabilities.length; i++) {
            stateProbabilities[i] = probabilities[i];
        }
    }

    public int getStateCount() {
        return stateCount;
    }

    public int getObservationCount() {
        return observationCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + observationCount;
        result = prime * result + stateCount;
        result = prime * result + Arrays.deepHashCode(stateObservationProbabilities);
        result = prime * result + Arrays.hashCode(stateProbabilities);
        result = prime * result + Arrays.deepHashCode(transitionProbabilities);
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
        if (observationCount != other.observationCount) {
            return false;
        }
        if (stateCount != other.stateCount) {
            return false;
        }
        if (!Arrays.deepEquals(stateObservationProbabilities, other.stateObservationProbabilities)) {
            return false;
        }
        if (!Arrays.equals(stateProbabilities, other.stateProbabilities)) {
            return false;
        }
        if (!Arrays.deepEquals(transitionProbabilities, other.transitionProbabilities)) {
            return false;
        }
        return true;
    }
}
