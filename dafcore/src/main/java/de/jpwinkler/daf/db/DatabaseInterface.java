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
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fwiesweg
 */
public interface DatabaseInterface {

    default String getPath() {
        return null;
    }

    default void setPath(String path) {
        throw new UnsupportedOperationException("Not supported");
    }

    default void flush() throws IOException {
        throw new UnsupportedOperationException("Not supported");
    }

    default DoorsModule importModule(final DoorsModule module) {
        throw new UnsupportedOperationException("Not supported");
    }

    default void removeNode(DoorsTreeNode node) {
        throw new UnsupportedOperationException("Not supported");
    }

    default DoorsTreeNode getNode(String path) {
        DoorsTreeNode node = this.getDatabaseObject().getRoot();
        for (String name : path.split("/")) {
            node = node.getChild(name);
            if (node == null) {
                break;
            }
        }

        return node;
    }

    default List<DoorsModule> getModules(final SearchExpression e) {
        final List<DoorsModule> result = new ArrayList<>();
        getDatabaseObject().getRoot().accept(new DoorsTreeNodeVisitor<>(DoorsModule.class) {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                if (e.matches(module)) {
                    result.add(module);
                }
            }
        });
        return result;
    }

    DoorsDatabase getDatabaseObject();
}
