package de.jpwinkler.daf.filter.modules;

import de.jpwinkler.daf.model.DoorsModule;
import java.util.Arrays;
import java.util.List;

public class HasTagsSearchExpression extends SearchExpression {

    private final List<String> tags;

    public HasTagsSearchExpression(final String... tags) {
        this.tags = Arrays.asList(tags);
    }

    @Override
    public boolean matches(final DoorsModule module) {
        return tags.stream().allMatch(tag -> module.hasTag(tag));
    }

}
