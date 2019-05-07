package de.jpwinkler.daf.doorsdbgui;

import java.util.Comparator;
import javafx.scene.control.TreeItem;
import de.jpwinkler.daf.bridge.DoorsTreeNodeRef;

public class ItemRefComparator implements Comparator<TreeItem<DoorsTreeNodeRef>> {

    @Override
    public int compare(final TreeItem<DoorsTreeNodeRef> o1, final TreeItem<DoorsTreeNodeRef> o2) {
        if (o1.getValue().getType() != o2.getValue().getType()) {
            return Integer.compare(o1.getValue().getType().ordinal(), o2.getValue().getType().ordinal());
        } else {
            return o1.getValue().getFullName().compareTo(o2.getValue().getFullName());
        }
    }
}
