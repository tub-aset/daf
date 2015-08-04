package de.jpwinkler.daf.documenttagging.doors.maxent;

import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class SpecialTokenPredicateGenerator extends PredicateGenerator {

    private static final Pattern PATTERN_ABBREVIATION = Pattern.compile("[A-Z0-9]{3,}");
    private static final Pattern PATTERN_NAME = Pattern.compile("[a-zA-Z0-9]+_[a-zA-Z0-9]+");

    @Override
    protected void runGenerator(final DoorsObject object) {
        emitPredicate("hasAbbreviation=" + PATTERN_ABBREVIATION.matcher(object.getText()).find());
        emitPredicate("hasSignalName=" + PATTERN_NAME.matcher(object.getText()).find());
    }

}
