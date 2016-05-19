package de.jpwinkler.daf.doorsdb.tasks;

import java.util.function.Consumer;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public interface ModuleSource {

    void run(Consumer<DBModule> consumer);

}
