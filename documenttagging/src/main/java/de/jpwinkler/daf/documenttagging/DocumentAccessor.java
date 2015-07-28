package de.jpwinkler.daf.documenttagging;

import java.util.List;

public interface DocumentAccessor<T> {

    public T getDocumentRoot();

    public List<T> getChildren(T documentElement);

}
