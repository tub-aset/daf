package de.jpwinkler.daf.doorsdb.tasks;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.search.AndSearchExpression;
import de.jpwinkler.daf.doorsdb.search.DBSearchExpression;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModuleTaskBuilder {

    private final List<ModulePass> passes = new ArrayList<>();
    private final List<ModuleSource> sources = new ArrayList<>();
    private final List<DBSearchExpression> filters = new ArrayList<>();

    private final DoorsDBInterface databaseInterface;
    private boolean parallel = false;

    public ModuleTaskBuilder() throws FileNotFoundException, IOException {
        this(DoorsDBInterface.getDefaultDatabase());
    }

    public ModuleTaskBuilder(final DoorsDBInterface databaseInterface) {
        super();
        this.databaseInterface = databaseInterface;
    }

    public ModuleTaskBuilder withSource(final ModuleSource source) {
        sources.add(source);
        return this;
    }

    public ModuleTaskBuilder withFilter(final DBSearchExpression filter) {
        filters.add(filter);
        return this;
    }

    public ModuleTaskBuilder withPass(final ModulePass pass) {
        passes.add(pass);
        return this;
    }

    public ModuleTaskBuilder parallel() {
        parallel = true;
        return this;
    }

    public ModuleTask build() {
        return new ModuleTask(databaseInterface, passes, sources, new AndSearchExpression(filters), parallel);
    }

    public void buildAndRun() {
        build().execute();
    }

}
