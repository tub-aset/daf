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
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Objects;
import java.util.function.Predicate;

class AttributeFilter implements Predicate<DoorsTreeNode> {

    private final String attribute;
    private final ValueMatcher valueMatcher;

    public AttributeFilter(final String attribute, ValueMatcher valueMatcher) {
        this.attribute = attribute;
        this.valueMatcher = valueMatcher;
    }

    @Override
    public boolean test(final DoorsTreeNode object) {
        String testString;
        switch (attribute) {
            case "__level__":
                testString = Objects.toString(object.getFullNameSegments().size());
                break;
            case "__path__":
                testString = object.getFullName();
                break;
            case "__name__":
                testString = object.getName();
                break;
            case "__text__":
                testString = (object instanceof DoorsObject) ? ((DoorsObject) object).getText() : null;
                break;
            default:
                testString = object.getAttributes().get(attribute);
                break;
        }

        return valueMatcher != null ? valueMatcher.test(testString) : (testString == null || testString.isEmpty());
    }

}
