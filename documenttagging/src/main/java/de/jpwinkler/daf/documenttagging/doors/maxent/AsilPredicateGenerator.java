package de.jpwinkler.daf.documenttagging.doors.maxent;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

/**
 * A predicate generator that generates predicates based on the ASIL attribute
 * of DOORS objects.
 * 
 * @author jonwink
 *
 */
public class AsilPredicateGenerator extends PredicateGenerator {

    @Override
    protected void runGenerator(final DoorsObject object) {
        if (object.getAttributes().containsKey("ASIL") && !object.getAttributes().get("ASIL").equals("-")) {
            emitPredicate("asil=" + object.getAttributes().get("ASIL"));
        }
    }

}
