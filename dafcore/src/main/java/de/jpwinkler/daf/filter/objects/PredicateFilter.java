package de.jpwinkler.daf.filter.objects;

import de.jpwinkler.daf.model.DoorsObject;
import java.util.function.Predicate;

public class PredicateFilter extends DoorsObjectFilter {

    private final Predicate<DoorsObject> p;

    public PredicateFilter(final Predicate<DoorsObject> p) {
        this.p = p;
    }

    @Override
    public boolean checkObject(final DoorsObject object) {
        return p.test(object);
    }

}
