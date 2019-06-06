/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.extensions;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.function.Predicate;

/**
 *
 * @author fwiesweg
 */
public interface ApplicationPartInterface {

    void executeCommand(final AbstractCommand command);

    DatabaseInterface getDatabaseInterface();

    void createSnapshot(Predicate<DoorsTreeNode> include);
    
}
