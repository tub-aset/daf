package de.jpwinkler.daf.filter.objects;

import de.jpwinkler.daf.model.DoorsObject;

public class ReverseCascadingFilter extends DoorsObjectFilter {

    private final DoorsObjectFilter filter;

    public ReverseCascadingFilter(final DoorsObjectFilter filter) {
        this.filter = filter;
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return filter.checkObject(object) || object.getChildren().stream().anyMatch(n -> n instanceof DoorsObject && checkObject((DoorsObject) n));
    }

}
