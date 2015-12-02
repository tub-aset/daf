package de.jpwinkler.daf.dataprocessing.featuregeneration;

import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class SpecialTokenFeatureGenerator extends FeatureGenerator<DoorsObject, String> {

    private static final Pattern PATTERN_ABBREVIATION = Pattern.compile("[A-Z0-9]{3,}");
    private static final Pattern PATTERN_NAME = Pattern.compile("[a-zA-Z0-9]+_[a-zA-Z0-9]+");

    @Override
    protected void runGenerator(final DoorsObject object) {
        if (PATTERN_ABBREVIATION.matcher(object.getText()).find()) {
            emitFeature("hasAbbreviation=true");
        }
        if (PATTERN_NAME.matcher(object.getText()).find()) {
            emitFeature("hasSignalName=true");
        }
    }

}
