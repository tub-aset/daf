package de.jpwinkler.daf.filter;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fwiesweg
 */
class ValueMatcher implements Predicate<String> {

    private final String filter;
    private final boolean exactMatch;
    private final Pattern pattern;

    public ValueMatcher(String filter, boolean exactMatch, boolean caseSensitive, int patternFlags) {
        this.filter = filter;
        this.exactMatch = exactMatch;
        this.pattern = patternFlags >= 0 ? Pattern.compile(filter, caseSensitive ? patternFlags : (patternFlags | Pattern.CASE_INSENSITIVE)) : null;
    }

    @Override
    public boolean test(String s) {
        if (s == null) {
            s = "";
        }

        if (pattern != null) {
            final Matcher matcher = pattern.matcher(s);
            return exactMatch ? matcher.matches() : matcher.find();
        } else {
            return exactMatch ? s.equalsIgnoreCase(filter) : s.toLowerCase().contains(filter.toLowerCase());
        }
    }
}
