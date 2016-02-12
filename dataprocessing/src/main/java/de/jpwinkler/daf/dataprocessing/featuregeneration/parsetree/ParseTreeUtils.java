package de.jpwinkler.daf.dataprocessing.featuregeneration.parsetree;

import java.util.function.Consumer;
import java.util.function.Function;

import edu.stanford.nlp.trees.Tree;

public final class ParseTreeUtils {

    private ParseTreeUtils() {
    }

    public static void traverseTree(final Tree tree, final Function<Tree, Boolean> f) {
        traverseTree(tree, f, null);
    }

    public static void traverseTree(final Tree tree, final Function<Tree, Boolean> pre, final Consumer<Tree> post) {
        if (pre.apply(tree)) {
            for (final Tree child : tree.children()) {
                traverseTree(child, pre, post);
            }
        }
        if (post != null) {
            post.accept(tree);
        }
    }

}
