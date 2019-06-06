/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui.databases.commands;

import de.jpwinkler.daf.gui.extensions.AbstractCommand;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 *
 * @author fwiesweg
 */
public abstract class AttributesCommand extends AbstractCommand {

    protected final Collection<Entry<String, String>> attributes;
    protected List<Entry<String, String>> oldAttributes;
    protected final DoorsTreeNode treeNode;

    public AttributesCommand(Collection<Entry<String, String>> attributes, DoorsTreeNode treeNode) {
        this.attributes = attributes;
        this.treeNode = treeNode;
    }

    @Override
    public boolean isApplicable() {
        return this.attributes != null && !this.attributes.isEmpty() && treeNode != null;
    }

    @Override
    public final void apply() {
        this.oldAttributes = treeNode.getAttributes().entrySet().stream().collect(Collectors.toList());
        redo();
    }

    @Override
    public final void undo() {
        treeNode.getAttributes().clear();
        oldAttributes.forEach(e -> treeNode.getAttributes().put(e.getKey(), e.getValue()));
    }

}
