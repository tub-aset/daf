package de.jpwinkler.daf.documenttagging;

public interface MaxentPredicateGenerator<ELEMENT> {

    String[] getContextualPredicates(ELEMENT element);

    String getOutcome(ELEMENT element);

}
