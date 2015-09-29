
package de.jpwinkler.daf.documenttagging.doors.maxent;

import java.util.HashSet;
import java.util.Set;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

/**
 * A predicate generator generates predicates for DOORS objects that may be used
 * by maximum entropy classifiers for training and classification tasks.
 *
 * @author jonwink
 *
 */
public abstract class PredicateGenerator {

    private final Set<String> predicates = new HashSet<>();

    /**
     * Determines and returns the predicates of the given DOORS object.
     *
     * @param object
     * @return
     */
    public final Set<String> getPredicates(final DoorsObject object) {
        predicates.clear();
        runGenerator(object);
        return predicates;
    }

    /**
     * Adds a new predicate. Call this method during execution of the
     * <code>runGenerator</code> method for each predicate.
     *
     * @param predicate
     */
    protected final void emitPredicate(final String predicate) {
        predicates.add(predicate);
    }

    /**
     * Runs the predicate generator on the given DOORS object. Implementing
     * subclasses must call <code>emitPredicate</code> to generate predicates.
     *
     * @param object
     */
    protected abstract void runGenerator(DoorsObject object);

    /**
     * Indicates whether this predicate generator should only generate
     * predicates during training. The default implementation returns false,
     * implementing classes may override this method to return true if needed.
     *
     * @return
     */
    public boolean useOnlyInTraining() {
        return false;
    }

}
