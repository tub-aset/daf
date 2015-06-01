package de.jpwinkler.daf.fap5.codebeamerrules;

import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class IllegalFormattingRule extends RegexIssueRule {

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_ILLEGAL_FORMATTING;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_ILLEGAL_FORMATTING;
    }

    @Override
    protected Pattern getRegexPattern() {
        return Pattern.compile("\\((.*?);\\)");
    }

    @Override
    protected String getDescription(final DoorsObject object, final String patternInstance) {
        return String.format("Objekt enth�lt Formatierung %s", patternInstance);
    }

    @Override
    protected String getIssueType() {
        return "Codebeamer-Formatierung";
    }
}
