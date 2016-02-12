package de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import edu.stanford.nlp.trees.Tree;

public class SentenceFragment extends ParseTreeFeatureGeneratorFragment {

    private static final List<String> SENTENCE_TAGS = Arrays.asList("NP", "VP", "PP", "AP", "CPP", "CNP", "AVP");

    @Override
    public void apply(final Tree t) {
        if (SENTENCE_TAGS.contains(t.label().toString())) {
            final List<String> leaves = new ArrayList<>();

            ParseTreeUtils.traverseTree(t, subtree -> {
                if (subtree.isLeaf()) {
                    leaves.add(subtree.label().toString());
                }
                return t == subtree || !SENTENCE_TAGS.contains(subtree.label().toString());
            });
            if (leaves.size() > 0) {
                emitFeature(StringUtils.join(leaves, " "));
            }
        }
    }

}
