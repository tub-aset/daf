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
import java.util.function.Predicate;

/**
 *
 * @author fwiesweg
 */
class ImplicationFilter implements Predicate<DoorsTreeNode> {

    private final Predicate<DoorsTreeNode> f1;
    private final Predicate<DoorsTreeNode> f2;

    public ImplicationFilter(Predicate<DoorsTreeNode> f1, Predicate<DoorsTreeNode> f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public boolean test(final DoorsTreeNode object) {
        return f1.test(object) ? f2.test(object) : true;
    }
}
