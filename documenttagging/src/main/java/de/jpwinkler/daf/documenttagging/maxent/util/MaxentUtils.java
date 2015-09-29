package de.jpwinkler.daf.documenttagging.maxent.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import opennlp.maxent.GISModel;
import opennlp.model.Context;
import opennlp.model.IndexHashTable;

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

        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < Math.min(list.size(), numOutcomes); i++) {
            result.append(String.format("%s %f\n", list.get(i).getRight(), list.get(i).getLeft() * 100));
        }

        return result.toString();
    }

    public static String printMostInfluencingPredicates(final GISModel model, final String outcome) {

        final Context[] contexts = (Context[]) model.getDataStructures()[0];
        final IndexHashTable<String> predicateIndices = (IndexHashTable<String>) model.getDataStructures()[1];

        final Map<Integer, String> predicateNames = new HashMap<>();
        for (final String e : predicateIndices.toArray(new String[predicateIndices.size()])) {
            predicateNames.put(predicateIndices.get(e), e);
        }

        final int outcomeIndex = model.getIndex(outcome);

        final List<ComparableTuple<Double, String>> list = new ArrayList<>();

        for (int i = 0; i < contexts.length; i++) {
            final Context c = contexts[i];
            final int oi = ArrayUtils.indexOf(c.getOutcomes(), outcomeIndex);
            if (oi != -1) {
                final double parameter = c.getParameters()[oi];
                list.add(new ComparableTuple<Double, String>(parameter, predicateNames.get(i)));
            }
        }
        Collections.sort(list);

        final StringBuilder builder = new StringBuilder();

        for (final ComparableTuple<Double, String> t : list) {
            builder.append(t.getRight() + ": " + t.getLeft() + "\n");
        }

        return builder.toString();

    }

}
