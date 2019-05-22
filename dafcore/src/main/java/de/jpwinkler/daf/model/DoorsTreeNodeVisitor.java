package de.jpwinkler.daf.model;

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

    protected boolean visitPreTraverse(final T object) {
        return true;
    }

    protected void visitPostTraverse(final T object) {

    }

}
