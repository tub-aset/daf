package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario;

/**
 * A visitor used for traversing the nodes of a {@link TreeNode} tree.
 * 
 * @author jonwink
 *
 */
public interface TreeNodeVisitor {

    /**
     * Called for each node within a tree.
     * 
     * @param treeNode
     */
    void visit(TreeNode treeNode);

}
