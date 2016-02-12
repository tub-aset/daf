package de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree;

import edu.stanford.nlp.trees.Tree;

public abstract class ParseTreeFeatureGeneratorFragment {

    private ParseTreeFeatureGenerator generator;

    public void setFeatureGenerator(final ParseTreeFeatureGenerator generator) {
        this.generator = generator;
    }

    public void emitFeature(final String feature) {
        generator.emitFeature(feature);
    }

    public abstract void apply(final Tree t);

}
