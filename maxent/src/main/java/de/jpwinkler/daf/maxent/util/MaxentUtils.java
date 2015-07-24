package de.jpwinkler.daf.maxent.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxentUtils {

    private static class ComparableTuple<T1 extends Comparable<T1>, T2> implements Comparable<ComparableTuple<T1, T2>> {
        private final T1 left;
        private final T2 right;

        public ComparableTuple(final T1 left, final T2 right) {
            super();
            this.left = left;
            this.right = right;
        }

        public T1 getLeft() {
            return left;
        }

        public T2 getRight() {
            return right;
        }

        @Override
        public int compareTo(final ComparableTuple<T1, T2> o) {
            return -this.left.compareTo(o.getLeft());
        }
    }

    public static String printMostLikelyOutcomes(final String[] x, final double[] p, final int numOutcomes) {
        final List<ComparableTuple<Double, String>> list = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            list.add(new ComparableTuple<Double, String>(p[i], x[i]));
        }

        Collections.sort(list);

        String result = "";

        for (int i = 0; i < Math.min(list.size(), numOutcomes); i++) {
            result += String.format("%s %f, ", list.get(i).getRight(), list.get(i).getLeft() * 100);
        }

        return result;
    }

}
