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
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class InternalDXLScript implements DXLScript {

    private final String scriptName;

    public InternalDXLScript(final String scriptName) {
        super();
        this.scriptName = scriptName;
    }

    @Override
    public String getDXL() throws IOException {
        final InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dxl/" + scriptName);
        if (resourceAsStream == null) {
            throw new ScriptNotFoundException("Could not read script " + scriptName + " (stream was null)");
        }
        return IOUtils.toString(resourceAsStream);
    }

    public String getScriptName() {
        return scriptName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((scriptName == null) ? 0 : scriptName.hashCode());
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
        final InternalDXLScript other = (InternalDXLScript) obj;
        if (scriptName == null) {
            if (other.scriptName != null) {
                return false;
            }
        } else if (!scriptName.equals(other.scriptName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InternalDXLScript [scriptName=" + scriptName + "]";
    }

}
