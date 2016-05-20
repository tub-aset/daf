package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.SparseInstance;

public class ARFFDatasetGenerator<E> extends DatasetGenerator<E, String> {

    private Instances data;
    private Attribute classAttribute;
    private OutputStream stream;

    public ARFFDatasetGenerator(final LabelGenerator<E> labelGenerator, final boolean unique) {
        super(labelGenerator, unique);
    }

    @Override
    protected void setStream(final OutputStream stream) {
        this.stream = stream;
    }

    @Override
    protected void beforeDatasetGeneration() throws IOException {
        final ArrayList<Attribute> attributes = new ArrayList<>();

        for (final String feature : getFeatureVectorGenerator().getSortedFeatureList()) {
            attributes.add(new Attribute(feature));
        }

        final ArrayList<String> classValues = new ArrayList<>(getLabels().keySet());
        if (!classValues.contains("")) {
            classValues.add(0, "");
        }
        classAttribute = new Attribute("$class$", classValues);
        attributes.add(classAttribute);

        data = new Instances("dataset", attributes, 0);
        data.setClass(classAttribute);
    }

    @Override
    protected void addDatasetRecord(final E object, final int[] featureVector, final String outcome) throws IOException {
        final double[] dataVector = new double[featureVector.length+1];
        for (int i = 0; i < featureVector.length; i++) {
            dataVector[i] = featureVector[i];
        }
        dataVector[dataVector.length - 1] = classAttribute.indexOfValue(outcome);
        data.add(new SparseInstance(1, dataVector));
    }

    @Override
    protected void afterDatasetGeneration() throws IOException {
        stream.write(data.toString().getBytes());
    }
}
