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

import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.Link;
import de.jpwinkler.daf.model.ResolvedLink;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author fwiesweg
 */
class FilteredDoorsObject extends FilteredDoorsTreeNode<DoorsObject> implements DoorsObject {

    FilteredDoorsObject(DoorsObject self, Predicate<DoorsTreeNode> filter) {
        super(self, filter);
    }

    @Override
    public String getObjectIdentifier() {
        return self.getObjectIdentifier();
    }

    @Override
    public void setObjectIdentifier(String value) {
        self.setObjectIdentifier(value);
    }

    @Override
    public int getObjectLevel() {
        return self.getObjectLevel();
    }

    @Override
    public void setObjectLevel(int value) {
        self.setObjectLevel(value);
    }

    @Override
    public String getObjectNumber() {
        return self.getObjectNumber();
    }

    @Override
    public void setObjectNumber(String value) {
        self.setObjectNumber(value);
    }

    @Override
    public int getAbsoluteNumber() {
        return self.getAbsoluteNumber();
    }

    @Override
    public void setAbsoluteNumber(int value) {
        self.setAbsoluteNumber(value);
    }

    @Override
    public String getObjectText() {
        return self.getObjectText();
    }

    @Override
    public void setObjectText(String value) {
        self.setObjectText(value);
    }

    @Override
    public String getObjectShortText() {
        return self.getObjectShortText();
    }

    @Override
    public void setObjectShortText(String value) {
        self.setObjectShortText(value);
    }

    @Override
    public String getObjectHeading() {
        return self.getObjectHeading();
    }

    @Override
    public void setObjectHeading(String value) {
        self.setObjectHeading(value);
    }

    @Override
    public String getText() {
        return self.getText();
    }

    @Override
    public void setText(String value) {
        self.setText(value);;
    }

    @Override
    public List<Link> getOutgoingLinks() {
        return self.getOutgoingLinks();
    }

    @Override
    public List<ResolvedLink> getIncomingLinks() {
        return self.getIncomingLinks();
    }

    @Override
    public boolean isHeading() {
        return self.isHeading();
    }
    
}
