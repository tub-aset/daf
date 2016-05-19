package de.jpwinkler.daf.reqinfclassifier.syntacticclassifier;

import java.util.regex.Pattern;

import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.DoorsObjectContext;
import de.jpwinkler.daf.reqinfclassifier.utils.SentenceUtils;

public class IdentifierClassifier extends Classifier<String> {

    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("([a-zäöüß])([_\\-a-z0-9äöüß]*)");
    private static final double THRESHOLD = 0.6;

    public IdentifierClassifier(final ClassifierContext context) {
        super(context);
    }

    @Override
    protected String run(final DoorsObjectContext context) {
        if (context.getLines().size() > 0) {
            final long numMatches = context.getLines().stream().filter(l -> isIdentifier(context, l)).count();
            if (context.getLines().size() == 1 && numMatches == 1) {
                return "identifier";
            } else if ((double) numMatches / context.getLines().size() > THRESHOLD) {
                return "identifier_list";
            }
        }
        return null;
    }

    private boolean isIdentifier(final DoorsObjectContext context, final String s) {
        if (context.getTrees(s).size() == 1 && SentenceUtils.isSingleNoun(context.getTrees(s).get(0))) {
            return true;
        } else if (IDENTIFIER_PATTERN.matcher(s.toLowerCase()).matches()) {
            return true;
        } else {
            return false;
        }
    }

}
