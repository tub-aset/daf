/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author fwiesweg
 */
public class RawFileDatabaseInterface implements DatabaseInterface {

    private final DoorsDatabase db = DoorsFactory.eINSTANCE.createDoorsDatabase();
    private final DatabasePath<RawFileDatabaseInterface> databasePath;

    public RawFileDatabaseInterface(DatabasePath<RawFileDatabaseInterface> databasePath) throws IOException {
        if (!databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must not have a path segment here");
        }

        this.databasePath = databasePath;
        db.setRoot(ModuleCSV.read(new File(databasePath.getDatabasePath())));
    }

    @Override
    public DatabasePath<RawFileDatabaseInterface> getPath() {
        return databasePath;
    }

    @Override
    public void flush() throws IOException {
        ModuleCSV.write(new File(databasePath.getDatabasePath()), (DoorsModule) this.db.getRoot());
    }

    @Override
    public DoorsTreeNode getNode(String path) {
        if (path == null || path.isEmpty()) {
            return getDatabaseObject().getRoot();
        }

        return null;
    }

    @Override
    public DoorsDatabase getDatabaseObject() {
        return db;
    }
}
