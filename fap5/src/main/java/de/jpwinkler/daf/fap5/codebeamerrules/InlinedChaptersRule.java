package de.jpwinkler.daf.fap5.codebeamerrules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class InlinedChaptersRule extends PredicateIssueRule {

    private static final Pattern PATTERN = Pattern.compile("[0-9]+\\.(([0-9]+\\.)+)");

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_INLINED_CHAPTERS;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_INLINED_CHAPTERS;
    }

    @Override
    protected String getIssueType() {
        return "Eingebettete Kapitel";
    }

    @Override
    protected boolean testObject(final DoorsObject o, final RuleContext context) {
        final String[] lines = o.getText().split("\n");
        int matchedLines = 0;
        for (final String line : lines) {
            final Matcher matcher = PATTERN.matcher(line.trim());
            if (matcher.find() && matcher.start() == 0) {
                matchedLines++;
            }
        }

        return matchedLines > 1;
    }

    @Override
    protected String getDescription(final DoorsObject object) {
        return "Objekt enth�lt in den Objekttext eingebettete Unterkapitel";
    }
}
