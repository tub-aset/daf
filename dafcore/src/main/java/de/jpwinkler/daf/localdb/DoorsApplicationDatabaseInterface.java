/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.localdb;

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.search.SearchExpression;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fwiesweg
 */
public class DoorsApplicationDatabaseInterface implements DatabaseInterface {
    
    private final DoorsDatabase db;
    
    private DoorsApplicationDatabaseInterface(DoorsApplication doorsApplication) {
        this.db = DoorsFactory.eINSTANCE.createDoorsDatabase();
        this.db.setRoot(doorsApplication.getRoot());
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public DoorsDatabase getDatabaseObject() {
        return db;
    }

    @Override
    public List<DoorsModule> getModules(SearchExpression e) {
        final List<DoorsModule> result = new ArrayList<>();
        db.getRoot().accept(new DoorsTreeNodeVisitor<>(DoorsModule.class) {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                if (e.matches(module)) {
                    result.add(module);
                }
            }
        });
        return result;
    }

    @Override
    public DoorsModule importModule(DoorsModule module) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void removeNode(DoorsTreeNode node) {
        throw new UnsupportedOperationException("Not supported");
    }


}
