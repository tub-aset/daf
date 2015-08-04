package de.jpwinkler.daf.documenttagging;

public interface DocumentTaggingAlgorithm<E, T> {

    TaggedDocument<E, T> tagDocument(DocumentAccessor<E> documentAccessor);

}
