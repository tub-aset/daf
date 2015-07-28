package de.jpwinkler.daf.documenttagging.maxent.features;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public abstract class FeatureGenerator {

    private FeatureSink fs;

    public final void run(final FeatureSink fs, final DoorsObject o) {
        this.fs = fs;
        processObject(o);
    }

    public FeatureGeneratorMode getFeatureGeneratorMode() {
        return FeatureGeneratorMode.ALWAYS;
    }

    protected abstract void processObject(DoorsObject o);

    protected final void emitFeature(final Feature<?> f) {
        fs.consume(f);
    }

}
