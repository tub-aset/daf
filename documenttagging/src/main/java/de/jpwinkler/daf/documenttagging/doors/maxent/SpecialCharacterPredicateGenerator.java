package de.jpwinkler.daf.documenttagging.doors.maxent;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

/**
 * A predicate generator that tests the object text and object heading for
 * certain special characters and generates predicates if these special
 * characters are present. The following characters are supported:
 *
 * <li>" Quotations</li>
 *
 * @author jonwink
 *
 */
public class SpecialCharacterPredicateGenerator extends PredicateGenerator {

    @Override
    protected void runGenerator(final DoorsObject object) {
        if (object.getText().contains("\"")) {
            emitPredicate("hasQuotations=true");
        }
    }

}
