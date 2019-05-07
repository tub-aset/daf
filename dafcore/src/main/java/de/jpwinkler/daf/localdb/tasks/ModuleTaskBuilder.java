package de.jpwinkler.daf.localdb.tasks;

import de.jpwinkler.daf.localdb.DatabaseInterface;
import de.jpwinkler.daf.search.AndSearchExpression;
import de.jpwinkler.daf.search.SearchExpression;
import java.util.ArrayList;
import java.util.List;

public class ModuleTaskBuilder {

    private final List<ModulePass> passes = new ArrayList<>();
    private final List<ModuleSource> sources = new ArrayList<>();
    private final List<SearchExpression> filters = new ArrayList<>();

    private final DatabaseInterface databaseInterface;
    private boolean parallel = false;

    public ModuleTaskBuilder(final DatabaseInterface databaseInterface) {
        super();
        this.databaseInterface = databaseInterface;
    }

    public ModuleTaskBuilder withSource(final ModuleSource source) {
        sources.add(source);
        return this;
    }

    public ModuleTaskBuilder withFilter(final SearchExpression filter) {
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
