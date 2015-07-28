package de.jpwinkler.daf.documenttagging.maxent.features.impl;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.documenttagging.maxent.features.BooleanFeature;
import de.jpwinkler.daf.documenttagging.maxent.features.FeatureGenerator;

public class SpecialCharacterFeatureGenerator extends FeatureGenerator {

    @Override
    protected void processObject(final DoorsObject o) {
        emitFeature(new BooleanFeature("hasQuotations", o.getText().contains("\"")));
    }

}
