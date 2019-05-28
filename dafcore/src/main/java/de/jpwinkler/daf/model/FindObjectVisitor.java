package de.jpwinkler.daf.model;

public class FindObjectVisitor extends DoorsTreeNodeVisitor<DoorsObject> {

    private final String objectIdentifier;

    private DoorsObject object;

    public FindObjectVisitor(final String objectIdentifier) {
        super(DoorsObject.class);
        this.objectIdentifier = objectIdentifier;
    }

    @Override
    public boolean visitPreTraverse(final DoorsObject object) {
        if (object.getObjectIdentifier() != null && object.getObjectIdentifier().equals(objectIdentifier)) {
            this.object = object;
            return false;
        } else {
            return true;
        }
    }

    public DoorsObject getObject() {
        return object;
    }
}
