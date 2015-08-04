package de.jpwinkler.daf.documenttagging;

import java.util.ArrayList;
import java.util.List;

public class ConfusionMatrix<T> {


    /**
     * This matrix is organized as following:
     *
     * <pre>
     *                predicted class
     *                a  b  c
     *              a .  .  .
     *              b .  .  .
     * actual class c .  .  .
     * </pre>
     *
     * The first dimension (x axis) is used to index predicted classes, the
     * second dimension (y axis) is used to index actual classes.
     */
    private final int[][] matrix;

    private final List<T> tags;

    private int sumRow(final int row) {
        int result = 0;
        for (int i = 0; i < tags.size(); i++) {
            result += matrix[i][row];
        }
        return result;
    }

    private int sumColumn(final int column) {
        int result = 0;
        for (int i = 0; i < tags.size(); i++) {
            result += matrix[column][i];
        }
        return result;
    }

    public <X> ConfusionMatrix(final TaggedDocument<X, T> taggedDocument) {
        tags = new ArrayList<>(taggedDocument.getTags());
        matrix = new int[tags.size()][tags.size()];

        for (final X e : taggedDocument.getElements()) {
            final T predicted = taggedDocument.getPredictedTag(e);
            final T actual = taggedDocument.getActualTag(e);
            if (predicted != null && actual != null) {
                matrix[tags.indexOf(predicted)][tags.indexOf(actual)]++;
            } else {
                System.out.println("oops...");
            }
        }
    }

    public ConfusionMatrix(final List<T> tags) {
        this.tags = tags;
        matrix = new int[tags.size()][tags.size()];
    }

    public int get(final T predicted, final T actual) {
        return matrix[tags.indexOf(predicted)][tags.indexOf(actual)];
    }

    public void set(final T predicted, final T actual, final int count) {
        matrix[tags.indexOf(predicted)][tags.indexOf(actual)] = count;
    }

    public float getPrecision(final T tag) {
        final int tagIndex = tags.indexOf(tag);
        final int sum = sumColumn(tagIndex);
        if (sum > 0) {
            return (float) matrix[tagIndex][tagIndex] / (float) sum;
        } else {
            return 0;
        }
    }

    public float getRecall(final T tag) {
        final int tagIndex = tags.indexOf(tag);
        final int sum = sumRow(tagIndex);
        if (sum > 0) {
            return (float) matrix[tagIndex][tagIndex] / (float) sum;
        } else {
            return 0;
        }
    }

    public float getF1Score(final T tag) {
        final float precision = getPrecision(tag);
        final float recall = getRecall(tag);
        if (precision + recall > 0) {
            return 2.0f * (precision * recall) / (precision + recall);
        } else {
            return 0;
        }
    }

    public float getMicroPrecision() {
        float a = 0;
        float b = 0;

        for (int tagIndex = 0; tagIndex < tags.size(); tagIndex++) {
            a += matrix[tagIndex][tagIndex];
            b += sumColumn(tagIndex);
        }

        return a / b;
    }

    public float getMacroPrecision() {
        float sum = 0;
        for (final T tag : tags) {
            sum += getPrecision(tag);
        }
        return sum / tags.size();
    }

    public float getMicroRecall() {
        float a = 0;
        float b = 0;

        for (int tagIndex = 0; tagIndex < tags.size(); tagIndex++) {
            a += matrix[tagIndex][tagIndex];
            b += sumRow(tagIndex);
        }

        return a / b;
    }

    public float getMacroRecall() {
        float sum = 0;
        for (final T tag : tags) {
            sum += getRecall(tag);
        }
        return sum / tags.size();
    }

    public float getMicroF1Score() {
        return 2.0f * (getMicroPrecision() * getMicroRecall()) / (getMicroPrecision() + getMicroRecall());
    }

    public float getMacroF1Score() {
        return 2.0f * (getMacroPrecision() * getMacroRecall()) / (getMacroPrecision() + getMacroRecall());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (final T headerTag : tags) {
            builder.append(headerTag.toString());
            builder.append("\t");
        }
        builder.append("\n");
        for (final T actual : tags) {
            builder.append(actual.toString());
            builder.append("\t");
            for (final T predicted : tags) {
                builder.append(get(predicted, actual));
                builder.append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
