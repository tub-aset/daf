package de.jpwinkler.daf.doorsdb.tasks;

import de.jpwinkler.daf.doorscsv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.doorscsv.model.DoorsFolder;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import java.util.function.Consumer;

public class FolderSource implements ModuleSource {

    private final String folder;

    public FolderSource(final String folder) {
        this.folder = folder;
    }

    @Override
    public void run(final DoorsDBInterface databaseInterface, final Consumer<DoorsModule> consumer) {
        final DoorsFolder f = databaseInterface.getFolder(folder);
        if (folder == null) {
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
