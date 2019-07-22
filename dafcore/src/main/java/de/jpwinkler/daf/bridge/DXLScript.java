package de.jpwinkler.daf.bridge;

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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public abstract class DXLScript {

    private final URI source;

    public DXLScript() {
        this(null);
    }

    protected DXLScript(URI source) {
        this.source = source;
    }

    public abstract String build();

    public URI getSource() {
        return source;
    }

    public static DXLScript fromFile(File dxlFile) {
        return new DXLScript(dxlFile.toPath().toUri()) {
            @Override
            public String build() {
                try {
                    return FileUtils.readFileToString(dxlFile, Charset.forName("UTF-8"));
                } catch (IOException ex) {
                    throw new ScriptNotFoundException(ex);
                }
            }

        };
    }

    public static DXLScript fromResource(String resourceName) {
        return fromResource(DXLScript.class.getClassLoader(), resourceName);
    }

    public static DXLScript fromResource(ClassLoader cl, String resourceNameIn) {
        String resourceName = "dxl/" + resourceNameIn;
        try {
            URI uri = cl.getResource(resourceName).toURI();
            return new DXLScript(uri) {
                @Override
                public String build() {
                    try {
                        final InputStream resourceAsStream = cl.getResourceAsStream(resourceName);
                        if (resourceAsStream == null) {
                            throw new ScriptNotFoundException("Could not read script " + resourceName + " (stream was null)");
                        }
                        return IOUtils.toString(resourceAsStream, Charset.forName("UTF-8"));
                    } catch (IOException ex) {
                        throw new ScriptNotFoundException(ex);
                    }
                }

            };
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static DXLScript fromString(String script) {
        return new DXLScript((URI) null) {
            @Override
            public String build() {
                return script;
            }

        };
    }
}
