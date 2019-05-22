package de.jpwinkler.daf.filter.objects;

import de.jpwinkler.daf.model.DoorsObject;

public class NotFilter extends DoorsObjectFilter {

    private final DoorsObjectFilter filter;

    public NotFilter(final DoorsObjectFilter filter) {
        this.filter = filter;
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return !filter.checkObject(object);
    }

}
