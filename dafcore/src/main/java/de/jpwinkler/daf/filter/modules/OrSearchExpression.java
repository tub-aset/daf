package de.jpwinkler.daf.filter.modules;

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

import de.jpwinkler.daf.model.DoorsModule;

public class OrSearchExpression extends SearchExpression {

    private final SearchExpression e1;
    private final SearchExpression e2;

    public OrSearchExpression(final SearchExpression e1, final SearchExpression e2) {
        super();
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return e1.matches(module) || e2.matches(module);
    }

}
