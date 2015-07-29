package de.jpwinkler.daf.documenttagging;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.documenttagging.maxent.util.TriMap;
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
            double[] eval = probabilitiyMap.get(node, parentState, previousState);
            if (eval == null) {
                final String[] contextualPredicates = contextualPredicateMap.get(node);
                contextualPredicates[contextualPredicates.length - 2] = "parent_pod_tags=" + parentState;
                contextualPredicates[contextualPredicates.length - 1] = "prev_pod_tags=" + previousState;
                eval = model.eval(contextualPredicates);
                probabilitiyMap.put(node, parentState, previousState, eval);
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
    private final MaxentPredicateGenerator<E> dataGenerator;
    private TriMap<E, String, String, double[]> probabilitiyMap;
    private Map<E, String[]> contextualPredicateMap;
    private TaggedDocument<E, String> result;

    public MaxEntRecursiveViterbiAlgorithm(final MaxentPredicateGenerator<E> dataGenerator, final Iterable<E> trainingData) throws IOException {
        recursiveViterbi.setProgressMonitor((current, max) -> System.out.println(current + "/" + max));
        this.dataGenerator = dataGenerator;
        this.model = GIS.trainModel(new MaxEntEventStream<>(trainingData, dataGenerator));
        states = Arrays.asList((String[]) model.getDataStructures()[2]);
    }

    @Override
    public TaggedDocument<E, String> tagDocument(final DocumentAccessor<E> documentAccessor) {
        this.documentAccessor = documentAccessor;
        contextualPredicateMap = new HashMap<>();
        probabilitiyMap = new TriMap<>();
        buildContextualPredicateMap(documentAccessor.getDocumentRoot());
        final Map<E, String> tags = recursiveViterbi.recursiveViterbi(documentAccessor.getDocumentRoot());
        result = new TaggedDocument<>();
        buildResult(tags, documentAccessor.getDocumentRoot());
        return result;
    }

    private void buildResult(final Map<E, String> tags, final E element) {
        result.putResult(element, dataGenerator.getOutcome(element), tags.get(element));
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
