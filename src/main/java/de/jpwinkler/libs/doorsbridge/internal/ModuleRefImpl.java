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

import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.DoorsRuntimeException;
import de.jpwinkler.libs.doorsbridge.DoorsURL;
import de.jpwinkler.libs.doorsbridge.ModuleRef;

public class ModuleRefImpl implements ModuleRef {

    private static final String STANDARD_VIEW = "Standard view";

    private final DoorsApplicationImpl doorsApplicationImpl;
    private DoorsURL url;
    private String name;

    private boolean closed = false;

    public ModuleRefImpl(final DoorsApplicationImpl doorsApplicationImpl, final DoorsURL url) {
        this.doorsApplicationImpl = doorsApplicationImpl;
        this.url = url;
    }

    public ModuleRefImpl(final DoorsApplicationImpl doorsApplicationImpl, final String name) {
        this.doorsApplicationImpl = doorsApplicationImpl;
        this.name = name;
    }

    @Override
    public void exportToCSV(final File file) throws DoorsException {
        exportToCSV(file, STANDARD_VIEW);
    }

    @Override
    public void exportToCSV(final File file, final String view) throws DoorsException {
        if (closed) {
            throw new DoorsRuntimeException("Module is closed.");
        }
        doorsApplicationImpl.buildAndRunCommand(builder -> {
            builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
            builder.addLibrary(new InternalDXLScript("lib/export_csv.dxl"));
            builder.addScript(new InternalDXLScript("export_csv_single.dxl"));
            builder.setVariable("url", url != null ? url.getUrl() : null);
            builder.setVariable("name", name);
            builder.setVariable("view", view);
            builder.setVariable("file", file.getAbsolutePath());
        });
    }

    @Override
    public void gotoObject(final int absoluteNumber) {
        if (closed) {
            throw new DoorsRuntimeException("Module is closed.");
        }
        try {
            doorsApplicationImpl.buildAndRunCommand(builder -> {
                builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
                builder.addScript(new InternalDXLScript("goto_object.dxl"));
                builder.setVariable("url", url != null ? url.getUrl() : null);
                builder.setVariable("name", name);
                builder.setVariable("absoluteNumber", String.valueOf(absoluteNumber));
            });
        } catch (final DoorsException e) {
            // This should never happen, because the script never calls 'throw'.
            throw new DoorsRuntimeException();
        }
    }

    @Override
    public void close() {
        if (closed) {
            throw new DoorsRuntimeException("Module is closed.");
        }
        try {
            doorsApplicationImpl.buildAndRunCommand(builder -> {
                builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
                builder.addScript(new InternalDXLScript("close_module.dxl"));
                builder.setVariable("url", url != null ? url.getUrl() : null);
                builder.setVariable("name", name);
            });
        } catch (final DoorsException e) {
            // This should never happen, because the script never calls 'throw'.
            throw new DoorsRuntimeException();
        }
        closed = true;
    }

}
