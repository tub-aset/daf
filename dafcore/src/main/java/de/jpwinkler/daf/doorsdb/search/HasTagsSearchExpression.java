package de.jpwinkler.daf.doorsdb.search;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.doorsdb.model.DBModule;

public class HasTagsSearchExpression extends DBSearchExpression {

    private final List<String> tags;

    public HasTagsSearchExpression(final String... tags) {
        this.tags = Arrays.asList(tags);
    }

    @Override
    public boolean matches(final DBModule module) {
        return tags.stream().allMatch(tag -> module.hasTag(tag));
    }

}
