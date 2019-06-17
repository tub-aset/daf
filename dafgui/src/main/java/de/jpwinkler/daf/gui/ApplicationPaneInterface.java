/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.function.Predicate;

/**
 *
 * @author fwiesweg
 */
public interface ApplicationPaneInterface {
    DatabasePath createSnapshot(DatabaseInterface sourceDB, DatabasePath sourcePath, Predicate<DoorsTreeNode> include, DatabasePath destinationPath);

    ApplicationPartInterface open(DatabasePath dbPath, DatabaseInterface.OpenFlag openFlag);
    
    BackgroundTaskMonitor getBackgroundTaskMonitor();
}
