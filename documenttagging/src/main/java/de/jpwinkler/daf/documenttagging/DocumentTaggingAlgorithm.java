package de.jpwinkler.daf.documenttagging;

public interface DocumentTaggingAlgorithm<ELEMENT, TAG> {

    TaggedDocument<ELEMENT, TAG> tagDocument(DocumentAccessor<ELEMENT> documentAccessor);

}
