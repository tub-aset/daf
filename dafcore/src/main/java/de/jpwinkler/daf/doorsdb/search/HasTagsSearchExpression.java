package de.jpwinkler.daf.doorsdb.search;

import de.jpwinkler.daf.model.DoorsModule;
import java.util.Arrays;
import java.util.List;

public class HasTagsSearchExpression extends DBSearchExpression {

    private final List<String> tags;

    public HasTagsSearchExpression(final String... tags) {
        this.tags = Arrays.asList(tags);
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return tags.stream().allMatch(tag -> module.hasTag(tag));
    }

}
