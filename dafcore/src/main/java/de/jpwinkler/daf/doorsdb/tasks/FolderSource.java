package de.jpwinkler.daf.doorsdb.tasks;

import java.util.function.Consumer;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.model.DBFolder;
import de.jpwinkler.daf.doorsdb.model.DBModule;
import de.jpwinkler.daf.doorsdb.DoorsDBVisitor;

public class FolderSource implements ModuleSource {

    private final String folder;

    public FolderSource(final String folder) {
        this.folder = folder;
    }

    @Override
    public void run(final DoorsDBInterface databaseInterface, final Consumer<DBModule> consumer) {
        final DBFolder f = databaseInterface.getFolder(folder);
        if (folder == null) {
            throw new IllegalArgumentException(folder + " does not exist.");
        }
        f.accept(new DoorsDBVisitor() {

            @Override
            public void visit(final DBModule module) {
                consumer.accept(module);
            }

            @Override
            public void visit(final DBFolder folder) {
            }
        });
    }

}
