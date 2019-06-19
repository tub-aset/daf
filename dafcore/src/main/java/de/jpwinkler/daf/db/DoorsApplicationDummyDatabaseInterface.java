/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsApplicationDummyImpl;
import de.jpwinkler.daf.model.DoorsFolder;

/**
 *
 * @author fwiesweg
 */
public class DoorsApplicationDummyDatabaseInterface implements DatabaseInterface {

    private final DoorsApplication doorsApplication = new DoorsApplicationDummyImpl();
    private final DoorsFolder root;
    private final DatabasePath<DoorsApplicationDummyDatabaseInterface> databasePath;

    public DoorsApplicationDummyDatabaseInterface(DatabasePath<DoorsApplicationDummyDatabaseInterface> databasePath, OpenFlag openFlag) {
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
