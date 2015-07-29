package de.jpwinkler.daf.documenttagging;

import java.util.LinkedHashSet;
import java.util.Set;

import de.jpwinkler.daf.documenttagging.maxent.util.BiMap;

public class ConfusionMatrix<TAG> {

    private final BiMap<TAG, TAG, Integer> map = new BiMap<>();

    private final Set<TAG> tags = new LinkedHashSet<>();

    // TODO: this is utterly ugly.
    public ConfusionMatrix(final TaggedDocument taggedDocument) {
        for (final Object e : taggedDocument.getElements()) {
            final TAG actual = (TAG) taggedDocument.getActualTag(e);
            final TAG predicted = (TAG) taggedDocument.getPredictedTag(e);
            if (actual != null && predicted != null) {
                put(actual, predicted, get(actual, predicted) + 1);
            } else {
                System.out.println("oops...");
            }
        }
    }

    public ConfusionMatrix() {
    }

    public void put(final TAG actual, final TAG predicted, final int count) {
        map.put(actual, predicted, count);
        tags.add(predicted);
        tags.add(actual);
    }

    public int get(final TAG actual, final TAG predicted) {
        if (map.containsKey(actual, predicted)) {
            return map.get(actual, predicted);
        } else {
            return 0;
        }
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
                builder.append(get(actual, predicted));
                builder.append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
