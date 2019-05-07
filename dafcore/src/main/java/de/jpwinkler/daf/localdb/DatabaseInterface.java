/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.localdb;

import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.search.SearchExpression;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author fwiesweg
 */
public interface DatabaseInterface {

    void flush() throws IOException;

    DoorsDatabase getDatabaseObject();
    
    

    DoorsModule getModule(final String path);
    
    List<DoorsModule> getModules();
    
    List<DoorsModule> getModules(final SearchExpression e);
    
    DoorsModule importModule(final DoorsModule module);
    
    void removeModule(final DoorsModule module);
    
    
    
    DoorsFolder getFolder(final String path);
    
    void removeFolder(final DoorsFolder folder);
    

    
    Collection<String> getTags();
    
    Collection<String> getTags(DoorsModule doorsModule);
    
    void addTag(DoorsModule module, String value);
    
    void removeTag(DoorsModule module, String tag);    
}
