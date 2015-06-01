package de.jpwinkler.daf.fap5.codebeamerrules;

import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class EllipsisRule extends RegexIssueRule {

    @Override
    protected String getObjectText(final DoorsObject object) {
        return object.getText().replace(" ", "");
    }

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_ELLIPSIS;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_ELLIPSIS;
    }

    @Override
    protected Pattern getRegexPattern() {
        return Pattern.compile("(?<!,)\\.\\.\\.");
    }

    @Override
    protected String getDescription(final DoorsObject object, final String patternInstance) {
        return "Objekt enth�lt den Text \"...\"";
    }

    @Override
    protected String getIssueType() {
        return "Platzhalter \"...\"";
    }
}
