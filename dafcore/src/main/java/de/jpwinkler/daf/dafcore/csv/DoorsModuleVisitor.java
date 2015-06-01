package de.jpwinkler.daf.dafcore.csv;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public interface DoorsModuleVisitor {

    /**
     * Called for each object in the module, before processing child objects.
     *
     * @param object
     * @return true, when child objects should be visited as well
     */
    public boolean visitPreTraverse(DoorsObject object);

    /**
     * Called for each object in the module, after processing child objects.
     * This method is called, even if visitPreTraverse for an object returned
     * false.
     *
     * @param object
     */
    default public void visitPostTraverse(final DoorsObject object) {
    }

}
