package de.jpwinkler.daf.dataprocessing.featuregeneration;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

/**
 * A predicate generator that generates predicates based on the FO_Object_Type
 * attribute of DOORS objects.
 *
 * @author jonwink
 *
 */
public class FoObjectTypePredicateGenerator extends FeatureGenerator<DoorsObject, String> {

    @Override
    protected void runGenerator(final DoorsObject element) {
        if (element.getAttributes().containsKey("FO_Object_Type") && !element.getAttributes().get("FO_Object_Type").isEmpty()) {
            emitFeature("foObjectType=" + element.getAttributes().get("FO_Object_Type"));
        }
    }

}
