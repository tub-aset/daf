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
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.impl.DoorsFolderImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class XmiDatabaseInterface implements DatabaseInterface {

    private final CompletableFuture<DoorsFolder> databaseRoot;
    private final DatabasePath databasePath;
    private final DatabaseFactory factory = new EmfDatabaseFactory();

    public XmiDatabaseInterface(BackgroundTaskExecutor executor, DatabasePath databasePath, OpenFlag openFlag) throws IOException {
        if (!databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must not have a path segment here");
        }
        this.databasePath = databasePath;

        File databaseFile = new File(databasePath.getDatabasePath());
        if (openFlag == OpenFlag.CREATE_IF_INEXISTENT && !databaseFile.exists()) {
            this.databaseRoot = CompletableFuture.completedFuture(factory.createFolder(null, FilenameUtils.getBaseName(databaseFile.getAbsolutePath()), false));
            try {
                this.flushAsync().get();
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        } else if (openFlag == OpenFlag.ERASE_IF_EXISTS) {
            if (databaseFile.exists()) {
                databaseFile.delete();
            }
            this.databaseRoot = CompletableFuture.completedFuture(factory.createFolder(null, FilenameUtils.getBaseName(databaseFile.getAbsolutePath()), false));
            try {
                this.flushAsync().get();
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        } else if (openFlag == OpenFlag.OPEN_ONLY && databaseFile.isFile()) {
            this.databaseRoot = executor.runBackgroundTask("Loading XMI database", i -> {
                final Resource resource = new ResourceSetImpl().getResource(URI.createFileURI(new File(databasePath.getDatabasePath()).toString()), true);
                return (DoorsFolder) resource.getContents().get(0);
            });
        } else {
            throw new FileNotFoundException(databaseFile.getAbsolutePath());
        }
    }

    @Override
    public final CompletableFuture<Void> flushAsync() {
        return databaseRoot.thenRun(() -> {
            final Resource resource = new ResourceSetImpl().createResource(URI.createFileURI(new File(databasePath.getDatabasePath()).getAbsolutePath()));
            try {
                resource.getContents().add((DoorsFolderImpl) databaseRoot.get());
                resource.save(Collections.emptyMap());
            } catch (InterruptedException | ExecutionException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    public DatabasePath getPath() {
        return databasePath;
    }

    @Override
    public CompletableFuture<DoorsFolder> getDatabaseRootAsync() {
        return databaseRoot;
    }

    @Override
    public Class<? extends DoorsTreeNode> getDatabaseRootClass() {
        return DoorsFolder.class;
    }

    @Override
    public DatabaseFactory getFactory() {
        return factory;
    }
}
