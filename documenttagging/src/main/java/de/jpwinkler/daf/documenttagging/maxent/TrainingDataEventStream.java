package de.jpwinkler.daf.documenttagging.maxent;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import opennlp.model.Event;
import opennlp.model.EventStream;

public class TrainingDataEventStream<E> implements EventStream {

    private final Iterator<E> i;
    private final MaxEntPredicateGenerator<E> dataGenerator;
    private E next = null;

    public TrainingDataEventStream(final MaxEntPredicateGenerator<E> dataGenerator, final Iterable<E> trainingData) {
        super();
        this.i = trainingData.iterator();
        this.dataGenerator = dataGenerator;
        setNext();
    }

    @Override
    public Event next() throws IOException {
        if (next == null) {
            throw new NoSuchElementException();
        }
        final E e = next;
        setNext();
        return new Event(dataGenerator.getOutcome(e), dataGenerator.getContextualPredicates(e));
    }

    private void setNext() {
        next = null;
        while (i.hasNext()) {
            final E candidate = i.next();
            if (dataGenerator.getContextualPredicates(candidate) != null && dataGenerator.getOutcome(candidate) != null && !dataGenerator.getOutcome(candidate).isEmpty()) {
                next = candidate;
                return;
            }
        }
    }

    @Override
    public boolean hasNext() throws IOException {
        return next != null;
    }

}
