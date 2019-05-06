package de.jpwinkler.daf.localdb;

import de.jpwinkler.daf.bridge.ItemRef;
import de.jpwinkler.daf.model.DoorsCSVFactory;
import de.jpwinkler.daf.model.DoorsDB;
import de.jpwinkler.daf.model.DoorsFolder;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBUtils {

    public static DoorsFolder mkdirs(final DoorsDB database, final ItemRef path) {
        return ensurePath(database.getRoot(), path.getItemName().getPathSegments());
    }

    public static DoorsFolder mkdirs(final DoorsDB database, final Path path) {
        System.out.println(path);
        final List<String> segments = new ArrayList<>();
        final Iterator<Path> iterator = path.iterator();
        while (iterator.hasNext()) {
            segments.add(iterator.next().toString());
        }
        return ensurePath(database.getRoot(), segments);
    }

    private static DoorsFolder ensurePath(final DoorsFolder parent, final List<String> path) {
        if (path.size() > 0) {
            DoorsFolder folder = parent.getFolder(path.get(0));
            if (folder == null) {
                folder = DoorsCSVFactory.eINSTANCE.createDoorsFolder();
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
