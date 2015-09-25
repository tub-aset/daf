package de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.jpwinkler.daf.documenttagging.hypermarkovchain.Edge;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.Weight;

public class KatzBackoff<T> extends AbstractSmoothingTechnique<T> {

    private final double k;
    private final double d;

    public KatzBackoff(final double k, final double d) {
        super();
        this.d = d;
        this.k = 1;
    }

    @Override
    public void smoothEdge(final Set<T> nodes, final Edge<T> edge) {

        final Map<T, Double> discountedWeights = new HashMap<>();

        for (final T node : nodes) {
            if (node != null) {
                final Weight weight = edge.getWeight(node);

                double p;

                if (weight.getCount() > k) {
                    // TODO: for some reason, k does not have any influence at
                    // all.
                    p = d * weight.doubleValue();
                } else {
                    final double sumOfProbabilitiesOfAllThingsWithBigramCountLessThanK = nodes.stream().filter(n -> edge.getWeight(n).getCount() <= k).mapToDouble(n -> d * getUnigramProbability(n)).sum();
                    final double b = 1 - nodes.stream().filter(n -> edge.getWeight(n).getCount() > k).mapToDouble(n -> d * edge.getWeight(n).doubleValue()).sum();
                    final double a = b / sumOfProbabilitiesOfAllThingsWithBigramCountLessThanK;
                    p = a * d * getUnigramProbability(node);
                }

                discountedWeights.put(node, p);
            }

        }

        for (final T node : nodes) {
            if (node != null) {
                final Weight weight = edge.getWeight(node);
                weight.setCount(discountedWeights.get(node));
                weight.setTotalCount(1);
            }
        }

        edge.validate();
    }

    @Override
    public String toString() {
        return "katz backoff(k=" + k + ", d=" + d + ")";
    }
}
