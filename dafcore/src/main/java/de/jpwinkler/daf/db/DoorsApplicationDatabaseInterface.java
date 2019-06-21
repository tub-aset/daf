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

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsApplicationImpl;
import de.jpwinkler.daf.model.DoorsFolder;

/**
 *
 * @author fwiesweg
 */
public class DoorsApplicationDatabaseInterface implements DatabaseInterface {

    private final DoorsApplication doorsApplication = new DoorsApplicationImpl();
    private final DoorsFolder root;
    private final DatabasePath<DoorsApplicationDatabaseInterface> databasePath;

    public DoorsApplicationDatabaseInterface(DatabasePath<DoorsApplicationDatabaseInterface> databasePath, OpenFlag openFlag) {
        if (!databasePath.getDatabasePath().isEmpty() || !databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must be fully empty for the doors bridge");
        }
        if (openFlag != OpenFlag.OPEN_ONLY) {
            throw new IllegalArgumentException("Only OpenFlag.OPEN_ONLY is allowed");
        }

        this.root = doorsApplication.getDatabaseFactory().createFolder(null, "Doors Application");
        this.databasePath = databasePath;
    }

    @Override
    public DatabasePath getPath() {
        return databasePath;
    }

    @Override
    public DoorsFolder getDatabaseRoot() {
        return root;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public DatabaseFactory getFactory() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void close() {
        this.doorsApplication.close();
    }
}
