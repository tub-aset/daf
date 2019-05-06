package de.jpwinkler.daf.search;

import de.jpwinkler.daf.model.DoorsModule;
import java.util.regex.Pattern;

public class NameSearchExpression extends SearchExpression {

    private final Pattern pattern;

    public NameSearchExpression(final String name) {
        super();
        pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return pattern.matcher(module.getName()).find();
    }

}
