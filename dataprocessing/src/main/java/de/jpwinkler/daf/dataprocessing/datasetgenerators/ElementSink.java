package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.IOException;

public interface ElementSink<E> {

    void add(E element);

    void finish() throws IOException;

}
