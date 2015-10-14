package de.jpwinkler.daf.documenttagging.hypermarkovchain;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey2;

public class HyperMarkovChain<T> {

    private final Set<T> nodes = new HashSet<>();

    private final Map<CompositeKey2<T, T>, Edge<T>> edges = new HashMap<>();

    public Set<T> getNodes() {
        return nodes;
    }

    public Collection<Edge<T>> getEdges() {
        return edges.values();
    }

    public Edge<T> getEdge(final T node1, final T node2) {
        return edges.get(new CompositeKey2<>(node1, node2));
    }

    public Weight getWeight(final T node1, final T node2, final T target) {
        final Edge<T> edge = edges.get(new CompositeKey2<>(node1, node2));
        if (edge != null) {
            return edge.getWeight(target);
        } else {
            return Weight.ZERO;
        }
    }

    public void putWeight(final T node1, final T node2, final T target, final Weight weight) {
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(target);
        Edge<T> edge = edges.get(new CompositeKey2<>(node1, node2));
        if (edge == null) {
            edge = new Edge<>(node1, node2);
            edges.put(new CompositeKey2<>(node1, node2), edge);
        }
        edge.getWeightedTargetNodes().put(target, weight);
    }

    public String getDOT(final double probabilityThreshold) {
        final StringBuilder builder = new StringBuilder();
        builder.append("digraph {\n");
        for (final T n1 : nodes) {
            final String n1s = String.valueOf(n1).replace(' ', '_');
            builder.append("subgraph cluster_" + n1s + " {\n");
            builder.append("label = \"" + n1s + "\";\n");
            final Set<T> nodesToDescribe = new HashSet<>();
            for (final T n2 : nodes) {
                final String n2s = String.valueOf(n2).replace(' ', '_');
                final Edge<T> edge = getEdge(n1, n2);
                if (edge != null) {
                    for (final T n3 : nodes) {
                        final String n3s = String.valueOf(n3).replace(' ', '_');
                        final Weight weight = edge.getWeight(n3);
                        if (weight != null && weight.doubleValue() > probabilityThreshold) {
                            builder.append(n1s + "__" + n2s + " -> " + n1s + "__" + n3s + "[label=\"" + String.format("%.1f", weight.doubleValue() * 100) + "%\"];\n");
                            nodesToDescribe.add(n2);
                            nodesToDescribe.add(n3);
                        }
                    }
                }
            }
            for (final T node : nodesToDescribe) {
                final String nodes = String.valueOf(node).replace(' ', '_');
                builder.append(n1s + "__" + nodes + "[label=\"" + nodes + "\"];\n");
            }
            builder.append("}\n");
        }
        builder.append("}\n");
        return builder.toString();
    }

    public void validate() {
        edges.values().stream().forEach(e -> e.validate());
    }
}
