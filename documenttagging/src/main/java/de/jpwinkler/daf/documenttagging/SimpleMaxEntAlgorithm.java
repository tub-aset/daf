package de.jpwinkler.daf.documenttagging;

import java.io.IOException;
import java.util.Iterator;

import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class SimpleMaxEntAlgorithm<E> implements DocumentTaggingAlgorithm<E, String> {

    private final GISModel model;
    private final MaxentPredicateGenerator<E> dataGenerator;

    public SimpleMaxEntAlgorithm(final MaxentPredicateGenerator<E> dataGenerator, final Iterable<E> trainingData) throws IOException {
        final Iterator<E> i = trainingData.iterator();
        model = GIS.trainModel(new MaxEntEventStream<>(trainingData, dataGenerator));
        this.dataGenerator = dataGenerator;
    }

    @Override
    public TaggedDocument<E, String> tagDocument(final DocumentAccessor<E> documentAccessor) {
        final TaggedDocument<E, String> result = new TaggedDocument<>();

        final E root = documentAccessor.getDocumentRoot();

        predictTags(documentAccessor, root, result);

        return result;
    }

    private void predictTags(final DocumentAccessor<E> documentAccessor, final E element, final TaggedDocument<E, String> result) {
        final String[] contextualPredicates = dataGenerator.getContextualPredicates(element);
        if (contextualPredicates != null) {
            final double[] eval = model.eval(contextualPredicates);
            result.putResult(element, dataGenerator.getOutcome(element), model.getBestOutcome(eval));
        }
        for (final E e : documentAccessor.getChildren(element)) {
            predictTags(documentAccessor, e, result);
        }
    }

}
