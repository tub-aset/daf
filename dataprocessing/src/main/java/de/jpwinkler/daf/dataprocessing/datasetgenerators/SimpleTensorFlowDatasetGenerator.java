package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import de.jpwinkler.daf.dataprocessing.utils.VectorUtils;

public class SimpleTensorFlowDatasetGenerator extends DatasetGenerator {

    public SimpleTensorFlowDatasetGenerator(final LabelGenerator labelGenerator, final boolean unique) {
        super(labelGenerator, unique);
    }

    @Override
    protected void addDatasetRecord(final OutputStream stream, final double[] featureVector, final String outcome) throws IOException {
        final double[] result = VectorUtils.oneHotVector(getLabels().get(outcome), getLabels().size());

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
    protected void addMultiClassDatasetRecord(final OutputStream stream, final double[] featureVector, final List<String> outcome) throws IOException {
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
}
