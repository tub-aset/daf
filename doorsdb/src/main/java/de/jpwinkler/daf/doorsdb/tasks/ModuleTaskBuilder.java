package de.jpwinkler.daf.doorsdb.tasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.search.DBSearchExpression;

public class ModuleTaskBuilder {

    private final List<ModulePass> passes = new ArrayList<>();
    private ModuleSource source = new AllModulesSource();
    private DBSearchExpression filter;

    private final DoorsDBInterface databaseInterface;

    public ModuleTaskBuilder() throws FileNotFoundException, IOException {
        this(DoorsDBInterface.getDefaultDatabase());
    }

    public ModuleTaskBuilder(final DoorsDBInterface databaseInterface) {
        super();
        this.databaseInterface = databaseInterface;
    }

    public ModuleTaskBuilder withSource(final ModuleSource source) {
        this.source = source;
        return this;
    }

    public ModuleTaskBuilder withFilter(final DBSearchExpression filter) {
        this.filter = filter;
        return this;
    }

    public ModuleTaskBuilder withPass(final ModulePass pass) {
        passes.add(pass);
        return this;
    }

    public ModuleTask build() {
        return new ModuleTask(databaseInterface, passes, source, filter);
    }

    public void buildAndRun() {
        build().execute();
    }

}
