package de.jpwinkler.daf.filter.objects;

import de.jpwinkler.daf.model.DoorsObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeAndFilter extends DoorsObjectFilter {

    private final List<DoorsObjectFilter> filters = new ArrayList<>();

    public CompositeAndFilter(final DoorsObjectFilter... filters) {
        super();
        this.filters.addAll(Arrays.asList(filters));
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return filters.stream().allMatch(filter -> filter.checkObject(object));
    }

}
