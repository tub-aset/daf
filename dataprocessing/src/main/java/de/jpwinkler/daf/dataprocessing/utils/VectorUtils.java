package de.jpwinkler.daf.dataprocessing.utils;

public class VectorUtils {

    public static double[] oneHotVector(final int index, final int size) {
        final double[] result = new double[size];

        result[index] = 1;

        return result;
    }

    public static double[] softmax(final double[] input) {
        double partition = 0;

        for (final double i : input) {
            partition += Math.exp(i);
        }

        final double[] result = new double[input.length];

        for (int i = 0; i < input.length; i++) {
            result[i] = Math.exp(input[i]) / partition;
        }

        return result;

    }

    public static int getBestOutcomeIndex(final double[] input) {
        double max = Double.NEGATIVE_INFINITY;
        int maxI = -1;
        for (int i = 0; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
                maxI = i;
            }
        }
        return maxI;
    }
}
