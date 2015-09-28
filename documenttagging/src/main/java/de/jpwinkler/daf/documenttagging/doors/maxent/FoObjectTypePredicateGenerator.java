package de.jpwinkler.daf.documenttagging.doors.maxent;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

/**
 * A predicate generator that generates predicates based on the FO_Object_Type
 * attribute of DOORS objects.
 * 
 * @author jonwink
 *
 */
public class FoObjectTypePredicateGenerator extends PredicateGenerator {

    @Override
    protected void runGenerator(final DoorsObject object) {
        if (object.getAttributes().containsKey("FO_Object_Type") && !object.getAttributes().get("FO_Object_Type").isEmpty()) {
            emitPredicate("foObjectType=" + object.getAttributes().get("FO_Object_Type"));
        }
    }

}
