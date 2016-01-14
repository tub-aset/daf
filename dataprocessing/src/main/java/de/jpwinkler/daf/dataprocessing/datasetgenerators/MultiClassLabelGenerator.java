package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class MultiClassLabelGenerator extends LabelGenerator<DoorsObject> {

    private final String labelAttribute;
    private final String separator;
    private final List<String> allowedLabels;

    public MultiClassLabelGenerator(final String labelAttribute, final List<String> allowedLabels, final String separator) {
        super();
        this.labelAttribute = labelAttribute;
        this.allowedLabels = allowedLabels;
        this.separator = separator;
    }

    @Override
    public boolean hasLabel(final DoorsObject object) {
        return object.getAttributes().containsKey(labelAttribute) && getLabels(object).size() > 0;
    }

    @Override
    public List<String> getLabels(final DoorsObject object) {
        final List<String> asList = Arrays.asList(object.getAttributes().get(labelAttribute).replace("\r", "").trim().split(separator));
        if (allowedLabels != null) {
            return asList.stream().filter(s -> allowedLabels.contains(s)).collect(Collectors.toList());
        } else {
            return asList;
        }
    }

}
