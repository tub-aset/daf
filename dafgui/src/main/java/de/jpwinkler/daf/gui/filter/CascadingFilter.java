package de.jpwinkler.daf.gui.filter;

import de.jpwinkler.daf.model.DoorsObject;

public class CascadingFilter extends DoorsObjectFilter {

    private final DoorsObjectFilter filter;

    public CascadingFilter(final DoorsObjectFilter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return filter.checkObject(object) || (object.getParent() instanceof DoorsObject && checkObject((DoorsObject) object.getParent()));
    }

}
