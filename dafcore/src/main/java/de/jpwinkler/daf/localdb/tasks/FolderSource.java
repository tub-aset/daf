package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.localdb.DatabaseInterface;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.util.function.Consumer;

public class FolderSource implements ModuleSource {

    private final String folder;

    public FolderSource(final String folder) {
        this.folder = folder;
    }

    @Override
    public void run(final DatabaseInterface databaseInterface, final Consumer<DoorsModule> consumer) {
        final DoorsTreeNode f = databaseInterface.getNode(folder);
        if (f == null || f instanceof DoorsModule) {
            throw new IllegalArgumentException(folder + " does not exist.");
        }
        f.accept(new DoorsTreeNodeVisitor() {

            @Override
            public void visitPostTraverse(final DoorsModule module) {
                consumer.accept(module);
            }
        });
    }

}
