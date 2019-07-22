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
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Arrays;
import java.util.function.Predicate;

class AndFilter implements Predicate<DoorsTreeNode> {

    private final Predicate<DoorsTreeNode>[] filters;

    @SafeVarargs
    public AndFilter(final Predicate<DoorsTreeNode>... filters) {
        this.filters = filters;
    }

    @Override
    public boolean test(final DoorsTreeNode object) {
        return Arrays.stream(filters).allMatch(filter -> filter.test(object));
    }

}
