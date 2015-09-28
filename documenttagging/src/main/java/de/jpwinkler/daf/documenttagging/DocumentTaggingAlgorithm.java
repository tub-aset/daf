package de.jpwinkler.daf.documenttagging;

/**
 * Interface for all document tagging algorithms.
 *
 * @author jonwink
 *
 * @param <E>
 *            Type of the elements of the document.
 * @param <T>
 *            Type of the tags.
 */
public interface DocumentTaggingAlgorithm<E, T> {

    /**
     * Runs the algorithm and tags each node of the document. Returns the tagged
     * document.
     *
     * @param documentAccessor
     * @return
     */
    TaggedDocument<E, T> tagDocument(DocumentAccessor<E> documentAccessor);

}
