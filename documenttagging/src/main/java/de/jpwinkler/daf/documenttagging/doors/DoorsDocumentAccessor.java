package de.jpwinkler.daf.documenttagging.doors;

import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.documenttagging.DocumentAccessor;

/**
 * An implementation of {@link DocumentAccessor} that allows DOORS modules to be
 * tagged by a document tagging algorithm.
 *
 * @author jonwink
 *
 */
public class DoorsDocumentAccessor extends DocumentAccessor<DoorsTreeNode> {

    private final DoorsModule doorsModule;

    /**
     * Creates a new instance of this class.
     *
     * @param doorsModule
     *            The DOORS module that this new instance should wrap.
     */
    public DoorsDocumentAccessor(final DoorsModule doorsModule) {
        super();
        this.doorsModule = doorsModule;
    }

    @Override
    public DoorsTreeNode getDocumentRoot() {
        return doorsModule;
    }

    @Override
    public List<DoorsTreeNode> getChildren(final DoorsTreeNode element) {
        return element.getChildren();
    }

    @Override
    public DoorsTreeNode getPrevious(final DoorsTreeNode element) {
        if (element.getParent() != null && !(element.getParent().getChildren().indexOf(element) == 0)) {
            return element.getParent().getChildren().get(element.getParent().getChildren().indexOf(element) - 1);
        } else {
            return null;
        }
    }

    @Override
    public DoorsTreeNode getNext(final DoorsTreeNode element) {
        if (element.getParent() != null && !(element.getParent().getChildren().indexOf(element) == element.getParent().getChildren().size() - 1)) {
            return element.getParent().getChildren().get(element.getParent().getChildren().indexOf(element) + 1);
        } else {
            return null;
        }
    }

    @Override
    public DoorsTreeNode getParent(final DoorsTreeNode element) {
        return element.getParent();
    }

    /**
     * Returns the DOORS module wrapped by this document accessor.
     *
     * @return
     */
    public DoorsModule getDoorsModule() {
        return doorsModule;
    }
}
