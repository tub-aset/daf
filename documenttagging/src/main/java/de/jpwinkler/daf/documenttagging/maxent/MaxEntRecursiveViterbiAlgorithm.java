package de.jpwinkler.daf.documenttagging.maxent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.DocumentTaggingAlgorithm;
import de.jpwinkler.daf.documenttagging.TaggedDocument;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.HyperMarkovChain;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.HyperMarkovChainBuilder;
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
            if (mode == MaxEntRecursiveViterbiMode.MARKOV_CHAIN) {
                if (eval == null) {
                    final String[] contextualPredicates = contextualPredicateMap.get(node);
                    eval = model.eval(contextualPredicates);
                    probabilitiyMap.put(new CompositeKey3<E, String, String>(node, parentState, previousState), eval);
                }
                final double d = markovChain.getWeight(parentState, previousState, state);
                return eval[model.getIndex(state)] * (1 - markovChainInfluence + markovChainInfluence * d);
            } else if (mode == MaxEntRecursiveViterbiMode.PREDICATES) {
                if (eval == null) {
                    final String[] contextualPredicates = contextualPredicateMap.get(node);
                    final String[] contextualPredicatesModified = Arrays.copyOf(contextualPredicates, contextualPredicates.length + 2);
                    contextualPredicatesModified[contextualPredicatesModified.length - 2] = "sparent_pod_tag=" + parentState;
                    contextualPredicatesModified[contextualPredicatesModified.length - 1] = "sprev_pod_tag=" + previousState;
                    eval = model.eval(contextualPredicatesModified);
                    probabilitiyMap.put(new CompositeKey3<E, String, String>(node, parentState, previousState), eval);
                }
                return eval[model.getIndex(state)];
            } else {
                throw new RuntimeException("Unknown mode " + mode);
            }
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

    private final HyperMarkovChain<String> markovChain;

    private MaxEntRecursiveViterbiMode mode = MaxEntRecursiveViterbiMode.PREDICATES;
    private float markovChainInfluence = 1f;

    public MaxEntRecursiveViterbiAlgorithm(final MaxEntPredicateGenerator<E> dataGenerator, final List<DocumentAccessor<E>> trainingData) throws IOException {
        this.dataGenerator = dataGenerator;
        final List<E> trainingElements = new ArrayList<>();
        final HyperMarkovChainBuilder<String> hyperMarkovChainBuilder = new HyperMarkovChainBuilder<>();
        for (final DocumentAccessor<E> documentAccessor : trainingData) {
            documentAccessor.visit(documentAccessor.getDocumentRoot(), e -> trainingElements.add(e));
            hyperMarkovChainBuilder.addAll(documentAccessor, e -> dataGenerator.getOutcome(e), t -> !t.isEmpty());
        }
        markovChain = hyperMarkovChainBuilder.build();
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
        buildResult(tags);
        return result;
    }

    private void buildResult(final Map<E, String> tags) {
        for (final Entry<E, String> e : tags.entrySet()) {
            final String actual = dataGenerator.getOutcome(e.getKey());
            final String predicted = e.getValue();
            result.putResult(e.getKey(), actual, predicted);
        }
    }

    private void buildContextualPredicateMap(final E element) {
        final String[] contextualPredicates = dataGenerator.getContextualPredicates(element);
        if (contextualPredicates != null) {
            contextualPredicateMap.put(element, contextualPredicates);
        }
        for (final E child : documentAccessor.getChildren(element)) {
            buildContextualPredicateMap(child);
        }
    }

    public float getMarkovChainInfluence() {
        return markovChainInfluence;
    }

    public void setMarkovChainInfluence(final float markovChainInfluence) {
        this.markovChainInfluence = markovChainInfluence;
    }

    public MaxEntRecursiveViterbiMode getMode() {
        return mode;
    }

    public void setMode(final MaxEntRecursiveViterbiMode mode) {
        this.mode = mode;
    }

}
