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
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class RawFileDatabaseInterface implements DatabaseInterface {

    private CompletableFuture<DoorsModule> databaseRoot;
    private final DatabasePath databasePath;
    private final DatabaseFactory factory = new EmfDatabaseFactory();

    public RawFileDatabaseInterface(BackgroundTaskExecutor executor, DatabasePath databasePath, OpenFlag openFlag) throws IOException {
        if (!databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must not have a path segment here");
        }

        this.databasePath = databasePath;
        databaseRoot = executor.runBackgroundTask("Load raw file database", i -> {
            File dbFile = new File(databasePath.getDatabasePath());
            try {
                return ModuleCSV.read(factory, dbFile, openFlag);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    public DatabasePath getPath() {
        return databasePath;
    }

    @Override
    public CompletableFuture<Void> flushAsync() {
        return this.databaseRoot.thenAccept(root -> {
            try {
                ModuleCSV.write(new File(databasePath.getDatabasePath()), root);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    public CompletableFuture<DoorsModule> getDatabaseRootAsync() {
        return databaseRoot;
    }
    
    @Override
    public Class<? extends DoorsTreeNode> getDatabaseRootClass() {
        return DoorsModule.class;
    }
    

    @Override
    public DatabaseFactory getFactory() {
        return factory;
    }
}
