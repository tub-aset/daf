package de.jpwinkler.daf.dataprocessing.preprocessing;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.DoorsModuleOperation;

public class DoorsModulePreprocessor extends DoorsModuleOperation {

    private final List<DoorsObjectPreprocessor> objectPreprocessors = new ArrayList<>();

    public void addObjectPreprocessor(final DoorsObjectPreprocessor objectPreprocessor) {
        objectPreprocessors.add(objectPreprocessor);
    }
    @Override
    protected DoorsModule apply(final DoorsModule module) {
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                for (final DoorsObjectPreprocessor objectPreprocessor : objectPreprocessors) {
                    objectPreprocessor.preprocessObject(object);
                }
                return true;
            }
        });
        return module;
    }

}
