package de.jpwinkler.daf.documenttagging.maxent;

public interface MaxEntPredicateGenerator<E> {

    String[] getContextualPredicates(E element, boolean isTraining);

    String getOutcome(E element);

}
