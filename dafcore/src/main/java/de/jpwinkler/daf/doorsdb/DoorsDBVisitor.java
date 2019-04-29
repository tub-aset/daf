package de.jpwinkler.daf.doorsdb;

import de.jpwinkler.daf.doorsdb.model.DBFolder;
import de.jpwinkler.daf.doorsdb.model.DBModule;

public interface DoorsDBVisitor {

    void visit(DBFolder folder);

    void visit(DBModule module);

}
