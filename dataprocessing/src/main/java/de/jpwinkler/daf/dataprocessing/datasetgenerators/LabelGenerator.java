package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.util.List;

public abstract class LabelGenerator<E> {

    public abstract boolean hasLabel(final E object);

    public abstract List<String> getLabels(final E object);

}
