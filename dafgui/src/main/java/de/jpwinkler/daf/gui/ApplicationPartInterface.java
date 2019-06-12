/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.function.Predicate;
import javafx.scene.control.SelectionModel;

/**
 *
 * @author fwiesweg
 */
public interface ApplicationPartInterface {

    void executeCommand(final AbstractCommand command);

    DatabaseInterface getDatabaseInterface();

    DatabasePath createSnapshot(Predicate<DoorsTreeNode> include, DatabasePath destination);

    ApplicationPartInterface open(DatabasePath dbPath, OpenFlag openFlag);

    SelectionModel<DoorsFolder> getCurrentFolderSelectionModel();

    SelectionModel<DoorsModule> getCurrentModuleSelectionModel();

    SelectionModel<DoorsObject> getCurrentObjectSelectionModel();

}
