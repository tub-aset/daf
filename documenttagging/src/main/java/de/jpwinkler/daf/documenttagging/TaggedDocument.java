package de.jpwinkler.daf.documenttagging;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TaggedDocument<E, T> {

    private final Map<E, T> actualTags = new HashMap<>();

    private final Map<E, T> predictedTags = new HashMap<>();

    public T getActualTag(final E e) {
        return actualTags.get(e);
    }

    public T getPredictedTag(final E e) {
        return predictedTags.get(e);
    }

    public Set<E> getElements() {
        return actualTags.keySet();
    }

    public void putResult(final E e, final T actualTag, final T predictedTag) {
        actualTags.put(e, actualTag);
        predictedTags.put(e, predictedTag);
    }
}
