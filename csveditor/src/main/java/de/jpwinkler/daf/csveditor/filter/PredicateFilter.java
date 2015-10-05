package de.jpwinkler.daf.csveditor.filter;

import java.util.function.Predicate;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class PredicateFilter implements DoorsObjectFilter {

    private final Predicate<DoorsObject> p;

    public PredicateFilter(final Predicate<DoorsObject> p) {
        super();
        this.p = p;
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return p.test(object);
    }

}
