package de.jpwinkler.daf.documenttagging;

public interface DocumentTaggingAlgorithm<S, T extends DocumentElementTag> {

    TaggedDocument<S, T> tagDocument(DocumentAccessor<S> documentAccessor);

}
