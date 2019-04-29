package de.jpwinkler.daf.doorsdb.search;

import java.util.regex.Pattern;

import de.jpwinkler.daf.doorsdb.model.DBModule;

public class NameSearchExpression extends DBSearchExpression {

    private final Pattern pattern;

    public NameSearchExpression(final String name) {
        super();
        pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean matches(final DBModule module) {
        return pattern.matcher(module.getName()).find();
    }

}
