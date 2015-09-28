package de.jpwinkler.daf.documenttagging.doors.maxent;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

/**
 * A predicate generator that generates predicates based on the Object Type
 * attribute of DOORS objects.
 *
 * @author jonwink
 *
 */
public class ObjectTypePredicateGenerator extends PredicateGenerator {

    @Override
    protected void runGenerator(final DoorsObject o) {
        if (o.getAttributes().containsKey("Object Type")) {
            emitPredicate("objectType=" + o.getAttributes().get("Object Type"));
        }
    }

}
