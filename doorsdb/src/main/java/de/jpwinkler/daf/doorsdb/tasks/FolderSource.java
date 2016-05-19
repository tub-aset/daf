package de.jpwinkler.daf.doorsdb.tasks;

import java.util.function.Consumer;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.search.DBSearchExpression;
import de.jpwinkler.daf.doorsdb.util.DoorsDBVisitor;

public class FolderSource implements ModuleSource {

    private final DBFolder folder;
    private final DBSearchExpression filter;

    public FolderSource(final DoorsDBInterface databaseInterface, final String folder) {
        this(databaseInterface, folder, null);
    }

    public FolderSource(final DoorsDBInterface databaseInterface, final String folder, final DBSearchExpression filter) {
        super();
        this.folder = databaseInterface.getFolder(folder);
        if (this.folder == null) {
            throw new IllegalArgumentException(folder + " does not exist.");
        }
        this.filter = filter;
    }

    @Override
    public void run(final Consumer<DBModule> consumer) {
        folder.accept(new DoorsDBVisitor() {

            @Override
            public void visit(final DBModule module) {
                if (filter == null || filter.matches(module)) {
                    consumer.accept(module);
                }
            }

            @Override
            public void visit(final DBFolder folder) {
            }
        });
    }

}
