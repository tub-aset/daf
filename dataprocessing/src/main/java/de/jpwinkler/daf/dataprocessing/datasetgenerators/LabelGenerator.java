package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public abstract class LabelGenerator {

    public abstract boolean hasLabel(final DoorsObject object);

    public abstract List<String> getLabels(final DoorsObject object);

}
