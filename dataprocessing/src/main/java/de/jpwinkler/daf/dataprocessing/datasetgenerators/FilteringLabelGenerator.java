package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.jpwinkler.daf.dataprocessing.streaming.SimpleDoorsObject;

public class FilteringLabelGenerator extends LabelGenerator<SimpleDoorsObject> {

    private final String labelAttribute;

    private final List<String> allowedLabels;

    public FilteringLabelGenerator(final String labelAttribute, final List<String> allowedLabels) {
        super();
        this.labelAttribute = labelAttribute;
        this.allowedLabels = allowedLabels;
    }

    @Override
    public boolean hasLabel(final SimpleDoorsObject object) {
        return object.hasAttribute(labelAttribute) && allowedLabels.contains(object.getAttribute(labelAttribute));
    }

    @Override
    public List<String> getLabels(final SimpleDoorsObject object) {
        final String label = object.getAttribute(labelAttribute);
        if (allowedLabels.contains(label)) {
            return Arrays.asList(label);
        } else {
            return Collections.emptyList();
        }
    }

}
