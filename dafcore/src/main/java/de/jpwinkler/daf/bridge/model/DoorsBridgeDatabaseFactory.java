package de.jpwinkler.daf.bridge.model;

/*-
 * #%L
 * dafcore
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

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.db.DatabaseFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTableRow;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.UnresolvedLink;

/**
 *
 * @author fwiesweg
 */
public class DoorsBridgeDatabaseFactory extends DatabaseFactory {

    private final DoorsApplication doorsApplication;

    public DoorsBridgeDatabaseFactory(DoorsApplication doorsApplication) {
        this.doorsApplication = doorsApplication;
    }

    @Override
    public DoorsFolder createFolder(DoorsTreeNode parent, String name, boolean project) {
        return new DoorsFolderRefImpl(doorsApplication, parent, name, project);
    }

    @Override
    public DoorsModule createModule(DoorsTreeNode parent, String name) {
        return new DoorsModuleRefImpl(doorsApplication, parent, name);
    }

    @Override
    public DoorsObject createObject(DoorsTreeNode parent, String objectText) {
        return new DoorsObjectRefImpl(doorsApplication, parent);
    }
    
    @Override
    public DoorsTableRow createTableRow(DoorsTreeNode parent) {
        return new DoorsTableRowRefImpl(doorsApplication, parent);
    }

    @Override
    public UnresolvedLink createLink(DoorsObject source, String targetModule, String targetObject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
