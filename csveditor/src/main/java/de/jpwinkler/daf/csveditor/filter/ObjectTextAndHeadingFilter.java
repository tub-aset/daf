package de.jpwinkler.daf.csveditor.filter;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class ObjectTextAndHeadingFilter implements DoorsObjectFilter {

    private final DoorsObjectFilter filter;

    public ObjectTextAndHeadingFilter(final String filter, final boolean matchCase, final boolean regexp) {
        this.filter = new CompositeOrFilter(new AttributeFilter("Object Text", filter, matchCase, regexp), new AttributeFilter("Object Heading", filter, matchCase, regexp));
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return filter.checkObject(object);
    }

}
