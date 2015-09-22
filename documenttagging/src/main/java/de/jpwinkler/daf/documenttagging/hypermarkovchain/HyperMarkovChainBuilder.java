package de.jpwinkler.daf.documenttagging.hypermarkovchain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.AbstractSmoothingTechnique;
import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey2;

public class HyperMarkovChainBuilder<T> {

    // (parent, pre) -> (state -> count)
    private final Map<CompositeKey2<T, T>, Map<T, Integer>> counts;

    private final Map<T, Integer> unigramCounts;

    private final Set<T> allTags;

    private final AbstractSmoothingTechnique<T> smoothingTechnique;
    private final GrowRateFunction growRateFunction;

    public HyperMarkovChainBuilder(final AbstractSmoothingTechnique<T> smoothingTechnique, final GrowRateFunction growRateFunction) {
        super();
        this.smoothingTechnique = smoothingTechnique;
        this.growRateFunction = growRateFunction;
        counts = new HashMap<>();
        unigramCounts = new HashMap<>();
        allTags = new HashSet<>();
    }

    public void add(final T parent, final T previous, final T state, final int countToAdd) {
        if (state == null) {
            return;
        }
        Map<T, Integer> cPaPre = counts.get(new CompositeKey2<T, T>(parent, previous));
        if (cPaPre == null) {
            cPaPre = new HashMap<>();
            counts.put(new CompositeKey2<T, T>(parent, previous), cPaPre);
        }

        final Integer count = cPaPre.get(state);
        if (count == null) {
            cPaPre.put(state, countToAdd);
        } else {
            cPaPre.put(state, count + countToAdd);
        }

        if (unigramCounts.containsKey(state)) {
            unigramCounts.put(state, unigramCounts.get(state) + countToAdd);
        } else {
            unigramCounts.put(state, countToAdd);
        }

        allTags.add(parent);
        allTags.add(previous);
        allTags.add(state);
    }

    public void add(final T parent, final T previous, final T state) {
        add(parent, previous, state, 1);
    }

    public <E> void addAll(final DocumentAccessor<E> documentAccessor, final Function<E, T> outcomeFunction) {
        addAll(documentAccessor, outcomeFunction, (e) -> true);
    }

    public <E> void addAll(final DocumentAccessor<E> documentAccessor, final Function<E, T> outcomeFunction, final Function<T, Boolean> isTagValidFunction) {

        documentAccessor.visit(documentAccessor.getDocumentRoot(), e -> {
            final T outcomeParent = documentAccessor.getParent(e) != null ? outcomeFunction.apply(documentAccessor.getParent(e)) : null;
            final T outcomePrevious = documentAccessor.getPrevious(e) != null ? outcomeFunction.apply(documentAccessor.getPrevious(e)) : null;
            final T outcomeE = outcomeFunction.apply(e);
            if ((outcomeParent == null || isTagValidFunction.apply(outcomeParent)) && (outcomePrevious == null || isTagValidFunction.apply(outcomePrevious)) && (outcomeE == null || isTagValidFunction.apply(outcomeE))) {
                add(outcomeParent, outcomePrevious, outcomeE);
            }
        });
    }

    public HyperMarkovChain<T> build() {
        final HyperMarkovChain<T> result = new HyperMarkovChain<>();

        for (final T t1 : allTags) {
            for (final T t2 : allTags) {
                final Map<T, Integer> map = counts.get(new CompositeKey2<>(t1, t2));
                final double sum = map != null ? map.values().stream().mapToDouble(i -> growRate(i)).sum() : 0;
                for (final T tag : allTags) {
                    if (tag != null) {
                        final int count = (map != null && map.containsKey(tag)) ? map.get(tag) : 0;
                        result.putWeight(t1, t2, tag, new Weight(growRate(count), sum));
                    }
                }
            }
        }

        smooth(result);
        result.validate();
        return result;
    }

    private void smooth(final HyperMarkovChain<T> hyperMarkovChain) {
        smoothingTechnique.init(counts, unigramCounts);
        for (final T node1 : hyperMarkovChain.getNodes()) {
            for (final T node2 : hyperMarkovChain.getNodes()) {
                final Edge<T> edge = hyperMarkovChain.getEdge(node1, node2);
                if (edge != null) {
                    smoothingTechnique.smoothEdge(hyperMarkovChain.getNodes(), edge);
                }
            }
        }
    }

    private double growRate(final Integer i) {
        switch (growRateFunction) {
        case CONSTANT_1:
            return i > 0 ? 1 : 0;
        case CONSTANT_5:
            return i > 0 ? 5 : 0;
        case LINEAR:
            return i;
        case LOG:
            return Math.log(i + 1);
        case ROOT_10:
            return Math.pow(i, 0.1);
        case ROOT_2:
            return Math.sqrt(i);
        default:
            return i;
        }
    }

}
