package de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import de.jpwinkler.daf.documenttagging.hypermarkovchain.Edge;
import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey2;

public class VeryStupidBackoff<T> extends AbstractSmoothingTechnique<T> {

    private final double k;
    private final double d;
    private Set<T> nodesWithoutNull;

    public VeryStupidBackoff(final double k, final double d) {
        super();
        this.k = k;
        this.d = d;
    }

    private final Map<T, Integer> theMap = new HashMap<>();

    private int count(final T t) {
        if (theMap.containsKey(t)) {
            return theMap.get(t);
        }
        Stream<Entry<T, Integer>> stream = counts.entrySet().stream().flatMap(e -> e.getValue().entrySet().stream());
        if (t != null) {
            stream = stream.filter(e -> e.getKey() == t);
        }
        final int sum = stream.mapToInt(e -> e.getValue()).sum();
        theMap.put(t, sum);
        return sum;
    }

    private int count(final T pa, final T t) {
        Stream<Entry<T, Integer>> stream = counts.entrySet().stream().filter(e -> e.getKey().getK1() == pa).flatMap(e -> e.getValue().entrySet().stream());
        if (t != null) {
            stream = stream.filter(e -> e.getKey() == t);
        }
        return stream.mapToInt(e -> e.getValue()).sum();
    }

    private int count(final T pa, final T pre, final T t) {
        final Map<T, Integer> map = counts.get(new CompositeKey2<>(pa, pre));
        if (map == null) {
            return 0;
        }
        if (t != null) {
            return map.get(t) != null ? map.get(t) : 0;
        } else {
            return map.entrySet().stream().mapToInt(e -> e.getValue()).sum();
        }
    }

    private double pStar(final T t) {
        return d * count(t) / count(null);
    }

    private double pStar(final T pa, final T t) {
        return d * count(pa, t) / count(pa, null);
    }

    private double pStar(final T pa, final T pre, final T t) {
        return d * count(pa, pre, t) / count(pa, pre, null);
    }

    private double pS(final T t) {
        return pStar(t);
    }

    private double pS(final T pa, final T t) {
        if (count(pa, t) > k) {
            return pStar(pa, t);
        } else {
            final double a = (1 - nodesWithoutNull.stream().filter(n -> count(pa, n) > k).mapToDouble(n -> pStar(pa, n)).sum()) /
                    (nodesWithoutNull.stream().filter(n -> count(pa, n) <= k).mapToDouble(n -> pS(n)).sum());
            return a * pS(t);
        }
    }

    private double pS(final T pa, final T pre, final T t) {
        if (count(pa, pre, t) > k) {
            return pStar(pa, pre, t);
        } else {
            final double a = (1 - nodesWithoutNull.stream().filter(n -> count(pa, pre, n) > k).mapToDouble(n -> pStar(pa, pre, n)).sum()) /
                    (nodesWithoutNull.stream().filter(n -> count(pa, pre, n) <= k).mapToDouble(n -> pS(pa, n)).sum());
            return a * pS(pa, t);
        }
    }

    @Override
    public void smoothEdge(final Set<T> nodes, final Edge<T> edge) {
        nodesWithoutNull = new HashSet<>(nodes);
        nodesWithoutNull.remove(null);

        final T pa = edge.getSource1();
        final T pre = edge.getSource2();

        final Map<T, Double> smoothedProbabilities = new HashMap<>();
        for (final T node : nodesWithoutNull) {
            final double pS = pS(pa, pre, node);
            smoothedProbabilities.put(node, pS);
        }

        for (final T node : nodesWithoutNull) {
            edge.getWeight(node).setCount(smoothedProbabilities.get(node));
            edge.getWeight(node).setTotalCount(1);
        }

        edge.validate();
    }

    @Override
    public String toString() {
        return "very stupid backoff(k=" + k + ", d=" + d + ")";
    }

}
