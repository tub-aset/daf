package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.VectorUtils;

public class FancyDatasetGenerator extends DatasetGenerator<DoorsObject, String> {

    private DataOutputStream dos;

    public FancyDatasetGenerator(final LabelGenerator<DoorsObject> labelGenerator, final boolean unique) {
        super(labelGenerator, unique);
    }

    @Override
    protected void setStream(final OutputStream stream) {
        dos = new DataOutputStream(stream);
    }

    @Override
    protected void beforeDatasetGeneration() throws IOException {
        dos.writeInt(getFeatureVectorGenerator().getFeatureCount());
        dos.writeInt(getLabels().size());
    }

    @Override
    protected void addDatasetRecord(final DoorsObject object, final int[] featureVector, final String outcome) throws IOException {
        for (final int i : featureVector) {
            dos.writeByte(i);
        }

        for (final int i : VectorUtils.oneHotVector(getLabels().get(outcome), getLabels().size())) {
            dos.writeByte(i);
        }
    }

}
