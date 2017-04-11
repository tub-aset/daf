package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

import java.util.ArrayList;
import java.util.List;

public class SentenceMarkup {

    private final List<SentenceMarkupRange> ranges = new ArrayList<>();

    public List<SentenceMarkupRange> getRanges() {
        return ranges;
    }

}
