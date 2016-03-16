package de.jpwinkler.daf.dataprocessing.datasetgenerators;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.utils.VectorUtils;

public class FancyDatasetGenerator extends DatasetGenerator<DoorsObject, String> {

	private DataOutputStream dos;

	public FancyDatasetGenerator(LabelGenerator<DoorsObject> labelGenerator, boolean unique) {
		super(labelGenerator, unique);
	}

	@Override
	protected void setStream(OutputStream stream) {
		this.dos = new DataOutputStream(stream);
	}

	@Override
	protected void beforeDatasetGeneration() throws IOException {
		dos.writeInt(getFeatureVectorGenerator().getFeatureCount());
		dos.writeInt(getLabels().size());
	}
	
	@Override
	protected void addDatasetRecord(DoorsObject object, int[] featureVector, String outcome) throws IOException {
		for (int i : featureVector) {
			dos.writeInt(i);
		}
		
		for (int i : VectorUtils.oneHotVector(getLabels().get(outcome), getLabels().size())) {
			dos.writeInt(i);
		}
	}
	
}
