package de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing;

import java.util.Set;

import de.jpwinkler.daf.documenttagging.hypermarkovchain.Edge;

public class NoSmoothing<T> extends AbstractSmoothingTechnique<T> {

    @Override
    public void smoothEdge(final Set<T> nodes, final Edge<T> edge) {
    }

    @Override
    public String toString() {
        return "no smoothing";
    }
}
