package de.jpwinkler.daf.documenttagging;

import java.util.List;
import java.util.function.Consumer;

public abstract class DocumentAccessor<E> {

    public abstract E getDocumentRoot();

    public abstract List<E> getChildren(E documentElement);

    public void visit(final E node, final Consumer<E> visitor) {
        visitor.accept(node);
        for (final E child : getChildren(node)) {
            visit(child, visitor);
        }
    }

}
