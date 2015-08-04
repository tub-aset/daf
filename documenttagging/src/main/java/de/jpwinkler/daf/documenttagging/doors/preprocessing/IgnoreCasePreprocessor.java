package de.jpwinkler.daf.documenttagging.doors.preprocessing;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class IgnoreCasePreprocessor implements DoorsObjectPreprocessor {

    @Override
    public void preprocessObject(final DoorsObject object) {
        if (object.getObjectText() != null) {
            object.setObjectText(object.getObjectText().toLowerCase());
        }

        if (object.getObjectHeading() != null) {
            object.setObjectHeading(object.getObjectHeading().toLowerCase());
        }
    }

}
