package de.jpwinkler.daf.documenttagging.algorithmrunner;

import de.jpwinkler.daf.documenttagging.hypermarkovchain.GrowRateFunction;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.SmoothingTechnique;

public class AlgorithmConfiguration {

    private String testDocument;

    private String algorithmName;

    private SmoothingTechnique smoothingTechnique;

    private double smoothingK;

    private double smoothingD;

    private int gisCutoff;

    private int gisIterations;

    private GrowRateFunction growRateFunction;

    public AlgorithmConfiguration(final String testDocument, final String algorithmName, final SmoothingTechnique smoothingTechnique, final double smoothingK, final double smoothingD, final int gisCutoff, final int gisIterations, final GrowRateFunction growRateFunction) {
        super();
        this.testDocument = testDocument;
        this.algorithmName = algorithmName;
        this.smoothingTechnique = smoothingTechnique;
        this.smoothingK = smoothingK;
        this.smoothingD = smoothingD;
        this.gisCutoff = gisCutoff;
        this.gisIterations = gisIterations;
        this.growRateFunction = growRateFunction;
    }

    public AlgorithmConfiguration() {
    }

    public String getTestDocument() {
        return testDocument;
    }

    public void setTestDocument(final String testDocument) {
        this.testDocument = testDocument;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(final String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public SmoothingTechnique getSmoothingTechnique() {
        return smoothingTechnique;
    }

    public void setSmoothingTechnique(final SmoothingTechnique smoothingTechnique) {
        this.smoothingTechnique = smoothingTechnique;
    }

    public double getSmoothingK() {
        return smoothingK;
    }

    public void setSmoothingK(final double smoothingK) {
        this.smoothingK = smoothingK;
    }

    public double getSmoothingD() {
        return smoothingD;
    }

    public void setSmoothingD(final double smoothingD) {
        this.smoothingD = smoothingD;
    }

    public int getGisCutoff() {
        return gisCutoff;
    }

    public void setGisCutoff(final int gisCutoff) {
        this.gisCutoff = gisCutoff;
    }

    public int getGisIterations() {
        return gisIterations;
    }

    public void setGisIterations(final int gisIterations) {
        this.gisIterations = gisIterations;
    }

    public GrowRateFunction getGrowRateFunction() {
        return growRateFunction;
    }

    public void setGrowRateFunction(final GrowRateFunction growRateFunction) {
        this.growRateFunction = growRateFunction;
    }

    @Override
    public String toString() {
        return "test document: " + testDocument + ", alg: " + algorithmName + ", cutoff: " + gisCutoff + ", iterations: " + gisIterations + ", grow rate: " + growRateFunction + ", smoothing: " + smoothingTechnique + ", d: " + smoothingD + ", k: " + smoothingK;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((algorithmName == null) ? 0 : algorithmName.hashCode());
        result = prime * result + gisCutoff;
        result = prime * result + gisIterations;
        result = prime * result + ((growRateFunction == null) ? 0 : growRateFunction.hashCode());
        long temp;
        temp = Double.doubleToLongBits(smoothingD);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(smoothingK);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((smoothingTechnique == null) ? 0 : smoothingTechnique.hashCode());
        result = prime * result + ((testDocument == null) ? 0 : testDocument.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlgorithmConfiguration other = (AlgorithmConfiguration) obj;
        if (algorithmName == null) {
            if (other.algorithmName != null) {
                return false;
            }
        } else if (!algorithmName.equals(other.algorithmName)) {
            return false;
        }
        if (gisCutoff != other.gisCutoff) {
            return false;
        }
        if (gisIterations != other.gisIterations) {
            return false;
        }
        if (growRateFunction != other.growRateFunction) {
            return false;
        }
        if (Double.doubleToLongBits(smoothingD) != Double.doubleToLongBits(other.smoothingD)) {
            return false;
        }
        if (Double.doubleToLongBits(smoothingK) != Double.doubleToLongBits(other.smoothingK)) {
            return false;
        }
        if (smoothingTechnique != other.smoothingTechnique) {
            return false;
        }
        if (testDocument == null) {
            if (other.testDocument != null) {
                return false;
            }
        } else if (!testDocument.equals(other.testDocument)) {
            return false;
        }
        return true;
    }

}
