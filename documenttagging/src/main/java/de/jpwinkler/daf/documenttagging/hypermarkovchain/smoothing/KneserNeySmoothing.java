package de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.jpwinkler.daf.documenttagging.hypermarkovchain.Edge;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.Weight;

public class KneserNeySmoothing<T> extends AbstractSmoothingTechnique<T> {

    private final double d;

    public KneserNeySmoothing(final double d) {
        super();
        this.d = d;
    }

    @Override
    public void smoothEdge(final Set<T> nodes, final Edge<T> edge) {

        final Map<T, Double> discountedWeights = new HashMap<>();

        final long countOfThinsForWhichAContinuationExists = counts.entrySet().stream().mapToLong(e -> e.getValue().entrySet().stream().filter(e2 -> e2.getValue() > 0).count()).sum();
        for (final T node : nodes) {
            final Weight weight = edge.getWeight(node);

            final double lambda = d / weight.getTotalCount() * edge.getWeightedTargetNodes().values().stream().filter(w -> w.getCount() > 0).count();
            final long countOfThingsForWhichNodeIsAContinuation = counts.entrySet().stream().filter(e -> e.getValue().containsKey(node) && e.getValue().get(node) > 0).count();
            final double p = Math.max(weight.getCount() - d, 0) / weight.getTotalCount() + lambda * countOfThingsForWhichNodeIsAContinuation / countOfThinsForWhichAContinuationExists;
            discountedWeights.put(node, p);
        }

        for (final T node : nodes) {
            edge.getWeight(node).setCount(discountedWeights.get(node));
            edge.getWeight(node).setTotalCount(1);
        }

    }

    @Override
    public String toString() {
        return "kneser ney smoothing(d=" + d + ")";
    }
}
