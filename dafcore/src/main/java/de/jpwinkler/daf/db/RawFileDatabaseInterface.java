/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author fwiesweg
 */
public class RawFileDatabaseInterface implements DatabaseInterface {

    private DoorsModule databaseRoot;
    private final DatabasePath<RawFileDatabaseInterface> databasePath;

    public RawFileDatabaseInterface(DatabasePath<RawFileDatabaseInterface> databasePath, OpenFlag openFlag) throws IOException {
        if (!databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must not have a path segment here");
        }

        this.databasePath = databasePath;

        File dbFile = new File(databasePath.getDatabasePath());
        databaseRoot = ModuleCSV.read(dbFile, openFlag);
    }

    @Override
    public DatabasePath<RawFileDatabaseInterface> getPath() {
        return databasePath;
    }

    @Override
    public void flush() throws IOException {
        ModuleCSV.write(new File(databasePath.getDatabasePath()), this.databaseRoot);
    }

    @Override
    public DoorsTreeNode getNode(String path) {
        if (path == null || path.isEmpty()) {
            return getDatabaseRoot();
        }

        return null;
    }

    @Override
    public DoorsModule getDatabaseRoot() {
        return databaseRoot;
    }
}
