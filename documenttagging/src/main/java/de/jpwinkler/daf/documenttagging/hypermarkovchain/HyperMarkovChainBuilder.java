package de.jpwinkler.daf.documenttagging.hypermarkovchain;

import java.util.HashMap;
import java.util.Map;

import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey2;

public class HyperMarkovChainBuilder<T> {

    // (parent, pre) -> (state -> count)
    private final Map<CompositeKey2<T, T>, Map<T, Integer>> counts;

    public HyperMarkovChainBuilder() {
        counts = new HashMap<>();
    }

    public void add(final T parent, final T previous, final T state) {
        Map<T, Integer> cPaPre = counts.get(new CompositeKey2<T, T>(parent, previous));
        if (cPaPre == null) {
            cPaPre = new HashMap<>();
            counts.put(new CompositeKey2<T, T>(parent, previous), cPaPre);
        }

        final Integer count = cPaPre.get(state);
        if (count == null) {
            cPaPre.put(state, 1);
        } else {
            cPaPre.put(state, count + 1);
        }
    }

    // private void snippet() {
    // final HyperMarkovChainBuilder<String> hyperMarkovChainBuilder = new
    // HyperMarkovChainBuilder<>();
    // for (final DocumentAccessor<E> documentAccessor : trainingData) {
    // documentAccessor.visit(documentAccessor.getDocumentRoot(), e ->
    // trainingElements.add(e));
    //
    // documentAccessor.visit(documentAccessor.getDocumentRoot(), e -> {
    // final String outcomeParent = documentAccessor.getParent(e) != null ?
    // dataGenerator.getOutcome(documentAccessor.getParent(e)) : null;
    // final String outcomePrevious = documentAccessor.getPrevious(e) != null ?
    // dataGenerator.getOutcome(documentAccessor.getPrevious(e)) : null;
    // final String outcomeE = dataGenerator.getOutcome(e);
    // if ((outcomeParent == null || !outcomeParent.isEmpty()) &&
    // (outcomePrevious == null || !outcomePrevious.isEmpty()) && (outcomeE ==
    // null || !outcomeE.isEmpty())) {
    // hyperMarkovChainBuilder.add(outcomeParent, outcomePrevious, outcomeE);
    // }
    // });
    // }
    //
    // }
}
