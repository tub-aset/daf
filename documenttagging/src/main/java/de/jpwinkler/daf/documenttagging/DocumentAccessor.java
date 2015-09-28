package de.jpwinkler.daf.documenttagging;

import java.util.List;
import java.util.function.Consumer;

/**
 * A {@link DocumentAccessor} acts as an abstraction of documents to be tagged
 * by a {@link DocumentTaggingAlgorithm}.
 *
 * @author jonwink
 *
 * @param <E>
 *            Type of the nodes within the document.
 */
public abstract class DocumentAccessor<E> {

    /**
     * Returns the root node of the document. The root node must be a
     * representation of the document itself, not one of its elements.
     *
     * @return
     */
    public abstract E getDocumentRoot();

    /**
     * Returns the children of a given node.
     *
     * @param element
     * @return
     */
    public abstract List<E> getChildren(E element);

    /**
     * Returns the parent of a given node.
     *
     * @param element
     * @return
     */
    public abstract E getParent(E element);

    /**
     * Returns the node that precedes the given node. The returned node and the
     * submitted node must have the same parent node.
     *
     * @param element
     * @return
     */
    public abstract E getPrevious(E element);

    /**
     * Returns the node that succeeds the given node. The returned node and the
     * submitted node must have the same parent node.
     *
     * @param element
     * @return
     */
    public abstract E getNext(E element);

    /**
     * Traverses the document using pre-order traversal and executes the given
     * visitor for each node in the document, including the root node.
     * 
     * @param node
     * @param visitor
     */
    public void visit(final E node, final Consumer<E> visitor) {
        visitor.accept(node);
        for (final E child : getChildren(node)) {
            visit(child, visitor);
        }
    }

}
