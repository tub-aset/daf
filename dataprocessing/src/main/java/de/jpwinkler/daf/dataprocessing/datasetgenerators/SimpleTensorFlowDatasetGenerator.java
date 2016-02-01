package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.utils.VectorUtils;

public class SimpleTensorFlowDatasetGenerator extends DatasetGenerator<DoorsObject, String> {

    public SimpleTensorFlowDatasetGenerator(final LabelGenerator<DoorsObject> labelGenerator, final boolean unique) {
        super(labelGenerator, unique);
    }

    private List<String> records;

    @Override
    protected void beforeDatasetGeneration(final OutputStream stream) throws IOException {
        records = new ArrayList<>();
    }

    @Override
    protected void addDatasetRecord(final OutputStream stream, final DoorsObject object, final double[] featureVector, final String outcome) throws IOException {
        final double[] result = VectorUtils.oneHotVector(getLabels().get(outcome), getLabels().size());

        final StringBuilder b = new StringBuilder();

        b.append(object.getObjectIdentifier() + ":");

        for (final double d : featureVector) {
            b.append(d > 0.5 ? "1" : "0");
        }
        b.append(":");
        for (final double d : result) {
            b.append(d > 0.5 ? "1" : "0");
        }
        b.append("\n");

        records.add(b.toString());
    }

    @Override
    protected void addMultiClassDatasetRecord(final OutputStream stream, final DoorsObject object, final double[] featureVector, final List<String> outcome) throws IOException {
        final double[] result = new double[getLabels().size()];

        for (final String label : outcome) {
            result[getLabels().get(label)] = 1;
        }

        final StringBuilder b = new StringBuilder();

        for (final double d : featureVector) {
            b.append(d > 0.5 ? "1" : "0");
        }
        b.append(":");
        for (final double d : result) {
            b.append(d > 0.5 ? "1" : "0");
        }
        b.append("\n");

        stream.write(b.toString().getBytes());
    }

    @Override
    protected void afterDatasetGeneration(final OutputStream stream) throws IOException {
        Collections.shuffle(records);
        for (final String record : records) {
            stream.write(record.getBytes());
        }
    }
}
