package de.jpwinkler.daf.documenttagging.doors.maxent;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class ObjectTypePredicateGenerator extends PredicateGenerator {

    @Override
    protected void runGenerator(final DoorsObject o) {
        if (o.getAttributes().containsKey("Object Type")) {
            emitPredicate("objectType=" + o.getAttributes().get("Object Type"));
        }
    }

}
