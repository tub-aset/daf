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
package de.jpwinkler.libs.doorsbridge.internal;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ExternalDXLScript implements DXLScript {

    private final File dxlFile;

    public ExternalDXLScript(final File dxlFile) {
        super();
        this.dxlFile = dxlFile;
    }

    @Override
    public String getDXL() throws IOException {
        return FileUtils.readFileToString(dxlFile);
    }

    public File getDxlFile() {
        return dxlFile;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dxlFile == null) ? 0 : dxlFile.hashCode());
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
        final ExternalDXLScript other = (ExternalDXLScript) obj;
        if (dxlFile == null) {
            if (other.dxlFile != null) {
                return false;
            }
        } else if (!dxlFile.equals(other.dxlFile)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalDXLScript [dxlFile=" + dxlFile + "]";
    }

}
