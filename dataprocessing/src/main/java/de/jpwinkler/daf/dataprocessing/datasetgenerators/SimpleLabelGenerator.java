package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.dataprocessing.streaming.SimpleDoorsObject;

public class SimpleLabelGenerator extends LabelGenerator<SimpleDoorsObject> {

    private final String labelAttribute;

    public SimpleLabelGenerator(final String labelAttribute) {
        super();
        this.labelAttribute = labelAttribute;
    }

    @Override
    public boolean hasLabel(final SimpleDoorsObject object) {
        return object.hasAttribute(labelAttribute) && !object.getAttribute(labelAttribute).trim().isEmpty();
    }

    @Override
    public List<String> getLabels(final SimpleDoorsObject object) {
        return Arrays.asList(object.getAttribute(labelAttribute));
    }

}
