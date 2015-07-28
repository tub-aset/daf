package de.jpwinkler.daf.documenttagging.maxent.preprocessing;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public abstract class ObjectTextPreprocessor implements Preprocessor {

    @Override
    public void apply(final DoorsObject object) {
        object.setObjectText(processString(object.getObjectText()));
        object.setObjectHeading(processString(object.getObjectHeading()));
    }

    protected abstract String processString(final String s);

}
