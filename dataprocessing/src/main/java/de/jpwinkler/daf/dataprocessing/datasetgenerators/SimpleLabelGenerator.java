package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class SimpleLabelGenerator extends LabelGenerator<DoorsObject> {

    private final String labelAttribute;

    public SimpleLabelGenerator(final String labelAttribute) {
        super();
        this.labelAttribute = labelAttribute;
    }

    @Override
    public boolean hasLabel(final DoorsObject object) {
        return object.getAttributes().containsKey(labelAttribute) && !object.getAttributes().get(labelAttribute).trim().isEmpty();
    }

    @Override
    public List<String> getLabels(final DoorsObject object) {
        return Arrays.asList(object.getAttributes().get(labelAttribute));
    }

}
