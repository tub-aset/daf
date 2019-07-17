package de.jpwinkler.daf.plugin;

/*-
 * #%L
 * dafplugin
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

import de.jpwinkler.daf.db.DatabaseFactory;
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.db.EmfDatabaseFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import java.io.IOException;

/**
 *
 * @author fwiesweg
 */
public class PluginDatabaseInterface implements DatabaseInterface {

    private DatabasePath databasePath;
    private final DoorsFolder databaseRoot;

    public PluginDatabaseInterface(DatabasePath databasePath, DatabaseInterface.OpenFlag openFlag) throws IOException {
        this.databasePath = databasePath;
        DatabaseFactory factory = new EmfDatabaseFactory();
        this.databaseRoot = factory.createFolder(null, "Test plugin root folder", false);
        factory.createFolder(databaseRoot, "Test plugin folder 1", false);
        factory.createFolder(databaseRoot, "Test plugin folder 2", false);
    }

    @Override
    public DatabasePath getPath() {
        return databasePath;
    }

    @Override
    public DoorsFolder getDatabaseRoot() {
        return databaseRoot;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public DatabaseFactory getFactory() {
        return null;
    }

}
