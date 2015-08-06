package de.jpwinkler.daf.documenttagging.maxent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.DocumentTaggingAlgorithm;
import de.jpwinkler.daf.documenttagging.TaggedDocument;
import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey3;
import de.jpwinkler.daf.documenttagging.recursiveviterbi.AbstractRecursiveViterbiAlgorithm;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class MaxEntRecursiveViterbiAlgorithm<E> implements DocumentTaggingAlgorithm<E, String> {

    private final AbstractRecursiveViterbiAlgorithm<E, String> recursiveViterbi = new AbstractRecursiveViterbiAlgorithm<E, String>() {

        @Override
        protected List<String> getStates() {
            return states;
        }

        @Override
        protected List<E> getChildren(final E node) {
            return documentAccessor.getChildren(node);
        }

        @Override
        protected double getProbability(final E node, final String state, final String parentState, final String previousState) {
            if (!contextualPredicateMap.containsKey(node)) {
                return 1.0;
            }
            double[] eval = probabilitiyMap.get(new CompositeKey3<E, String, String>(node, parentState, previousState));
            if (eval == null) {
                final String[] contextualPredicates = contextualPredicateMap.get(node);
                contextualPredicates[contextualPredicates.length - 2] = "parent_pod_tags=" + parentState;
                contextualPredicates[contextualPredicates.length - 1] = "prev_pod_tags=" + previousState;
                eval = model.eval(contextualPredicates);
                probabilitiyMap.put(new CompositeKey3<E, String, String>(node, parentState, previousState), eval);
            }
            return eval[model.getIndex(state)];
        }

        @Override
        protected String[] createArray(final int size) {
            return new String[size];
        }
    };

    private final GISModel model;
    private final List<String> states;
    private DocumentAccessor<E> documentAccessor;
    private final MaxEntPredicateGenerator<E> dataGenerator;
    private Map<CompositeKey3<E, String, String>, double[]> probabilitiyMap;
    private Map<E, String[]> contextualPredicateMap;
    private TaggedDocument<E, String> result;

    public MaxEntRecursiveViterbiAlgorithm(final MaxEntPredicateGenerator<E> dataGenerator, final List<DocumentAccessor<E>> trainingData) throws IOException {
        this.dataGenerator = dataGenerator;
        final List<E> trainingElements = new ArrayList<>();
        for (final DocumentAccessor<E> documentAccessor : trainingData) {
            documentAccessor.visit(documentAccessor.getDocumentRoot(), e -> trainingElements.add(e));
        }
        this.model = GIS.trainModel(new TrainingDataEventStream<>(dataGenerator, trainingElements));
        states = Arrays.asList((String[]) model.getDataStructures()[2]);
    }

    @Override
    public TaggedDocument<E, String> tagDocument(final DocumentAccessor<E> documentAccessor) {
        this.documentAccessor = documentAccessor;
        contextualPredicateMap = new HashMap<>();
        probabilitiyMap = new HashMap<>();
        buildContextualPredicateMap(documentAccessor.getDocumentRoot());
        final Map<E, String> tags = recursiveViterbi.recursiveViterbi(documentAccessor.getDocumentRoot());
        result = new TaggedDocument<>();
        buildResult(tags, documentAccessor.getDocumentRoot());
        return result;
    }

    private void buildResult(final Map<E, String> tags, final E element) {
        final String actual = dataGenerator.getOutcome(element);
        final String predicted = tags.get(element);
        if (actual != null && predicted != null && !actual.isEmpty() && !predicted.isEmpty()) {
            result.putResult(element, actual, predicted);
        }
        for (final E child : documentAccessor.getChildren(element)) {
            buildResult(tags, child);
        }
    }

    private void buildContextualPredicateMap(final E element) {
        String[] contextualPredicates = dataGenerator.getContextualPredicates(element);
        if (contextualPredicates != null) {
            contextualPredicates = Arrays.asList(contextualPredicates).stream().filter(s -> !s.startsWith("parent_pod_tags=") && !s.startsWith("prev_pod_tags=")).toArray(i -> new String[i + 2]);
            contextualPredicateMap.put(element, contextualPredicates);
        }
        for (final E child : documentAccessor.getChildren(element)) {
            buildContextualPredicateMap(child);
        }
    }

}
