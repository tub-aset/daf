/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import de.jpwinkler.daf.filter.modules.SearchExpression;
import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author fwiesweg
 */
public interface DatabaseInterface {

    default void flush() throws IOException {
        if (isReadOnly()) {
            throw new UnsupportedOperationException("Not supported");
        }
    }
    
    default String getPath() {
        return null;
    }
    
    default void setPath(String path) {
        throw new UnsupportedOperationException("Not supported");
    }

    DoorsDatabase getDatabaseObject();

    List<DoorsModule> getModules(final SearchExpression e);

    default DoorsModule importModule(final DoorsModule module) {
        throw new UnsupportedOperationException("Not supported");
    }

    default void removeNode(DoorsTreeNode node) {
        throw new UnsupportedOperationException("Not supported");
    }

    boolean isReadOnly();

    public static DatabaseInterface openFileDatabase(Path path) throws IOException {
        return new FileDatabaseInterface(path);
    }

    public static DatabaseInterface openDoorsApplicationDatabase() throws IOException {
        return new DoorsApplicationDatabaseInterface();
    }
}
