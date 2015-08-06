package de.jpwinkler.daf.documenttagging.maxent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.DocumentTaggingAlgorithm;
import de.jpwinkler.daf.documenttagging.TaggedDocument;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class SimpleMaxEntAlgorithm<E> implements DocumentTaggingAlgorithm<E, String> {

    private final GISModel model;
    private final MaxEntPredicateGenerator<E> dataGenerator;

    public SimpleMaxEntAlgorithm(final MaxEntPredicateGenerator<E> dataGenerator, final List<DocumentAccessor<E>> trainingData) throws IOException {
        final List<E> trainingElements = new ArrayList<>();
        for (final DocumentAccessor<E> documentAccessor : trainingData) {
            documentAccessor.visit(documentAccessor.getDocumentRoot(), e -> trainingElements.add(e));
        }
        model = GIS.trainModel(new TrainingDataEventStream<>(dataGenerator, trainingElements));

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
            final String actual = dataGenerator.getOutcome(element);
            final String predicted = model.getBestOutcome(eval);
            if (actual != null && predicted != null && !actual.isEmpty() && !predicted.isEmpty()) {
                result.putResult(element, actual, predicted);
            }
        }
        for (final E e : documentAccessor.getChildren(element)) {
            predictTags(documentAccessor, e, result);
        }
    }

}
