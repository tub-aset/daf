package de.jpwinkler.daf.documenttagging;

import java.util.List;

public interface DocumentAccessor<ELEMENT> {

    public ELEMENT getDocumentRoot();

    public List<ELEMENT> getChildren(ELEMENT documentElement);

}
