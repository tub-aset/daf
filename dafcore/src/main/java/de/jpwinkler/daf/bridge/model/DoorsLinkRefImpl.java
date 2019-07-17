/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import de.jpwinkler.daf.filter.model.FilteredDoorsTreeNode;
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Objects;

/**
 *
 * @author fwiesweg
 */
public class DoorsLinkRefImpl implements DoorsLink {

    public DoorsLinkRefImpl(DoorsObject source, String targetModule, String targetObject) {
        this.source = source;
        this.targetModule = targetModule;
        this.targetObject = targetObject;
    }

    private final DoorsObject source;
    private DoorsObject target;

    private final String targetModule;
    private final String targetObject;

    @Override
    public DoorsObject getSource() {
        return source;
    }

    @Override
    public void setSource(DoorsObject value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public DoorsObject getTarget() {
        if (this.isResolved()) {
            return target;
        }

        DoorsTreeNode root = source;
        while (root.getParent() != null) {
            root = root.getParent();
        }

        DoorsTreeNode pathTreeNode = root.getChild(targetModule);
        if (!(pathTreeNode instanceof DoorsModule)) {
            throw new IllegalStateException("Target module is not a DoorsModule");
        }

        DoorsModule pathModule = (DoorsModule) FilteredDoorsTreeNode.createFilteredTree(pathTreeNode,
                tn -> Objects.equals(targetObject, tn.getAttributes().get("Absolute Number")), true);

        if (pathModule.getChildren().size() != 1) {
            throw new IllegalStateException("Target object points to more than one DoorsTreeNode");
        }

        DoorsTreeNode targetObject = pathModule.getChildren().get(0);
        if (!(targetObject instanceof DoorsObject)) {
            throw new IllegalStateException("Target object is no DoorsObject");
        }

        this.target = (DoorsObject) targetObject;
        ((DoorsObject) targetObject).getIncomingLinks().add(this);
        return this.target;
    }

    @Override
    public void setTarget(DoorsObject value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public String getTargetModule() {
        return targetModule;
    }

    @Override
    public void setTargetModule(String value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public String getTargetObject() {
        return targetObject;
    }

    @Override
    public void setTargetObject(String value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean isResolved() {
        return target != null;
    }

}
