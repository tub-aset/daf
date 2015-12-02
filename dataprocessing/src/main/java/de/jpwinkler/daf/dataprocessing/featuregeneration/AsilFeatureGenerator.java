package de.jpwinkler.daf.dataprocessing.featuregeneration;

import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

public class AsilFeatureGenerator extends FeatureGenerator<DoorsTreeNode, String> {

    @Override
    protected void runGenerator(final DoorsTreeNode element) {
        if (element.getAttributes().containsKey("ASIL") && !element.getAttributes().get("ASIL").equals("-")) {
            emitFeature("asil=" + element.getAttributes().get("ASIL"));
        }
    }

}
