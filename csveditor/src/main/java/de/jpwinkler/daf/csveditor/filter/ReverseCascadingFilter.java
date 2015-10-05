package de.jpwinkler.daf.csveditor.filter;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class ReverseCascadingFilter implements DoorsObjectFilter {

    private final DoorsObjectFilter filter;

    public ReverseCascadingFilter(final DoorsObjectFilter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return filter.checkObject(object) || object.getChildren().stream().anyMatch(n -> n instanceof DoorsObject && checkObject((DoorsObject) n));
    }

}
