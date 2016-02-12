package de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import edu.stanford.nlp.trees.Tree;

public class VerbFragment extends ParseTreeFeatureGeneratorFragment {

    private static final List<String> SENTENCE_TAGS = Arrays.asList("S");

    private static final List<String> VERB_TAGS = Arrays.asList("ADV", "VVFIN", "VVIMP", "VVINF", "VVIZU", "VVPP", "VAFIN", "VAIMP", "VAINF", "VAPP", "VMFIN", "VMINF", "VMPP");

    @Override
    public void apply(final Tree tree) {
        if (SENTENCE_TAGS.contains(tree.label().toString())) {
            final List<String> b = new ArrayList<>();
            ParseTreeUtils.traverseTree(tree, t -> {
                if (t != tree && SENTENCE_TAGS.contains(t.label().toString())) {
                    return false;
                } else if (t.numChildren() == 1 && t.getChild(0).isLeaf() &&
                        VERB_TAGS.contains(t.label().toString())) {
                    b.add(t.getChild(0).label().toString());
                    return true;
                } else {
                    return true;
                }
            });
            emitFeature(StringUtils.join(b, " ").trim().toLowerCase());
        }
    }

}
