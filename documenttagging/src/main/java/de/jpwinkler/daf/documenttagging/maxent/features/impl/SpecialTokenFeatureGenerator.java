package de.jpwinkler.daf.documenttagging.maxent.features.impl;

import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.documenttagging.maxent.features.BooleanFeature;
import de.jpwinkler.daf.documenttagging.maxent.features.FeatureGenerator;

public class SpecialTokenFeatureGenerator extends FeatureGenerator {

    private static final Pattern PATTERN_ABBREVIATION = Pattern.compile("[A-Z0-9]{3,}");
    private static final Pattern PATTERN_NAME = Pattern.compile("[a-zA-Z0-9]+_[a-zA-Z0-9]+");

    @Override
    protected void processObject(final DoorsObject o) {
        emitFeature(new BooleanFeature("hasAbbreviation", PATTERN_ABBREVIATION.matcher(o.getText()).find()));
        emitFeature(new BooleanFeature("hasSignalName", PATTERN_NAME.matcher(o.getText()).find()));
    }

}
