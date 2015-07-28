package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TreeNode {

    private final List<TreeNode> children = new ArrayList<>();

    private transient TreeNode parent;

    private transient TreeNode previous;

    private transient TreeNode next;

    private int observation;

    public TreeNode getPrevious() {
        return previous;
    }

    public TreeNode getNext() {
        return next;
    }

    public int getObservation() {
        return observation;
    }

    public void setObservation(final int observation) {
        this.observation = observation;
    }

    public void addChild(final TreeNode treeNode) {
        children.add(treeNode);
    }

    public void removeChild(final TreeNode treeNode) {
        treeNode.parent = null;
    }

    public TreeNode getParent() {
        return parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return toString(t -> "");
    }

    public void accept(final TreeNodeVisitor visitor) {
        visitor.visit(this);
        for (final TreeNode node : children) {
            node.accept(visitor);
        }
    }

    public String toString(final ScenarioResult result) {
        return toString(node -> String.valueOf(result.getState(node)));
    }

    public String toString(final Function<TreeNode, String> nodeAnnotationFunction) {
        final StringBuilder b = new StringBuilder();
        b.append("n");
        if (nodeAnnotationFunction != null) {
            b.append(nodeAnnotationFunction.apply(this));
        }
        if (children.size() > 0) {
            b.append("(");
            for (final TreeNode n : children) {
                b.append(n.toString(nodeAnnotationFunction));
                if (n.getNext() != null) {
                    b.append(", ");
                }
            }
            b.append(")");
        }
        return b.toString();
    }

    public void finalizeTree() {
        TreeNode previous = null;
        for (final TreeNode node : getChildren()) {
            node.parent = this;

            if (previous != null) {
                node.previous = previous;
                previous.next = node;
            }

            previous = node;

            node.finalizeTree();
        }
    }
}
