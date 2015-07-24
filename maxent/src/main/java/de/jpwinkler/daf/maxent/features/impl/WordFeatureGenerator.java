package de.jpwinkler.daf.maxent.features.impl;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.maxent.features.FeatureGenerator;
import de.jpwinkler.daf.maxent.features.UniFeature;

public class WordFeatureGenerator extends FeatureGenerator {

    private String[] tokenizer(final String s) {
        String temp = s.toLowerCase();
        temp = temp.replaceAll("(?![äöüßa-z0-9 ])(.)", " ");
        return temp.split(" ");
    }

    @Override
    protected void processObject(final DoorsObject o) {
        for (final String word : tokenizer(o.getText())) {
            if (!word.isEmpty()) {
                emitFeature(new UniFeature(word));
            }
        }
    }

}
