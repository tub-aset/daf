package de.jpwinkler.daf.dataprocessing.featuregeneration;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class SpecialCharacterFeatureGenerator extends FeatureGenerator<DoorsObject, String> {

    @Override
    protected void runGenerator(final DoorsObject element) {
        if (element.getText().contains("\"")) {
            emitFeature("hasQuotations=true");
        }
    }

}
