package de.jpwinkler.daf.csveditor.filter;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class NotFilter extends DoorsObjectFilter {

    private final DoorsObjectFilter filter;

    public NotFilter(final DoorsObjectFilter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return !filter.checkObject(object);
    }

}
