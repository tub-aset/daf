package de.jpwinkler.daf.dataprocessing.featuregeneration;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class ObjectTypeFeatureGenerator extends FeatureGenerator<DoorsObject, String> {

    @Override
    protected void runGenerator(final DoorsObject element) {
        if (element.getAttributes().containsKey("Object Type")) {
            emitFeature("objectType=" + element.getAttributes().get("Object Type"));
        }
    }

}
