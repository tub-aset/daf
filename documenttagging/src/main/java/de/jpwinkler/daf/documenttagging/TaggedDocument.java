package de.jpwinkler.daf.documenttagging;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class is used to store the tags assigned to the elements of a document
 * by a document tagging algorithm.
 *
 * @author jonwink
 *
 * @param <E>
 *            The type of the elements of a document.
 * @param <T>
 *            The type of the tags.
 */
public class TaggedDocument<E, T> {

    private static final Logger LOGGER = Logger.getLogger(TaggedDocument.class.getName());

    private final Map<E, T> actualTags = new HashMap<>();

    private final Map<E, T> predictedTags = new HashMap<>();

    private final Set<T> tags = new LinkedHashSet<>();

    /**
     * Returns the actual tag of the element as provided by training material.
     * Might be null, if that information is not available.
     *
     * @param e
     * @return
     */
    public T getActualTag(final E e) {
        return actualTags.get(e);
    }

    /**
     * Returns the tag predicted by a document tagging algorithm of the element.
     *
     * @param e
     * @return
     */
    public T getPredictedTag(final E e) {
        return predictedTags.get(e);
    }

    /**
     * Returns all elements for which a tag was assigned.
     *
     * @return
     */
    public Set<E> getElements() {
        return predictedTags.keySet();
    }

    /**
     * Assigns tags to the given element.
     *
     * @param e
     *            The element.
     * @param actualTag
     *            The actual tag of the element as provided by training
     *            material. May be null.
     * @param predictedTag
     *            The tag as predicted by a document tagging algorithm. Must not
     *            be null.
     */
    public void putResult(final E e, final T actualTag, final T predictedTag) {
        if (actualTag != null) {
            actualTags.put(e, actualTag);
            tags.add(actualTag);
        }

        if (predictedTag != null) {
            predictedTags.put(e, predictedTag);
            tags.add(predictedTag);
        } else {
            LOGGER.severe("Tried to store null as the predicted tag for element " + e);
        }

    }

    /**
     * Returns all tags used within this document.
     *
     * @return
     */
    public Set<T> getTags() {
        return tags;
    }

}
