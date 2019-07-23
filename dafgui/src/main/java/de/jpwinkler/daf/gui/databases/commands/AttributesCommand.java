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

import de.jpwinkler.daf.gui.commands.AbstractCommand;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author fwiesweg
 */
public abstract class AttributesCommand extends AbstractCommand {

    protected final List<Pair<String, String>> attributes;
    protected List<Pair<String, String>> oldAttributes;
    protected final DoorsTreeNode treeNode;

    public AttributesCommand(Collection<Entry<String, String>> attributes, DoorsTreeNode treeNode) {
        this.attributes = attributes.stream().map(e -> Pair.of(e.getKey(), e.getValue())).collect(Collectors.toList());
        this.treeNode = treeNode;
    }

    @Override
    public boolean isApplicable() {
        return this.attributes != null && !this.attributes.isEmpty() && treeNode != null;
    }

    @Override
    public final void apply() {
        this.oldAttributes = treeNode.getAttributes().entrySet().stream().map(e -> Pair.of(e.getKey(), e.getValue())).collect(Collectors.toList());
        redo();
    }

    @Override
    public final void undo() {
        treeNode.getAttributes().clear();
        oldAttributes.forEach(e -> treeNode.getAttributes().put(e.getKey(), e.getValue()));
    }

}
