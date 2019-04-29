package de.jpwinkler.daf.csveditor.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.doorscsv.model.DoorsObject;

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
