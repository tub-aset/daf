package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.IOException;
import java.io.OutputStream;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class EmergentDatasetGenerator extends DatasetGenerator<DoorsObject, String> {

    public EmergentDatasetGenerator(final LabelGenerator<DoorsObject> labelGenerator, final boolean unique) {
        super(labelGenerator, unique);
    }

    @Override
    protected void beforeDatasetGeneration(final OutputStream stream) throws IOException {
        final StringBuilder sb = new StringBuilder();
        sb.append("_H:\t$Name\t");
        for (int i = 0; i < getFeatureVectorGenerator().getFeatureCount(); i++) {
            sb.append("%Input[2:" + i + ",0]");
            if (i == 0) {
                sb.append("<2:" + getFeatureVectorGenerator().getFeatureCount() + ",1>");
            }
            sb.append("\t");
        }
        for (int i = 0; i < getLabels().size(); i++) {
            sb.append("%Output[2:" + i + ",0]");
            if (i == 0) {
                sb.append("<2:" + getLabels().size() + ",1>");
            }
            if (i != getLabels().size() - 1) {
                sb.append("\t");
            }
        }
        sb.append("\n");
        stream.write(sb.toString().getBytes());
    }

    @Override
    protected void addDatasetRecord(final OutputStream stream, final DoorsObject object, final double[] featureVector, final String outcome) throws IOException {
        final StringBuilder sb = new StringBuilder();
        sb.append("_D:\t\"" + object.getObjectIdentifier() + "\"\t");
        for (final double d : featureVector) {
            sb.append(d);
            sb.append("\t");
        }
        for (int i = 0; i < getLabels().size(); i++) {
            if (i == getLabels().get(outcome)) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            if (i != getLabels().size() - 1) {
                sb.append("\t");
            }
        }
        sb.append("\n");
        stream.write(sb.toString().getBytes());
    }
}
