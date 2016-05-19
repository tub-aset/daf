package de.jpwinkler.daf.reqinfclassifier.syntacticclassifier;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;

public class MathExpressionClassifier extends Classifier<String> {

    private static final List<Character> IGNORE_SYMBOLS = Arrays.asList(' ');
    private static final List<Character> MATH_SYMBOLS = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/', '(', ')', '=', ',', '.');
    private static final double THRESHOLD_A = 0.1;
    private static final double THRESHOLD_B = 0.2;

    public MathExpressionClassifier(final ClassifierContext context) {
        super(context);
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        final String text = context.getDoorsObject().getText();
        int mathSymbolCount = 0;
        int totalSymbolCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (!IGNORE_SYMBOLS.contains(text.charAt(i))) {
                totalSymbolCount++;
                if (MATH_SYMBOLS.contains(text.charAt(i))) {
                    mathSymbolCount++;
                }
            }
        }
        if ((double) mathSymbolCount / totalSymbolCount > movingThreshold(totalSymbolCount)) {
            return "math_expression";
        } else {
            return null;
        }
    }

    private double movingThreshold(final int numchars) {
        return Math.pow(2, -numchars * THRESHOLD_A) * (1 - THRESHOLD_B) + THRESHOLD_B;
    }
}
