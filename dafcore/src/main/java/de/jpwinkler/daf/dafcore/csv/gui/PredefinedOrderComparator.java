package de.jpwinkler.daf.dafcore.csv.gui;

import java.util.Comparator;
import java.util.List;

public class PredefinedOrderComparator<T> implements Comparator<T> {

    private final List<T> predefinedOrder;

    public PredefinedOrderComparator(final List<T> predefinedOrder) {
        super();
        this.predefinedOrder = predefinedOrder;
    }

    @Override
    public int compare(final T o1, final T o2) {
        if (predefinedOrder.contains(o1) && predefinedOrder.contains(o2)) {
            return predefinedOrder.indexOf(o1) - predefinedOrder.indexOf(o2);
        } else if (predefinedOrder.contains(o1) && !predefinedOrder.contains(o2)) {
            return -1;
        } else if (!predefinedOrder.contains(o1) && predefinedOrder.contains(o2)) {
            return 1;
        } else {
            return 0;
        }
    }

}
