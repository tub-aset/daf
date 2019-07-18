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
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsLinkResolveException;
import de.jpwinkler.daf.model.DoorsLinkStatus;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsObject;

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
    private DoorsLinkStatus targetStatus = DoorsLinkStatus.UNRESOLVED;

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
    public DoorsObject resolve() throws DoorsLinkResolveException {
        if (targetStatus != DoorsLinkStatus.RESOLVED) {
            try {
                this.target = DoorsModelUtil.resolve(this);
                this.targetStatus = DoorsLinkStatus.RESOLVED;
            } catch (DoorsLinkResolveException ex) {
                this.targetStatus = DoorsLinkStatus.RESOLVE_FAILED;
                throw ex;
            }
        }
        return target;
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
    public DoorsLinkStatus getLinkStatus() {
        return targetStatus;
    }

}
