package de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing;

import java.util.Map;
import java.util.Set;

import de.jpwinkler.daf.documenttagging.hypermarkovchain.Edge;
import de.jpwinkler.daf.documenttagging.util.CompositeKey2;

public abstract class AbstractSmoothingTechnique<T> {

    protected Map<T, Integer> unigramCounts;
    protected Map<CompositeKey2<T, T>, Map<T, Integer>> counts;

    public abstract void smoothEdge(Set<T> nodes, Edge<T> edge);

    protected double getUnigramProbability(final T node) {
        if (unigramCounts.containsKey(node)) {
            final int sum = unigramCounts.values().stream().reduce(0, (i1, i2) -> i1 + i2);
            return (double) unigramCounts.get(node) / (double) sum;
        } else {
            return 0;
        }
    }

    public void init(final Map<CompositeKey2<T, T>, Map<T, Integer>> counts, final Map<T, Integer> unigramCounts) {
        this.counts = counts;
        this.unigramCounts = unigramCounts;
    }
}
