package de.jpwinkler.daf.documenttagging;

import java.util.List;

public interface DocumentAccessor<E> {

    public E getDocumentRoot();

    public List<E> getChildren(E documentElement);

}
