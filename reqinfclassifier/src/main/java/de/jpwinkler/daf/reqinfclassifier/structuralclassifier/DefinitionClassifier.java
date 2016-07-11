package de.jpwinkler.daf.reqinfclassifier.structuralclassifier;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;
import de.jpwinkler.daf.reqinfclassifier.utils.SentenceUtils;

public class DefinitionClassifier extends Classifier<String> {

    private static final double THRESHOLD = 0.6;
    private static final List<String> DEFINITION_DELIMITERS = Arrays.asList("\t", ":");

    public DefinitionClassifier(final ClassifierContext context) {
        super(context);
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        int positive = 0;
        for (final String line : context.getPreprocessedLines()) {

            for (final String delimiter : DEFINITION_DELIMITERS) {
                if (line.contains(delimiter)) {
                    final String first = line.substring(0, line.indexOf(delimiter)).trim();
                    final String last = line.substring(line.indexOf(delimiter) + delimiter.length()).trim();

                    if (!first.isEmpty() &&
                            !context.getTrees(first).stream().anyMatch(s -> SentenceUtils.containsSentence(s)) &&
                            !context.getTrees(last).stream().anyMatch(s -> SentenceUtils.containsSentence(s))) {
                        positive++;
                        break;
                    }
                }
            }
        }

        if ((double) positive / context.getPreprocessedLines().size() > THRESHOLD) {
            return "definition";
        } else {
            return null;
        }
    }

}
