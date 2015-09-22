package de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing;

import java.util.Set;

import de.jpwinkler.daf.documenttagging.hypermarkovchain.Edge;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.Weight;

public class AddKSmoothing<T> extends AbstractSmoothingTechnique<T> {

    private final double k;

    public AddKSmoothing(final double k) {
        super();
        this.k = k;
    }

    @Override
    public void smoothEdge(final Set<T> nodes, final Edge<T> edge) {
        for (final Weight w : edge.getWeightedTargetNodes().values()) {
            w.setCount(w.getCount() + k);
            w.setTotalCount(w.getTotalCount() + edge.getWeightedTargetNodes().size() * k);
        }
    }

    @Override
    public String toString() {
        return "add-k smoothing(k=" + k + ")";
    }
}
