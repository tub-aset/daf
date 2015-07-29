package de.jpwinkler.daf.documenttagging;

import java.io.IOException;
import java.util.Iterator;

import opennlp.model.Event;
import opennlp.model.EventStream;

public class MaxEntEventStream<E> implements EventStream {

    private final Iterator<E> i;
    private final MaxentPredicateGenerator<E> dataGenerator;

    public MaxEntEventStream(final Iterable<E> iterable, final MaxentPredicateGenerator<E> dataGenerator) {
        super();
        this.i = iterable.iterator();
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
