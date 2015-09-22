package de.jpwinkler.daf.documenttagging.hypermarkovchain;

import java.util.HashMap;
import java.util.Map;

public class Edge<T> {

    private T source1, source2;

    private final Map<T, Weight> weightedTargetNodes = new HashMap<>();

    public Edge() {
        // TODO Auto-generated constructor stub
    }

    public Edge(final T source1, final T source2) {
        super();
        this.source1 = source1;
        this.source2 = source2;
    }

    public T getSource1() {
        return source1;
    }

    public void setSource1(final T source1) {
        this.source1 = source1;
    }

    public T getSource2() {
        return source2;
    }

    public void setSource2(final T source2) {
        this.source2 = source2;
    }

    public Map<T, Weight> getWeightedTargetNodes() {
        return weightedTargetNodes;
    }

    public Weight getWeight(final T target) {
        if (weightedTargetNodes.containsKey(target)) {
            return weightedTargetNodes.get(target);
        } else {
            return Weight.ZERO;
        }
    }

    public void validate() {
        final double totalSumOfWeights = weightedTargetNodes.values().stream().mapToDouble(w -> w.doubleValue()).reduce(0.0, (d1, d2) -> d1 + d2);
        if (Math.abs(totalSumOfWeights - 1.0) > 0.0000001) {
            throw new RuntimeException("Total sum of weights is " +
                    totalSumOfWeights);
        }
    }

}
