package de.jpwinkler.daf.documenttagging.maxent.features;

public interface FeatureSink {

    void consume(Feature<?> feature);

}
