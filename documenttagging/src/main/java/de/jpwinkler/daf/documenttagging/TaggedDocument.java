package de.jpwinkler.daf.documenttagging;

import java.util.HashMap;
import java.util.Map;

public class TaggedDocument<S, T extends DocumentElementTag> {

    private final Map<S, T> tags = new HashMap<>();

    private final Map<S, T> tagsPredicted = new HashMap<>();

    public Map<S, T> getTags() {
        return tags;
    }

    public Map<S, T> getTagsPredicted() {
        return tagsPredicted;
    }

}
