package de.jpwinkler.daf.fap5.codebeamerrules;

import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class TodoRule extends RegexIssueRule {

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_TODO;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_TODO;
    }

    @Override
    protected Pattern getRegexPattern() {
        return Pattern.compile("todo", Pattern.CASE_INSENSITIVE);
    }

    @Override
    protected String getDescription(final DoorsObject object, final String patternInstance) {
        return String.format("Objekt enth�lt den Text %s", patternInstance);
    }

    @Override
    protected String getIssueType() {
        return "Platzhalter \"TODO\"";
    }
}