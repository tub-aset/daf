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

import de.jpwinkler.daf.model.DoorsModule;
import java.io.File;
import java.io.IOException;
public class RawFileDatabaseInterface implements DatabaseInterface {

    private DoorsModule databaseRoot;
    private final DatabasePath databasePath;
    private final DatabaseFactory factory = new EmfDatabaseFactory();

    public RawFileDatabaseInterface(DatabasePath databasePath, OpenFlag openFlag) throws IOException {
        if (!databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must not have a path segment here");
        }

        this.databasePath = databasePath;

        File dbFile = new File(databasePath.getDatabasePath());
        databaseRoot = ModuleCSV.read(factory, dbFile, openFlag);
    }

    @Override
    public DatabasePath getPath() {
        return databasePath;
    }

    @Override
    public void flush() throws IOException {
        ModuleCSV.write(new File(databasePath.getDatabasePath()), this.databaseRoot);
    }

    @Override
    public DoorsModule getDatabaseRoot() {
        return databaseRoot;
    }
    
    @Override
    public DatabaseFactory getFactory() {
        return factory;
    }
}
