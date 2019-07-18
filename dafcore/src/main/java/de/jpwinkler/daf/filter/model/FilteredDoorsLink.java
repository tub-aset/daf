/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.filter.model;

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
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsLinkResolveException;
import de.jpwinkler.daf.model.DoorsLinkStatus;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.WeakHashMap;
import java.util.function.Predicate;

/**
 *
 * @author fwiesweg
 */
public class FilteredDoorsLink implements DoorsLink {

    private final DoorsLink self;
    private final Predicate<DoorsTreeNode> predicate;
    private final WeakHashMap<DoorsTreeNode, FilteredDoorsTreeNode<?>> nodeMap;

    public FilteredDoorsLink(DoorsLink self, Predicate<DoorsTreeNode> predicate, WeakHashMap<DoorsTreeNode, FilteredDoorsTreeNode<?>> nodeMap) {
        this.self = self;
        this.predicate = predicate;
        this.nodeMap = nodeMap;
    }

    @Override
    public DoorsObject getSource() {
        return (DoorsObject) ForwardingChildrenList.wrap(nodeMap, predicate, self.getSource());
    }

    @Override
    public void setSource(DoorsObject value) {
        self.setSource(ForwardingChildrenList.unwrap(nodeMap, value));
    }

    @Override
    public DoorsObject resolve() throws DoorsLinkResolveException {
        DoorsObject target = self.resolve();
        if (!predicate.test(target)) {
            return null;
        }

        return (DoorsObject) ForwardingChildrenList.wrap(nodeMap, predicate, target);
    }

    @Override
    public String getTargetModule() {
        return self.getTargetModule();
    }

    @Override
    public void setTargetModule(String value) {
        self.setTargetModule(value);
    }

    @Override
    public String getTargetObject() {
        return self.getTargetObject();
    }

    @Override
    public void setTargetObject(String value) {
        self.setTargetObject(value);
    }

    @Override
    public DoorsLinkStatus getLinkStatus() {
        DoorsLinkStatus status = self.getLinkStatus();
        if (status == DoorsLinkStatus.RESOLVED) {
            DoorsObject linkTarget;
            try {
                linkTarget = self.resolve();
            } catch (DoorsLinkResolveException ex) {
                throw new AssertionError(ex);
            }

            return predicate.test(linkTarget) ? DoorsLinkStatus.RESOLVED : DoorsLinkStatus.RESOLVE_FAILED;
        } else {
            return status;
        }
    }

}
