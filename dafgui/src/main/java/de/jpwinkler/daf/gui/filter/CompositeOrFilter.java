package de.jpwinkler.daf.gui.filter;

import de.jpwinkler.daf.model.DoorsObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeOrFilter extends DoorsObjectFilter {

    private final List<DoorsObjectFilter> filters = new ArrayList<>();

    public CompositeOrFilter(final DoorsObjectFilter... filters) {
        super();
        this.filters.addAll(Arrays.asList(filters));
    }


    @Override
    public boolean checkObject(final DoorsObject object) {
        return filters.stream().anyMatch(filter -> filter.checkObject(object));
    }

}
