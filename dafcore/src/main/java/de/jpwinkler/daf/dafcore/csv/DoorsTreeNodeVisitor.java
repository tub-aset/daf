package de.jpwinkler.daf.dafcore.csv;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

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
