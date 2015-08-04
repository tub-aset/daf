package de.jpwinkler.daf.documenttagging.maxent;

import java.io.IOException;
import java.util.Iterator;

import opennlp.model.Event;
import opennlp.model.EventStream;

public class MaxEntEventStream<E> implements EventStream {

    private final Iterator<E> i;
    private final MaxEntPredicateGenerator<E> dataGenerator;

    public MaxEntEventStream(final MaxEntPredicateGenerator<E> dataGenerator, final Iterator<E> trainingDataIterator) {
        super();
        this.i = trainingDataIterator;
        this.dataGenerator = dataGenerator;
    }

    @Override
    public Event next() throws IOException {
        final E e = i.next();
        return new Event(dataGenerator.getOutcome(e), dataGenerator.getContextualPredicates(e));
    }

    @Override
    public boolean hasNext() throws IOException {
        return i.hasNext();
    }

}
