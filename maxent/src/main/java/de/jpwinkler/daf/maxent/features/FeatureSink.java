package de.jpwinkler.daf.maxent.features;

public interface FeatureSink {

    void consume(Feature<?> feature);

}
