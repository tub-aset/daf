package de.jpwinkler.daf.doorsdb.search;

import java.util.regex.Pattern;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public class RegexTagSearchExpression extends DBSearchExpression {

    private final Pattern pattern;

    public RegexTagSearchExpression(final String tag) {
        pattern = Pattern.compile(tag, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean matches(final DBModule module) {
        return module.getTags().stream().map(t -> t.getName()).anyMatch(s -> pattern.matcher(s).find());
    }

}
