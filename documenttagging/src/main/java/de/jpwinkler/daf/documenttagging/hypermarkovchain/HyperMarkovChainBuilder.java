package de.jpwinkler.daf.documenttagging.hypermarkovchain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Logger;

import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey2;

public class HyperMarkovChainBuilder<T> {

    private static final Logger LOGGER = Logger.getLogger(HyperMarkovChainBuilder.class.getName());

    // (parent, pre) -> (state -> count)
    private final Map<CompositeKey2<T, T>, Map<T, Integer>> counts;

    private final Set<T> allTags;

    private SmoothingTechnique smoothingTechnique = SmoothingTechnique.LAPLACE;

    public HyperMarkovChainBuilder() {
        counts = new HashMap<>();
        allTags = new HashSet<>();
    }

    public SmoothingTechnique getSmoothingTechnique() {
        return smoothingTechnique;
    }

    public void setSmoothingTechnique(final SmoothingTechnique smoothingTechnique) {
        this.smoothingTechnique = smoothingTechnique;
    }

    public void add(final T parent, final T previous, final T state, final int countToAdd) {
        if (state == null) {
            LOGGER.warning("cannot add an edge with null target to hmc");
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
                final int sum = map != null ? map.values().stream().reduce(0, (i1, i2) -> i1 + i2) : 0;

                for (final T tag : allTags) {

                    final int count = (map != null && map.containsKey(tag)) ? map.get(tag) : 0;
                    if (smoothingTechnique == SmoothingTechnique.LAPLACE) {
                        result.putWeight(t1, t2, tag, (double) (count + 1) / (sum + allTags.size()));
                    } else if (smoothingTechnique == SmoothingTechnique.NONE) {
                        if (sum > 0) {
                            result.putWeight(t1, t2, tag, (double) (count) / (sum));
                        }
                    }
                }
            }
        }

        result.validate();
        return result;
    }
}
