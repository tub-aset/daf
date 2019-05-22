package de.jpwinkler.daf.gui.filter;

import de.jpwinkler.daf.model.DoorsObject;

public class ObjectTextAndHeadingFilter extends DoorsObjectFilter {

    private final DoorsObjectFilter filter;

    public ObjectTextAndHeadingFilter(final String filter, final boolean exactMatch, final boolean regexp) {
        this.filter = new CompositeOrFilter(new AttributeFilter("Object Text", filter, exactMatch, regexp), new AttributeFilter("Object Heading", filter, exactMatch, regexp));
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return filter.checkObject(object);
    }

}
