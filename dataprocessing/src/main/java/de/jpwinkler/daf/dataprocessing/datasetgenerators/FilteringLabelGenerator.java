package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class FilteringLabelGenerator extends LabelGenerator<DoorsObject> {

    private final String labelAttribute;

    private final List<String> allowedLabels;

    public FilteringLabelGenerator(final String labelAttribute, final List<String> allowedLabels) {
        super();
        this.labelAttribute = labelAttribute;
        this.allowedLabels = allowedLabels;
    }

    @Override
    public boolean hasLabel(final DoorsObject object) {
        return object.getAttributes().containsKey(labelAttribute) && allowedLabels.contains(object.getAttributes().get(labelAttribute));
    }

    @Override
    public List<String> getLabels(final DoorsObject object) {
        final String label = object.getAttributes().get(labelAttribute);
        if (allowedLabels.contains(label)) {
            return Arrays.asList(label);
        } else {
            return Collections.emptyList();
        }
    }

}
