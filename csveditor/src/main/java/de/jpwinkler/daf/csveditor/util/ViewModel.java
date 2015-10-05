package de.jpwinkler.daf.csveditor.util;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.csveditor.filter.DoorsObjectFilter;

public class ViewModel {

    private final List<ColumnDefinition> displayedColumns = new ArrayList<>();

    private DoorsObjectFilter filter;

    public DoorsObjectFilter getFilter() {
        return filter;
    }

    public void setFilter(final DoorsObjectFilter filter) {
        this.filter = filter;
    }

    public List<ColumnDefinition> getDisplayedColumns() {
        return displayedColumns;
    }

}
