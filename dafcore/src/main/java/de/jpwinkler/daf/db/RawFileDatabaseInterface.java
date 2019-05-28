/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import de.jpwinkler.daf.filter.modules.SearchExpression;
import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsModule;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author fwiesweg
 */
public class RawFileDatabaseInterface implements DatabaseInterface {

    @Override
    public DoorsModule getNode(String path) {
        try {
            return ModuleCSV.read(new File(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public DoorsDatabase getDatabaseObject() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List<DoorsModule> getModules(SearchExpression e) {
        throw new UnsupportedOperationException("Not supported");
    }
}
