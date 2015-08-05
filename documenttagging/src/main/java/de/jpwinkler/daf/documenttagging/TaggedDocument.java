package de.jpwinkler.daf.documenttagging;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class TaggedDocument<E, T> {

    private static final Logger LOGGER = Logger.getLogger(TaggedDocument.class.getName());

    private final Map<E, T> actualTags = new HashMap<>();

    private final Map<E, T> predictedTags = new HashMap<>();

    private final Set<T> tags = new LinkedHashSet<>();

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
        if (actualTag != null && predictedTag != null) {
            actualTags.put(e, actualTag);
            predictedTags.put(e, predictedTag);

            tags.add(actualTag);
            tags.add(predictedTag);
        } else {
            LOGGER.warning("Tried to insert a result with null / partially null tags.");
        }
    }

    public Set<T> getTags() {
        return tags;
    }

}
