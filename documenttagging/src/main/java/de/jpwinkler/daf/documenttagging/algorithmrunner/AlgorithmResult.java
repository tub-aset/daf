package de.jpwinkler.daf.documenttagging.algorithmrunner;

import de.jpwinkler.daf.documenttagging.ConfusionMatrix;

public class AlgorithmResult {

    private AlgorithmConfiguration algorithmConfiguration;

    private ConfusionMatrix<String> confusionMatrix;

    public AlgorithmConfiguration getAlgorithmConfiguration() {
        return algorithmConfiguration;
    }

    public void setAlgorithmConfiguration(final AlgorithmConfiguration algorithmConfiguration) {
        this.algorithmConfiguration = algorithmConfiguration;
    }

    public ConfusionMatrix<String> getConfusionMatrix() {
        return confusionMatrix;
    }

    public void setConfusionMatrix(final ConfusionMatrix<String> confusionMatrix) {
        this.confusionMatrix = confusionMatrix;
    }

}
