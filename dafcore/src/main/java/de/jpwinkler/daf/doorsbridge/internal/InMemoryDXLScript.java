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
package de.jpwinkler.daf.doorsbridge.internal;

import java.io.IOException;

public class InMemoryDXLScript implements DXLScript {

    private final String dxl;

    public InMemoryDXLScript(final String dxl) {
        super();
        this.dxl = dxl;
    }

    @Override
    public String getDXL() throws IOException {
        return dxl;
    }

    public String getDxl() {
        return dxl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dxl == null) ? 0 : dxl.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InMemoryDXLScript other = (InMemoryDXLScript) obj;
        if (dxl == null) {
            if (other.dxl != null) {
                return false;
            }
        } else if (!dxl.equals(other.dxl)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InMemoryDXLScript [dxl=" + dxl + "]";
    }

}
