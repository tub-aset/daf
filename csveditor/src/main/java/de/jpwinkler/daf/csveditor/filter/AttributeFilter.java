package de.jpwinkler.daf.csveditor.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class AttributeFilter implements DoorsObjectFilter {

    private final String attribute;

    private final String filter;

    private final boolean matchCase;

    private final boolean regexp;

    private Pattern pattern;

    public AttributeFilter(final String attribute, final String filter, final boolean matchCase, final boolean regexp) {
        super();
        this.attribute = attribute;
        this.filter = filter;
        this.matchCase = matchCase;
        this.regexp = regexp;
        try {
            pattern = Pattern.compile(filter, matchCase ? 0 : Pattern.CASE_INSENSITIVE);
        } catch (final PatternSyntaxException e) {
            pattern = null;
        }
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        final String s = object.getAttributes().get(attribute);
        if (s != null) {
            if (regexp && pattern != null) {
                final Matcher matcher = pattern.matcher(s);
                return matcher.find();
            } else if (!regexp && matchCase) {
                return s.contains(filter);
            } else if (!regexp && !matchCase) {
                return s.toLowerCase().contains(filter.toLowerCase());
            }
        }
        return false;
    }

}
