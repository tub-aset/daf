package de.jpwinkler.daf.dataprocessing.preprocessing;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public abstract class ObjectTextPreprocessor extends DoorsObjectPreprocessor {

    @Override
    public final void preprocessObject(final DoorsObject object) {
        object.setObjectText(preprocessString(object.getObjectText()));
        object.setObjectHeading(preprocessString(object.getObjectHeading()));
    }

    protected abstract String preprocessString(final String string);

}
