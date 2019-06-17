/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.bridge.model;

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.db.DatabaseFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.UnresolvedLink;

/**
 *
 * @author fwiesweg
 */
public class DoorsBridgeDatabaseFactory implements DatabaseFactory {

    private final DoorsApplication doorsApplication;

    public DoorsBridgeDatabaseFactory(DoorsApplication doorsApplication) {
        this.doorsApplication = doorsApplication;
    }

    @Override
    public DoorsFolder createFolder(DoorsTreeNode parent, String name) {
        return new DoorsFolderRefImpl(doorsApplication, DoorsItemType.FOLDER, parent, name);
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
    public UnresolvedLink createLink(DoorsObject source, String targetModule, String targetObject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
