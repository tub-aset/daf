package de.jpwinkler.daf.dataprocessing;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.zip.GZIPOutputStream;

import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.DatasetGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.SimpleTensorFlowDatasetGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.FeatureVectorGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.WordFeatureGenerator;

public class TestMe {

    private DoorsModuleSource source;
    private FeatureVectorGenerator<DoorsObject, String> featureVectorGenerator;

    @Before
    public void init() throws IOException {
        source = new DoorsModulePipelineBuilder()
                .withSource(new CSVFolderSource("C:\\WORK\\DOORS\\export\\body\\comp\\de", true))
                .addDefaultPreprocessors()
                .build();
        featureVectorGenerator = new FeatureVectorGenerator<>(0);
        featureVectorGenerator.addFeatureGenerator(new WordFeatureGenerator(3, 2));
    }

    @Test
    public void test1() throws IOException {


        final int[] cutoffValues = new int[] { 10000, 8000, 5000, 2000, 1000, 800, 500, 200, 100, 80, 50, 20, 10, 8, 5, 2, 1 };

        final DatasetGenerator tfDatasetGenerator = new SimpleTensorFlowDatasetGenerator("Object Type", Arrays.asList("requirement", "information"), true);
        tfDatasetGenerator.init(source, featureVectorGenerator);

        for (final int c : cutoffValues) {
            featureVectorGenerator.setCutoff(c);
            featureVectorGenerator.buildFeatureIndexMap();
            try (OutputStream stream = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(String.format("tf-c%d-de-reqinf-unique.gz", c))))) {
                tfDatasetGenerator.generateDataset(stream);
            }
        }
    }

}
