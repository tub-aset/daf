/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.extensions;

import de.jpwinkler.daf.db.DatabaseInterface;
import java.util.Collection;

/**
 *
 * @author fwiesweg
 */
public interface ModuleViewsExtension extends ApplicationPartExtension {

    Collection<ViewDefinition> getAdditionalViews(DatabaseInterface databaseInterface);
}
