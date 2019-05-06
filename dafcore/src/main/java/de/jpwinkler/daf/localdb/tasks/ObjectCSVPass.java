package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;

public abstract class ObjectCSVPass extends ModuleCSVPass {

    @Override
    protected void processParsedModule(final DoorsModule module) {
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                processObject(object);
                return true;
            }
        });
    }

    abstract protected void processObject(DoorsObject object);

}
