/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.bridge.model;

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.bridge.DoorsTreeNodeRef;
import de.jpwinkler.daf.model.DoorsFolder;

/**
 *
 * @author fwiesweg
 */
class DoorsFolderRefImpl extends DoorsTreeNodeRefImpl implements DoorsFolder {

    public DoorsFolderRefImpl(DoorsApplication doorsApplicationImpl, DoorsItemType type, DoorsTreeNodeRef parent, String name) {
        super(doorsApplicationImpl, type, parent, name);
    }

}
