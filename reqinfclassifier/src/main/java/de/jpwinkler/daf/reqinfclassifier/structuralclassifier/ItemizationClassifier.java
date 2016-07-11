package de.jpwinkler.daf.reqinfclassifier.structuralclassifier;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;

public class ItemizationClassifier extends Classifier<String> {

    private static final double THRESHOLD = 0.7;
    private static final List<Character> LETTERS = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    public ItemizationClassifier(final ClassifierContext context) {
        super(context);
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        final Map<Character, Integer> firstCharacters = new HashMap<>();

        final List<String> lines = context.getLines();

        if (lines.size() < 2) {
            return null;
        }

        for (String line : lines) {
            line = line.trim();
            final Character firstChar = line.length() > 0 ? line.charAt(0) : null;
            if (!LETTERS.contains(firstChar)) {
                if (firstCharacters.containsKey(Character.toLowerCase(firstChar))) {
                    firstCharacters.put(firstChar, firstCharacters.get(firstChar) + 1);
                } else {
                    firstCharacters.put(firstChar, 1);
                }
            }
        }

        if (firstCharacters.values().stream().anyMatch(n -> (double) n / lines.size() > THRESHOLD)) {
            return "itemization";
        } else {
            return null;
        }

    }

}
