package de.jpwinkler.daf.documenttagging;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This is a pretty standard confusion matrix implementation. This class
 * provides functions to calculate per-class precision, recall and f1.
 * Macro-averaged and micro-averaged precision/recall/f1 are also available.
 * </p>
 *
 * <p>
 * This matrix is organized as following:
 * </p>
 *
 * <pre>
 *                predicted class
 *                a  b  c
 *              a .  .  .
 *              b .  .  .
 * actual class c .  .  .
 * </pre>
 *
 * <p>
 * The first dimension (x axis) is used to index predicted classes, the second
 * dimension (y axis) is used to index actual classes.
 * </p>
 *
 * @author JONWINK
 *
 * @param <T>
 *            Type of the tags.
 */
public class ConfusionMatrix<T> {

    private final int[][] matrix;
    private final List<T> tags;

    /**
     * Constructs a confusion matrix using a tagged document. Sets up tags and
     * fills the matrix according to the tagged document.
     *
     * @param taggedDocument
     *            A tagged document from which the matrix shall be build.
     */
    public <X> ConfusionMatrix(final TaggedDocument<X, T> taggedDocument) {
        tags = new ArrayList<>(taggedDocument.getTags());
        matrix = new int[tags.size()][tags.size()];

        for (final X e : taggedDocument.getElements()) {
            final T predicted = taggedDocument.getPredictedTag(e);
            final T actual = taggedDocument.getActualTag(e);
            if (predicted != null && actual != null) {
                matrix[tags.indexOf(predicted)][tags.indexOf(actual)]++;
            }
        }
    }

    /**
     * Constructs an empty confusion matrix using the supplied tags.
     *
     * @param tags
     */
    public ConfusionMatrix(final List<T> tags) {
        this.tags = tags;
        matrix = new int[tags.size()][tags.size()];
    }

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

    /**
     * Returns the number of instances where the predicted class was
     * <code>predicted</code> and the actual class was <code>actual</code>.
     *
     * @param predicted
     * @param actual
     * @return
     */
    public int get(final T predicted, final T actual) {
        return matrix[tags.indexOf(predicted)][tags.indexOf(actual)];
    }

    /**
     * Sets the number of instances where the predicted class was
     * <code>predicted</code> and the actual class was <code>actual</code>.
     *
     * @param predicted
     * @param actual
     * @param count
     */
    public void set(final T predicted, final T actual, final int count) {
        matrix[tags.indexOf(predicted)][tags.indexOf(actual)] = count;
    }

    /**
     * Returns the precision for tag <code>tag</code>.
     *
     * @param tag
     * @return
     */
    public float getPrecision(final T tag) {
        final int tagIndex = tags.indexOf(tag);
        final int sum = sumColumn(tagIndex);
        if (sum > 0) {
            return (float) matrix[tagIndex][tagIndex] / (float) sum;
        } else {
            return 0;
        }
    }

    /**
     * Returns the recall for tag <code>tag</code>.
     *
     * @param tag
     * @return
     */
    public float getRecall(final T tag) {
        final int tagIndex = tags.indexOf(tag);
        final int sum = sumRow(tagIndex);
        if (sum > 0) {
            return (float) matrix[tagIndex][tagIndex] / (float) sum;
        } else {
            return 0;
        }
    }

    /**
     * Returns the f1 score for tag <code>tag</code>.
     *
     * @param tag
     * @return
     */
    public float getF1Score(final T tag) {
        final float precision = getPrecision(tag);
        final float recall = getRecall(tag);
        if (precision + recall > 0) {
            return 2.0f * (precision * recall) / (precision + recall);
        } else {
            return 0;
        }
    }

    /**
     * Returns the micro-averaged precision.
     *
     * @return
     */
    public float getMicroPrecision() {
        float a = 0;
        float b = 0;

        for (int tagIndex = 0; tagIndex < tags.size(); tagIndex++) {
            a += matrix[tagIndex][tagIndex];
            b += sumColumn(tagIndex);
        }

        return a / b;
    }

    /**
     * Returns the macro-averaged precision.
     *
     * @return
     */
    public float getMacroPrecision() {
        float sum = 0;
        for (final T tag : tags) {
            sum += getPrecision(tag);
        }
        return sum / tags.size();
    }

    /**
     * Returns the micro-averaged recall.
     *
     * @return
     */
    public float getMicroRecall() {
        float a = 0;
        float b = 0;

        for (int tagIndex = 0; tagIndex < tags.size(); tagIndex++) {
            a += matrix[tagIndex][tagIndex];
            b += sumRow(tagIndex);
        }

        return a / b;
    }

    /**
     * Returns the macro-averaged recall.
     *
     * @return
     */
    public float getMacroRecall() {
        float sum = 0;
        for (final T tag : tags) {
            sum += getRecall(tag);
        }
        return sum / tags.size();
    }

    /**
     * returns the micro-averaged f1 score.
     *
     * @return
     */
    public float getMicroF1Score() {
        return 2.0f * (getMicroPrecision() * getMicroRecall()) / (getMicroPrecision() + getMicroRecall());
    }

    /**
     * returns the macro-averaged f1 score.
     *
     * @return
     */
    public float getMacroF1Score() {
        return 2.0f * (getMacroPrecision() * getMacroRecall()) / (getMacroPrecision() + getMacroRecall());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (final T headerTag : tags) {
            builder.append(headerTag);
            builder.append("\t");
        }
        builder.append("\n");
        for (final T actual : tags) {
            builder.append(actual);
            builder.append("\t");
            for (final T predicted : tags) {
                builder.append(get(predicted, actual));
                builder.append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Creates and resturns a string representation of all tags and their
     * precision, recall and f1-scores.
     * 
     * @return
     */
    public String toStringEvaluationMetrics() {
        final StringBuilder builder = new StringBuilder();

        final int maxLength = tags.stream().filter(t -> t != null).mapToInt(t -> t.toString().length()).max().getAsInt();

        builder.append(String.format("%" + maxLength + "s", "label") + "\tprecision\trecall   \tf1-score\n");

        for (final T t : tags) {
            builder.append(String.format("%" + maxLength + "s", t));
            builder.append("\t");
            builder.append(String.format("%f", getPrecision(t)));
            builder.append("\t");
            builder.append(String.format("%f", getRecall(t)));
            builder.append("\t");
            builder.append(String.format("%f", getF1Score(t)));
            builder.append("\n");
        }

        builder.append(String.format("%" + maxLength + "s", "micro"));
        builder.append("\t");
        builder.append(String.format("%f", getMicroPrecision()));
        builder.append("\t");
        builder.append(String.format("%f", getMicroRecall()));
        builder.append("\t");
        builder.append(String.format("%f", getMicroF1Score()));
        builder.append("\n");

        builder.append(String.format("%" + maxLength + "s", "macro"));
        builder.append("\t");
        builder.append(String.format("%f", getMacroPrecision()));
        builder.append("\t");
        builder.append(String.format("%f", getMacroRecall()));
        builder.append("\t");
        builder.append(String.format("%f", getMacroF1Score()));
        builder.append("\n");

        return builder.toString();
    }
}
