package de.jpwinkler.daf.documenttagging.algorithmrunner;

import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.SmoothingTechnique;

public class SmoothingConfiguration {

    private SmoothingTechnique smoothingTechnique;

    private double d;

    private double k;

    public SmoothingConfiguration() {
    }

    public SmoothingConfiguration(final SmoothingTechnique smoothingTechnique, final double d, final double k) {
        super();
        this.smoothingTechnique = smoothingTechnique;
        this.d = d;
        this.k = k;
    }

    public SmoothingTechnique getSmoothingTechnique() {
        return smoothingTechnique;
    }

    public void setSmoothingTechnique(final SmoothingTechnique smoothingTechnique) {
        this.smoothingTechnique = smoothingTechnique;
    }

    public double getD() {
        return d;
    }

    public void setD(final double d) {
        this.d = d;
    }

    public double getK() {
        return k;
    }

    public void setK(final double k) {
        this.k = k;
    }

}
