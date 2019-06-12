/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import de.jpwinkler.daf.filter.modules.SearchExpression;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fwiesweg
 */
public interface DatabaseInterface {
    
    public static enum OpenFlag {
        CREATE_IF_INEXISTENT,
        ERASE_IF_EXISTS,
        OPEN_ONLY
    }

    DatabasePath getPath();

    default void flush() throws IOException {
        throw new UnsupportedOperationException("Not supported");
    }

    default DoorsModule importModule(final DoorsModule module) {
        throw new UnsupportedOperationException("Not supported");
    }

    default DoorsTreeNode getNode(String path) {
        return this.getDatabaseRoot().getChild(path);
    }
    
    default boolean isReadOnly() {
        return false;
    }

    default List<DoorsModule> getModules(final SearchExpression e) {
        final List<DoorsModule> result = new ArrayList<>();
        getDatabaseRoot().accept(new DoorsTreeNodeVisitor<>(DoorsModule.class) {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                if (e.matches(module)) {
                    result.add(module);
                }
            }
        });
        return result;
    }

    DoorsTreeNode getDatabaseRoot();
}
