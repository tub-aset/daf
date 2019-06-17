/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.bridge.internal;

import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.bridge.DoorsTreeNodeRef;
import de.jpwinkler.daf.model.DoorsFolder;

/**
 *
 * @author fwiesweg
 */
public class DoorsFolderRefImpl extends DoorsTreeNodeRefImpl implements DoorsFolder {

    public DoorsFolderRefImpl(DoorsApplicationImpl doorsApplicationImpl, DoorsItemType type, DoorsTreeNodeRef parent, String name) {
        super(doorsApplicationImpl, type, parent, name);
    }

}
