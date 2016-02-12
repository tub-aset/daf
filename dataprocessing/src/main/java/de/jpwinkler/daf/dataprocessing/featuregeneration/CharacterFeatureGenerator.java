package de.jpwinkler.daf.dataprocessing.featuregeneration;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class CharacterFeatureGenerator extends FeatureGenerator<DoorsObject, String> {

    private final int minWindowSize;
    private final int maxWindowSize;

    public CharacterFeatureGenerator(final int minWindowSize, final int maxWindowSize) {
        super();
        this.minWindowSize = minWindowSize;
        this.maxWindowSize = maxWindowSize;
    }

    @Override
    protected void runGenerator(final DoorsObject element) {

        String text = element.getText();

        if (text != null && text.length() >= maxWindowSize) {

            text = text.toLowerCase();

            for (int currentWindowSize = minWindowSize; currentWindowSize <= maxWindowSize; currentWindowSize++) {
                for (int i = 0; i <= text.length() - currentWindowSize; i++) {
                    emitFeature(text.substring(i, i + currentWindowSize));
                }
            }

        }

    }

    public int getMinWindowSize() {
        return minWindowSize;
    }

    public int getMaxWindowSize() {
        return maxWindowSize;
    }


}
