package de.jpwinkler.daf.doorsdb.util;

import java.util.List;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelFactory;
import de.jpwinkler.libs.doorsbridge.ItemRef;

public class DBUtils {

    public static DBFolder mkdirs(final DoorsDB database, final ItemRef path) {
        return ensurePath(database.getRoot(), path.getPathSegments());
    }

    private static DBFolder ensurePath(final DBFolder parent, final List<String> path) {
        if (path.size() > 0) {
            DBFolder folder = parent.getFolder(path.get(0));
            if (folder == null) {
                folder = DoorsDBModelFactory.eINSTANCE.createDBFolder();
                folder.setName(path.get(0));
                parent.getChildren().add(folder);
            }
            if (path.size() > 1) {
                return ensurePath(parent.getFolder(path.get(0)), path.subList(1, path.size()));
            } else {
                return folder;
            }
        } else {
            return null;
        }
    }

}
