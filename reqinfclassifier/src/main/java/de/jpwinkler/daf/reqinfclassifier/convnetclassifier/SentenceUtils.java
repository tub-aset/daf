package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.trees.Tree;

public class SentenceUtils {

    private static class IgnoreLabel extends CoreLabel {

        private static final long serialVersionUID = 1L;

        private Label wrappedLabel;

        public IgnoreLabel(final Label wrappedLabel) {
            super();
            this.wrappedLabel = wrappedLabel;
        }

        public Label getWrappedLabel() {
            return wrappedLabel;
        }

        public void setWrappedLabel(final Label wrappedLabel) {
            this.wrappedLabel = wrappedLabel;
        }

        @Override
        public String toString() {
            return wrappedLabel.toString();
        }
    }

    private static final List<String> LABELS_TO_IGNORE = Arrays.asList("NP", "NN", "NE");

    private void reduceTree(final Tree tree) {
        for (final Tree t : tree.children()) {
            if (LABELS_TO_IGNORE.contains(t.label().toString())) {
                t.setLabel(new IgnoreLabel(t.label()));
            }
        }
    }

    public String treeToReducedString(final Tree tree) {
        reduceTree(tree);
        final List<Label> labels = new ArrayList<>();
        treeToList(tree, labels);
        return StringUtils.join(labels.stream().map(l -> l.toString()).collect(Collectors.toList()), " ");
    }

    private void treeToList(final Tree tree, final List<Label> list) {
        for (final Tree child : tree) {
            if (child.label() instanceof IgnoreLabel) {
                list.add(((IgnoreLabel) child.label()).getWrappedLabel());
            } else if (child.isLeaf()) {
                list.add(child.label());
            } else {
                treeToList(child, list);
            }
        }
    }
}
