package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import de.jpwinkler.daf.dataprocessing.utils.VectorUtils;

public class SimpleTensorFlowDatasetGenerator extends DatasetGenerator {

    public SimpleTensorFlowDatasetGenerator(final String labelAttribute, final List<String> allowedLabels, final boolean unique) {
        super(labelAttribute, allowedLabels, unique);
    }

    @Override
    protected void addDatasetRecord(final OutputStream stream, final double[] featureVector, final String outcome) throws IOException {
        final double[] result = VectorUtils.oneHotVector(getOutcomes().get(outcome), getOutcomes().size());

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
