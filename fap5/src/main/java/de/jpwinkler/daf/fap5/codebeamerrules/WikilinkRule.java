package de.jpwinkler.daf.fap5.codebeamerrules;

import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class WikilinkRule extends RegexIssueRule {

    @Override
    protected long getSeverity(final DoorsObject object) {
        return CodeBeamerConstants.SEVERITY_WIKILINK;
    }

    @Override
    protected String getMarkerType() {
        return CodeBeamerConstants.MARKER_WIKILINK;
    }

    @Override
    protected Pattern getRegexPattern() {
        return Pattern.compile("\\[CB_[a-zA-Z0-9_]*-[0-9]*\\]");
    }

    @Override
    protected String getDescription(final DoorsObject object, final String patternInstance) {
        return String.format("Objekt enth�lt den Wikilink %s", patternInstance);
    }

    @Override
    protected String getIssueType() {
        return "Wikilink";
    }
}
