/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases.commands;

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

import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fwiesweg
 */
public class NewAttributesCommand extends EditAttributesCommand {

    public NewAttributesCommand(String key, String newValue, DoorsTreeNode treeNode) {
        super(key, newValue, treeNode);
    }

    public NewAttributesCommand(List<Map.Entry<String, String>> attributes, DoorsTreeNode treeNode) {
        super(attributes, treeNode);
    }

    @Override
    public boolean isApplicable() {
        return getNotApplicableReason() != null;
    }

    @Override
    public String getNotApplicableReason() {
        if (!super.isApplicable()) {
            return "Command is not applicable";
        }

        return super.attributes.stream()
                .map(e -> e.getKey())
                .filter(treeNode.getAttributes()::containsKey)
                .findFirst()
                .map(e -> "Attribute \"" + e + "\"q does already exist on this node")
                .orElse(null);
    }

}
