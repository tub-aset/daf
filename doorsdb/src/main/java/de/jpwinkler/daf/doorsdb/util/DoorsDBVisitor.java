package de.jpwinkler.daf.doorsdb.util;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion;

public interface DoorsDBVisitor {

    void visit(DBFolder folder);

    void visit(DBModule module);

    void visit(DBVersion version);

}
