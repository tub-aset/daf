package de.jpwinkler.daf.gui.filter;

import de.jpwinkler.daf.model.DoorsObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AttributeFilter extends DoorsObjectFilter {

    private final String attribute;

    private final String filter;

    private final boolean exactMatch;

    private final boolean regexp;

    private final Pattern pattern;

    public AttributeFilter(final String attribute, final String filter, final boolean exactMatch, final boolean regexp) {
        super();
        this.attribute = attribute;
        this.filter = filter;
        this.exactMatch = exactMatch;
        this.regexp = regexp;
        try {
            pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        } catch (final PatternSyntaxException e) {
            throw new RuntimeException()
                    ;
        }
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        final String s = object.getAttributes().get(attribute);
        if (s != null) {
            if (regexp && pattern != null) {
                final Matcher matcher = pattern.matcher(s);
                return exactMatch ? matcher.matches() : matcher.find();
            } else if (!regexp) {
                return exactMatch ? s.equalsIgnoreCase(filter) : s.toLowerCase().contains(filter.toLowerCase());
            }
        }
        return false;
    }

}
