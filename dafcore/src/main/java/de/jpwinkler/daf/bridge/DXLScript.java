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
package de.jpwinkler.daf.bridge;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class DXLScript {

    private final Supplier<String> dxlSupplier;

    private DXLScript(final Supplier<String> dxlSupplier) {
        this.dxlSupplier = dxlSupplier;
    }

    public String getDXL() {
        return dxlSupplier.get();
    }
    
    public static DXLScript fromBuilder(DoorsScriptBuilder scopeBuilder) {
        return new DXLScript(() -> scopeBuilder.build());
    }

    public static DXLScript fromFile(File dxlFile) {
        return new DXLScript(() -> {
            try {
                return FileUtils.readFileToString(dxlFile);
            } catch (IOException ex) {
                throw new ScriptNotFoundException(ex);
            }
        });
    }

    public static DXLScript fromResource(String resourceName) {
        return fromResource(DXLScript.class.getClassLoader(), resourceName);
    }

    public static DXLScript fromResource(ClassLoader cl, String resourceName) {
        return new DXLScript(() -> {
            try {
                final InputStream resourceAsStream = cl.getResourceAsStream("dxl/" + resourceName);
                if (resourceAsStream == null) {
                    throw new ScriptNotFoundException("Could not read script " + resourceName + " (stream was null)");
                }
                return IOUtils.toString(resourceAsStream);
            } catch (IOException ex) {
                throw new ScriptNotFoundException(ex);
            }
        });
    }

    public static DXLScript fromString(String script) {
        return new DXLScript(() -> script);
    }
}
