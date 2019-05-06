package de.jpwinkler.daf.doorsdbgui;

import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.util.Comparator;
import javafx.scene.control.TreeItem;

public class DBItemComparator implements Comparator<TreeItem<DoorsTreeNode>> {

    @Override
    public int compare(final TreeItem<DoorsTreeNode> o1, final TreeItem<DoorsTreeNode> o2) {
        if (o1.getValue() instanceof DoorsModule && o2.getValue() instanceof DoorsFolder) {
            return -1;
        } else if (o1.getValue() instanceof DoorsFolder && o2.getValue() instanceof DoorsModule) {
            return 1;
        } else {
            return o1.getValue().getName().compareTo(o2.getValue().getName());
        }
    }

}
