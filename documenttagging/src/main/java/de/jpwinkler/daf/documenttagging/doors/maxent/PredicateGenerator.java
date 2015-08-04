
package de.jpwinkler.daf.documenttagging.doors.maxent;

import java.util.ArrayList;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public abstract class PredicateGenerator {

    private final List<String> predicates = new ArrayList<>();

    public final List<String> getPredicates(final DoorsObject object) {
        predicates.clear();
        runGenerator(object);
        return predicates;
    }

    protected final void emitPredicate(final String predicate) {
        predicates.add(predicate);
    }

    protected abstract void runGenerator(DoorsObject object);

}
