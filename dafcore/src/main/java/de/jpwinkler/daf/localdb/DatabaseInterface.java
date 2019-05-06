/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.localdb;

import de.jpwinkler.daf.model.DoorsDB;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.search.SearchExpression;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author fwiesweg
 */
public interface DatabaseInterface {

    DoorsModule importModule(final DoorsModule module);

    void addTag(DoorsModule module, String value);

    List<DoorsModule> findModules(final SearchExpression e);

    void flush() throws IOException;

    List<DoorsModule> getAllModules();

    DoorsDB getDatabaseObject();

    DoorsFolder getFolder(final String path);

    DoorsModule getModule(final String path);

    Collection<String> getTags(DoorsModule doorsModule);

    Collection<String> getTags();

    void removeFolder(final DoorsFolder folder);

    void removeModule(final DoorsModule module);

    void removeTag(DoorsModule module, String tag);    
}
