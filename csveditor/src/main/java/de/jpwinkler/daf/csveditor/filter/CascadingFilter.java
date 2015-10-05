package de.jpwinkler.daf.csveditor.filter;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class CascadingFilter implements DoorsObjectFilter {

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
