package de.jpwinkler.daf.documenttagging;

import java.util.ArrayList;
import java.util.List;

public class ConfusionMatrix<TAG> {


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

    private final List<TAG> tags;

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

    public <X> ConfusionMatrix(final TaggedDocument<X, TAG> taggedDocument) {
        tags = new ArrayList<>(taggedDocument.getTags());
        matrix = new int[tags.size()][tags.size()];

        for (final X e : taggedDocument.getElements()) {
            final TAG predicted = taggedDocument.getPredictedTag(e);
            final TAG actual = taggedDocument.getActualTag(e);
            if (predicted != null && actual != null) {
                matrix[tags.indexOf(predicted)][tags.indexOf(actual)]++;
            } else {
                System.out.println("oops...");
            }
        }
    }

    public ConfusionMatrix(final List<TAG> tags) {
        this.tags = tags;
        matrix = new int[tags.size()][tags.size()];
    }

    public int get(final TAG predicted, final TAG actual) {
        return matrix[tags.indexOf(predicted)][tags.indexOf(actual)];
    }

    public void set(final TAG predicted, final TAG actual, final int count) {
        matrix[tags.indexOf(predicted)][tags.indexOf(actual)] = count;
    }

    public float getPrecision(final TAG tag) {
        final int tagIndex = tags.indexOf(tag);
        final int sum = sumColumn(tagIndex);
        if (sum > 0) {
            return (float) matrix[tagIndex][tagIndex] / (float) sum;
        } else {
            return 0;
        }
    }

    public float getRecall(final TAG tag) {
        final int tagIndex = tags.indexOf(tag);
        final int sum = sumRow(tagIndex);
        if (sum > 0) {
            return (float) matrix[tagIndex][tagIndex] / (float) sum;
        } else {
            return 0;
        }
    }

    public float getF1Score(final TAG tag) {
        return 2.0f * (getPrecision(tag) * getRecall(tag)) / (getPrecision(tag) + getRecall(tag));
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
        for (final TAG tag : tags) {
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
        for (final TAG tag : tags) {
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
        for (final TAG headerTag : tags) {
            builder.append(headerTag.toString());
            builder.append("\t");
        }
        builder.append("\n");
        for (final TAG actual : tags) {
            builder.append(actual.toString());
            builder.append("\t");
            for (final TAG predicted : tags) {
                builder.append(get(predicted, actual));
                builder.append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
