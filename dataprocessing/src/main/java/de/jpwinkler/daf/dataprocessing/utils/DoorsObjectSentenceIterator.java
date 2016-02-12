package de.jpwinkler.daf.dataprocessing.utils;

import java.util.Iterator;
import java.util.function.Supplier;

import org.deeplearning4j.text.sentenceiterator.BaseSentenceIterator;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class DoorsObjectSentenceIterator extends BaseSentenceIterator {

    private Iterator<DoorsObject> iterator;
    private final Supplier<Iterator<DoorsObject>> iteratorSupplier;

    public DoorsObjectSentenceIterator(final Supplier<Iterator<DoorsObject>> iteratorSupplier) {
        super();
        this.iteratorSupplier = iteratorSupplier;
        reset();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public String nextSentence() {
        return iterator.next().getText();
    }

    @Override
    public void reset() {
        iterator = iteratorSupplier.get();
    }

}
