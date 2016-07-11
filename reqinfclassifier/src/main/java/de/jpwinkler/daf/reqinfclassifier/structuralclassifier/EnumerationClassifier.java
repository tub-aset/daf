package de.jpwinkler.daf.reqinfclassifier.structuralclassifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;

public class EnumerationClassifier extends Classifier<String> {

    private static final double THRESHOLD = 0.7;

    private static final List<Integer> VALID_STARTING_CODEPOINTS = Arrays.asList("A", "a", "1").stream().map(c -> c.codePointAt(0)).collect(Collectors.toList());

    public EnumerationClassifier(final ClassifierContext context) {
        super(context);
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        if (context.getLines().size() < 2) {
            return null;
        }

        final List<Integer> codePoints = context.getLines().stream().map(l -> l.codePointAt(0)).collect(Collectors.toList());

        int longestSequence = 1;

        for (int i = 0; i < codePoints.size() - 1; i++) {
            if ((longestSequence > 1 || VALID_STARTING_CODEPOINTS.contains(codePoints.get(i))) && codePoints.get(i) == codePoints.get(i + 1) - 1) {
                longestSequence++;
            } else {
                longestSequence = 1;
            }
        }

        if ((double) longestSequence / codePoints.size() > THRESHOLD) {
            return "enumeration";
        } else {
            return null;
        }
    }

}
