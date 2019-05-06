package de.jpwinkler.daf.model;

public abstract class DoorsTreeNodeVisitor {

    public boolean visitPreTraverse(final DoorsModule module) {
        return true;
    }

    public void visitPostTraverse(final DoorsModule module) {
    }

    public boolean visitPreTraverse(final DoorsObject object) {
        return true;
    }

    public void visitPostTraverse(final DoorsObject object) {

    }
}
