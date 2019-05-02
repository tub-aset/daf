package de.jpwinkler.daf.csveditor;

import java.util.Comparator;
import java.util.List;

import de.jpwinkler.daf.csveditor.views.ColumnDefinition;

public class PredefinedOrderComparator implements Comparator<ColumnDefinition> {

    private final List<String> predefinedOrder;

    public PredefinedOrderComparator(final List<String> predefinedOrder) {
        super();
        this.predefinedOrder = predefinedOrder;
    }

    @Override
    public int compare(final ColumnDefinition o1, final ColumnDefinition o2) {
        if (predefinedOrder.contains(o1.getColumnTitle()) && predefinedOrder.contains(o2.getColumnTitle())) {
            return predefinedOrder.indexOf(o1.getColumnTitle()) - predefinedOrder.indexOf(o2.getColumnTitle());
        } else if (predefinedOrder.contains(o1.getColumnTitle()) && !predefinedOrder.contains(o2.getColumnTitle())) {
            return -1;
        } else if (!predefinedOrder.contains(o1.getColumnTitle()) && predefinedOrder.contains(o2.getColumnTitle())) {
            return 1;
        } else {
            return 0;
        }
    }

}
