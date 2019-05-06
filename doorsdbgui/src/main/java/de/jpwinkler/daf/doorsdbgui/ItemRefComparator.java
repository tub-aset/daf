package de.jpwinkler.daf.doorsdbgui;

import de.jpwinkler.daf.bridge.DoorsException;
import de.jpwinkler.daf.bridge.ItemRef;
import java.util.Comparator;
import javafx.scene.control.TreeItem;

public class ItemRefComparator implements Comparator<TreeItem<ItemRef>> {

    @Override
    public int compare(final TreeItem<ItemRef> o1, final TreeItem<ItemRef> o2) {
        try {
            if (o1.getValue().getType() != o2.getValue().getType()) {
                return Integer.compare(o1.getValue().getType().ordinal(), o2.getValue().getType().ordinal());
            } else {
                return o1.getValue().getItemName().compareTo(o2.getValue().getItemName());
            }
        } catch (final DoorsException e) {
            throw new RuntimeException(e);
        }

    }

}
