package de.jpwinkler.daf.documenttagging.doors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;

public class DoorsTreeNodeIterator implements Iterator<DoorsTreeNode> {

    private final Iterator<DoorsTreeNode> iterator;


    public DoorsTreeNodeIterator(final boolean skipEmptyPodTags, final DoorsTreeNode... roots) {
        final List<DoorsTreeNode> list = new ArrayList<>();
        for (final DoorsTreeNode root : roots) {
            addNodes(root, list, skipEmptyPodTags);
        }
        iterator = list.iterator();
    }

    private void addNodes(final DoorsTreeNode root, final List<DoorsTreeNode> list, final boolean skipEmptyPodTags) {
        root.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                if (!object.getAttributes().get("pod_tags").isEmpty() || !skipEmptyPodTags) {
                    list.add(object);
                }
                return true;
            }
        });
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public DoorsTreeNode next() {
        return iterator.next();
    }

}
