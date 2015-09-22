package de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing;

import java.util.Set;

import de.jpwinkler.daf.documenttagging.hypermarkovchain.Edge;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.Weight;

public class AbsoluteDiscountingSmoothing<T> extends AbstractSmoothingTechnique<T> {

    private final double d;

    public AbsoluteDiscountingSmoothing(final double d) {
        super();
        this.d = d;
    }

    @Override
    public void smoothEdge(final Set<T> nodes, final Edge<T> edge) {

        final Edge<T> e = edge;

        final long sumOfThingsWithBigramCountGreaterZero = e.getWeightedTargetNodes().values().stream().filter(w -> w.getCount() > 0).count();

        for (final T node : nodes) {
            if (node != null) {
                final Weight weight = e.getWeight(node);
                final double lambda = d / weight.getTotalCount() * sumOfThingsWithBigramCountGreaterZero;
                final double p = Math.max(weight.getCount() - d, 0) / weight.getTotalCount() + (lambda) * getUnigramProbability(node);
                weight.setCount(p);
                weight.setTotalCount(1);
            }

        }

    }

    @Override
    public String toString() {
        return "absolute discounting(d=" + d + ")";
    }

}
