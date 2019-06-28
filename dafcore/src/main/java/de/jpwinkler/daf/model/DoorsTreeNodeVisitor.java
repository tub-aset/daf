package de.jpwinkler.daf.model;

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

public abstract class DoorsTreeNodeVisitor<T extends DoorsTreeNode> {
    public DoorsTreeNodeVisitor() {
        this.visitedNodeCls = null;
    }

    public DoorsTreeNodeVisitor(Class<T> visited) {
        this.visitedNodeCls = visited;
    }

    private final Class<T> visitedNodeCls;

    public final void traverse(final DoorsTreeNode node) {
        boolean classMatch = visitedNodeCls == null || visitedNodeCls.isAssignableFrom(node.getClass());

        if (!classMatch || visitPreTraverse((T) node)) {
            for (final DoorsTreeNode child : node.getChildren()) {
                child.accept(this);
            }
        }

        if (classMatch) {
            visitPostTraverse((T) node);
        }
    }

    public boolean visitPreTraverse(final T object) {
        return true;
    }

    public void visitPostTraverse(final T object) {

    }

}
