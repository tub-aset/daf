package de.jpwinkler.daf.maxent.features.impl;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.maxent.features.FeatureGenerator;
import de.jpwinkler.daf.maxent.features.StringFeature;

public class FOObjectTypeFeatureGenerator extends FeatureGenerator {

    @Override
    protected void processObject(final DoorsObject o) {
        if (o.getAttributes().containsKey("FO_Object_Type") && !o.getAttributes().get("FO_Object_Type").isEmpty()) {
            emitFeature(new StringFeature("foObjectType", o.getAttributes().get("FO_Object_Type")));
        }
    }

}
