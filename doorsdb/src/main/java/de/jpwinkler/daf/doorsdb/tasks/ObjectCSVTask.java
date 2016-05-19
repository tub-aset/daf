package de.jpwinkler.daf.doorsdb.tasks;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;

public abstract class ObjectCSVTask extends ModuleCSVTask {

    public ObjectCSVTask(final DoorsDBInterface databaseInterface) {
        super(databaseInterface);
    }

    public ObjectCSVTask(final DoorsDBInterface databaseInterface, final ModuleSource source) {
        super(databaseInterface, source);
    }

    @Override
    protected void processParsedModule() {
        getParsedModule().accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                processObject(object);
                return true;
            }
        });
    }

    protected abstract void processObject(DoorsObject object);

}
