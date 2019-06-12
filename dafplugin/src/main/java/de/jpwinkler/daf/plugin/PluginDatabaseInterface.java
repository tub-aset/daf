/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.plugin;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;

/**
 *
 * @author fwiesweg
 */
public class PluginDatabaseInterface implements DatabaseInterface {
    
    private DatabasePath<PluginDatabaseInterface> databasePath;
    private final DoorsFolder databaseRoot;

    public PluginDatabaseInterface(DatabasePath<PluginDatabaseInterface> databasePath, DatabaseInterface.OpenFlag openFlag) throws IOException {
        this.databasePath = databasePath;
        this.databaseRoot = DoorsModelUtil.createFolder(null, "Test plugin root folder");
        DoorsModelUtil.createFolder(databaseRoot, "Test plugin folder 1");
        DoorsModelUtil.createFolder(databaseRoot, "Test plugin folder 2");
    }

    @Override
    public DatabasePath<PluginDatabaseInterface> getPath() {
        return databasePath;
    }

    @Override
    public DoorsTreeNode getNode(String path) {
        if (path == null || path.isEmpty()) {
            return getDatabaseRoot();
        }
        return null;
    }

    @Override
    public DoorsFolder getDatabaseRoot() {
        return databaseRoot;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }
    
}
