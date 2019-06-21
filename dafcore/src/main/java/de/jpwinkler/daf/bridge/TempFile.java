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

/**
 *
 * @author fwiesweg
 */
public final class TempFile extends File implements AutoCloseable {

    private static final String PREFIX = "doorsbridge";

    public TempFile(File file) {
        super(file.getAbsolutePath());
        this.deleteOnExit();
    }

    public TempFile() throws IOException {
        this(File.createTempFile(PREFIX, null));
    }

    public TempFile(String prefix, String suffix) throws IOException {
        this(File.createTempFile(PREFIX, suffix));
    }

    public TempFile(String prefix, String suffix, File directory) throws IOException {
        this(File.createTempFile(PREFIX, suffix, directory));
    }

    @Override
    public void close() {
        this.delete();
    }

}
