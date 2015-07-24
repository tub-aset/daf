package de.jpwinkler.daf.recursiveviterbi.scenario.util;

import java.math.BigDecimal;
import java.util.Random;

public class ProbabilityUtils {

    private static final Random r = new Random();

    public static BigDecimal[] getProbabilities(final int count) {
        final BigDecimal[] result = new BigDecimal[count];
        BigDecimal sum = BigDecimals.ZERO;
        for (int i = 0; i < count; i++) {
            result[i] = new BigDecimal(r.nextDouble(), BigDecimals.CONTEXT);
            sum = sum.add(result[i]);
        }

        for (int i = 0; i < count; i++) {
            result[i] = result[i].divide(sum, BigDecimals.CONTEXT);
        }

        return result;
    }

    public static int[] getDistribution(final long sum, final int count) {
        final int[] result = new int[count];

        for (int i = 0; i < sum; i++) {
            result[r.nextInt(count)]++;
        }

        return result;
    }

    public static BigDecimal[] getBigDecimalArray(final int size) {
        return getBigDecimalArray(size, BigDecimals.ZERO);
    }

    public static BigDecimal[] getBigDecimalArray(final int size, final BigDecimal initialValue) {
        final BigDecimal[] result = new BigDecimal[size];

        for (int i = 0; i < size; i++) {
            result[i] = initialValue;
        }

        return result;
    }

    public static BigDecimal[][] getBigDecimalMatrix(final int sizeX, final int sizeY) {
        return getBigDecimalMatrix(sizeX, sizeY, BigDecimals.ZERO);
    }

    public static BigDecimal[][] getBigDecimalMatrix(final int sizeX, final int sizeY, final BigDecimal initialValue) {
        final BigDecimal[][] result = new BigDecimal[sizeX][sizeY];

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                result[x][y] = initialValue;
            }
        }
        return result;
    }
}
