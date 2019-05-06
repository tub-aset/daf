package de.jpwinkler.daf.search;

import de.jpwinkler.daf.model.DoorsModule;
import java.util.regex.Pattern;

public class RegexTagSearchExpression extends SearchExpression {

    private final Pattern pattern;

    public RegexTagSearchExpression(final String tag) {
        pattern = Pattern.compile(tag, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return module.hasTag(pattern);
    }

}
