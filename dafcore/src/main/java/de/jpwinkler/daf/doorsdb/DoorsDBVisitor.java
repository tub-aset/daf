package de.jpwinkler.daf.doorsdb;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;

public interface DoorsDBVisitor {

    void visit(DBFolder folder);

    void visit(DBModule module);

}
