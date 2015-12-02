package de.jpwinkler.daf.dataprocessing.preprocessing;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.DoorsModuleOperation;

public abstract class DoorsObjectPreprocessor extends DoorsModuleOperation {

    public abstract void preprocessObject(DoorsObject object);

    @Override
    protected DoorsModule apply(final DoorsModule module) {
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                preprocessObject(object);
                return true;
            }
        });
        return module;
    }

}
