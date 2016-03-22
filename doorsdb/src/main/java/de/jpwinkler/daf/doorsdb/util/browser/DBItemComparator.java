package de.jpwinkler.daf.doorsdb.util.browser;

import java.util.Comparator;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import javafx.scene.control.TreeItem;

public class DBItemComparator implements Comparator<TreeItem<DBItem>> {

    @Override
    public int compare(final TreeItem<DBItem> o1, final TreeItem<DBItem> o2) {
        if (o1.getValue() instanceof DBModule && o2.getValue() instanceof DBFolder) {
            return -1;
        } else if (o1.getValue() instanceof DBFolder && o2.getValue() instanceof DBModule) {
            return 1;
        } else {
            return o1.getValue().getName().compareTo(o2.getValue().getName());
        }
    }

}
