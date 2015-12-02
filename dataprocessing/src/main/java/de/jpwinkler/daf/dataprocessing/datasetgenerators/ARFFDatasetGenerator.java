package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.SparseInstance;

public class ARFFDatasetGenerator extends DatasetGenerator {

    private Instances data;
    private Attribute classAttribute;

    public ARFFDatasetGenerator(final String labelAttribute, final List<String> allowedLabels, final boolean unique) {
        super(labelAttribute, allowedLabels, unique);
    }

    @Override
    protected void beforeDatasetGeneration(final OutputStream stream) throws IOException {
        final ArrayList<Attribute> attributes = new ArrayList<>();

        for (final String feature : getFeatureVectorGenerator().getSortedFeatureList()) {
            attributes.add(new Attribute(feature));
        }

        final ArrayList<String> classValues = new ArrayList<>(getOutcomes().keySet());
        classValues.add(0, "");
        classAttribute = new Attribute("$class$", classValues);
        attributes.add(classAttribute);

        data = new Instances("dataset", attributes, 0);
        data.setClass(classAttribute);
    }

    @Override
    protected void addDatasetRecord(final OutputStream stream, final double[] featureVector, final String outcome) throws IOException {
        final double[] dataVector = Arrays.copyOf(featureVector, featureVector.length + 1);
        dataVector[dataVector.length - 1] = classAttribute.indexOfValue(outcome);
        data.add(new SparseInstance(1, dataVector));
    }

    @Override
    protected void afterDatasetGeneration(final OutputStream stream) throws IOException {
        stream.write(data.toString().getBytes());
    }
}
