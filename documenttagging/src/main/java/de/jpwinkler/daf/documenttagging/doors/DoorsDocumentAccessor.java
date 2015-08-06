package de.jpwinkler.daf.documenttagging.doors;

import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.documenttagging.DocumentAccessor;

public class DoorsDocumentAccessor extends DocumentAccessor<DoorsTreeNode> {

    private final DoorsModule doorsModule;

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
        return element.getObjects();
    }

    @Override
    public DoorsTreeNode getPrevious(final DoorsTreeNode element) {
        if (element.getParent() != null && !(element.getParent().getObjects().indexOf(element) == 0)) {
            return element.getParent().getObjects().get(element.getParent().getObjects().indexOf(element) - 1);
        } else {
            return null;
        }
    }

    @Override
    public DoorsTreeNode getNext(final DoorsTreeNode element) {
        if (element.getParent() != null && !(element.getParent().getObjects().indexOf(element) == element.getParent().getObjects().size() - 1)) {
            return element.getParent().getObjects().get(element.getParent().getObjects().indexOf(element) + 1);
        } else {
            return null;
        }
    }

    @Override
    public DoorsTreeNode getParent(final DoorsTreeNode element) {
        return element.getParent();
    }
}
