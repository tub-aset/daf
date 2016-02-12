package de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree;

import edu.stanford.nlp.trees.Tree;

public class SkeletonFragment extends ParseTreeFeatureGeneratorFragment {

    @Override
    public void apply(final Tree t) {
        if (!t.isLeaf()) {
            final StringBuilder builder = new StringBuilder();
            builder.append(t.label().toString());
            ParseTreeUtils.traverseTree(t, subtree -> {
                if (!subtree.isLeaf()) {
                    builder.append("(");
                }
                return true;
            } , subtree -> {
                if (!subtree.isLeaf()) {
                    builder.append(")");
                }
            });
            emitFeature(builder.toString());
        }

    }

}
