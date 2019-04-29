package de.jpwinkler.daf.doorscsv;

import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;

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
