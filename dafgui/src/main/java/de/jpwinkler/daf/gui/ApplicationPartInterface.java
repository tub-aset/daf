package de.jpwinkler.daf.gui;

/*-
 * #%L
 * dafgui
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.db.DatabaseInterface;
import de.jpwinkler.daf.db.DatabaseInterface.OpenFlag;
import de.jpwinkler.daf.db.DatabasePath;
import de.jpwinkler.daf.gui.ApplicationPartFactoryRegistry.ApplicationPart;
import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import javafx.scene.control.SelectionModel;

/**
 *
 * @author fwiesweg
 */
public interface ApplicationPartInterface {

    void executeCommand(final AbstractCommand command);

    DatabaseInterface getDatabaseInterface();
    
    BackgroundTaskExecutor getBackgroundTaskExecutor();

    CompletableFuture<DatabasePath> createSnapshot(Predicate<DoorsTreeNode> include, DatabasePath destination);

    ApplicationPartInterface open(DatabasePath dbPath, OpenFlag openFlag);
    
    ApplicationPartInterface open(ApplicationPart part, OpenFlag openFlag);
    
    ApplicationPartInterface open(DoorsLink dl, OpenFlag openFlag);

    SelectionModel<DoorsFolder> getCurrentFolderSelectionModel();

    SelectionModel<DoorsModule> getCurrentModuleSelectionModel();

    SelectionModel<DoorsObject> getCurrentObjectSelectionModel();
    
    void selectLinkTarget(DoorsObject linkTarget);

}
