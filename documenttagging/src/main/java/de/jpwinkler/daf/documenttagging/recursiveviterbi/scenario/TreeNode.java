package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Data model used for testing document tagging algorithms.
 *
 * @author jonwink
 *
 */
public class TreeNode {

    private final List<TreeNode> children = new ArrayList<>();

    private transient TreeNode parent;

    private transient TreeNode previous;

    private transient TreeNode next;

    private int observation;

    /**
     * Returns the node that is positioned before this node beneath the same
     * parent or null, if there is no such node.
     *
     * @return
     */
    public TreeNode getPrevious() {
        return previous;
    }

    /**
     * Returns the node that is positioned next to this node beneath the same
     * parent or null, if there is no such node.
     *
     * @return
     */
    public TreeNode getNext() {
        return next;
    }

    /**
     * Returns the observation associated with this node.
     *
     * @return
     */
    public int getObservation() {
        return observation;
    }

    /**
     * Sets the observation associated with this node.
     *
     * @param observation
     */
    public void setObservation(final int observation) {
        this.observation = observation;
    }

    /**
     * Adds a new child to this node.
     *
     * @param treeNode
     */
    public void addChild(final TreeNode treeNode) {
        children.add(treeNode);
    }

    /**
     * Returns the parent node of this node.
     *
     * @return
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * Returns all childrenn of this node.
     *
     * @return
     */
    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return toString(t -> "");
    }

    /**
     * Traverses the tree and calls the visitor's visit method for each node.
     * The tree is traversed pre-order.
     *
     * @param visitor
     */
    public void accept(final TreeNodeVisitor visitor) {
        visitor.visit(this);
        for (final TreeNode node : children) {
            node.accept(visitor);
        }
    }


    /**
     * Returns a string representation of this tree. Each node will be printed
     * with its associated tag according to the {@link ScenarioResult}
     * <code>result</code>.
     *
     * @param result
     * @return
     */
    public String toString(final ScenarioResult result) {
        return toString(node -> String.valueOf(result.getState(node)));
    }

    /**
     * Returns a string representation of this tree.
     *
     * @param nodeAnnotationFunction
     *            A function returning labels for nodes. If the function is not
     *            null, this label is appended to each node.
     * @return
     */
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

    /**
     * Must be called after building the tree. This method sets the parent,
     * previous and next references of all node in the tree.
     */
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
