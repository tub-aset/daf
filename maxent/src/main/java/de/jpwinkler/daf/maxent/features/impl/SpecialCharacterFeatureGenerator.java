package de.jpwinkler.daf.maxent.features.impl;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.maxent.features.BooleanFeature;
import de.jpwinkler.daf.maxent.features.FeatureGenerator;

public class SpecialCharacterFeatureGenerator extends FeatureGenerator {

    @Override
    protected void processObject(final DoorsObject o) {
        emitFeature(new BooleanFeature("hasQuotations", o.getText().contains("\"")));
    }

}
