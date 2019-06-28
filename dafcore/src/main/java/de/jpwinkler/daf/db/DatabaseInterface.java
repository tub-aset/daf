package de.jpwinkler.daf.db;

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
import java.io.IOException;

/**
 *
 * @author fwiesweg
 */
public interface DatabaseInterface {

    public static enum OpenFlag {
        CREATE_IF_INEXISTENT,
        ERASE_IF_EXISTS,
        OPEN_ONLY
    }

    DatabasePath getPath();

    default void flush() throws IOException {
        throw new UnsupportedOperationException("Not supported");
    }

    default boolean isReadOnly() {
        return false;
    }

    DoorsTreeNode getDatabaseRoot();

    DatabaseFactory getFactory();

    default void close() {
    }
}
