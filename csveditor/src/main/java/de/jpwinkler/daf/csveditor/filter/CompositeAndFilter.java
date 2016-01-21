package de.jpwinkler.daf.csveditor.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

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