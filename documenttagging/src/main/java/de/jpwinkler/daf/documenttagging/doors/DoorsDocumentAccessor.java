package de.jpwinkler.daf.documenttagging.doors;

import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.documenttagging.DocumentAccessor;

public class DoorsDocumentAccessor implements DocumentAccessor<DoorsTreeNode> {

    private final DoorsModule doorsModule;

    public DoorsDocumentAccessor(final DoorsModule doorsModule) {
        super();
        this.doorsModule = doorsModule;
    }

    public DoorsTreeNode getDocumentRoot() {
        return doorsModule;
    }

    public List<DoorsTreeNode> getChildren(final DoorsTreeNode documentElement) {
        return documentElement.getObjects();
    }

}
