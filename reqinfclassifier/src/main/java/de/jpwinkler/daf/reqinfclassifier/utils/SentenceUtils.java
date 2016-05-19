package de.jpwinkler.daf.reqinfclassifier.utils;

import java.util.Arrays;
import java.util.List;

import edu.stanford.nlp.trees.Tree;

public class SentenceUtils {

    private static final List<String> SENTENCE_LABELS = Arrays.asList("S", "CS");
    private static final List<String> NOUN_LABELS = Arrays.asList("NN", "NE");

    public static boolean isSentence(final Tree tree) {
        return tree.numChildren() == 1 && SENTENCE_LABELS.contains(tree.getChild(0).label().toString());
    }

    public static boolean containsSentence(final Tree tree) {
        for (final Tree child : tree.getChildrenAsList()) {
            if (child.label() != null && SENTENCE_LABELS.contains(child.label().toString())) {
                return true;
            } else if (containsSentence(child)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSingleNoun(final Tree tree) {
        return tree.numChildren() == 1 && tree.getChild(0).label().toString().equals("NUR") && tree.getChild(0).numChildren() == 1 && NOUN_LABELS.contains(tree.getChild(0).getChild(0).label().toString());
    }
}
