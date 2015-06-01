package de.jpwinkler.daf.dafcore.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PreorderedComparator<T extends Comparable<T>> implements Comparator<T> {

    private final List<T> list;

    public PreorderedComparator(final T... list) {
        super();
        this.list = Arrays.asList(list);
    }

    @Override
    public int compare(final T o1, final T o2) {
        if (list.contains(o1) && !list.contains(o2)) {
            return -1;
        } else if (!list.contains(o1) && list.contains(o2)) {
            return 1;
        } else {
            return o1.compareTo(o2);
        }
    }

}
