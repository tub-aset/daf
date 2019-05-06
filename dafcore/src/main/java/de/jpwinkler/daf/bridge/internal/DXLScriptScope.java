/*
 * doorsbridge - A library for Java to Doors interaction.
 * Copyright (C) 2016 Jonas Winkler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jpwinkler.daf.bridge.internal;

import java.io.IOException;

class DXLScriptScope implements DXLScript {

    private final DoorsScriptBuilder builder = new DoorsScriptBuilder();

    public DoorsScriptBuilder getBuilder() {
        return builder;
    }

    @Override
    public String getDXL() throws IOException {
        return builder.build();
    }

    @Override
    public String toString() {
        return "DXLScriptScope";
    }

}
