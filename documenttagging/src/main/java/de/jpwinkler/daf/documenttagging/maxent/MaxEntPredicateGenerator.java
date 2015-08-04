package de.jpwinkler.daf.documenttagging.maxent;

public interface MaxEntPredicateGenerator<E> {

    String[] getContextualPredicates(E element);

    String getOutcome(E element);

}
