package de.jpwinkler.daf.dataprocessing;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.DatasetGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.MultiClassLabelGenerator;
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


        final int[] cutoffValues = new int[] { 5000, 2000, 1000, 800, 500, 200, 100, 80, 50 };

        System.out.println("Initializing dataset generator...");
        final List<String> allowedLabels = Arrays.asList("Process audit", "Eng. develop. test", "Production control", "Review", "Simulation/Analysis", "Formal verification", "(n.a.)");
        final DatasetGenerator tfDatasetGenerator = new SimpleTensorFlowDatasetGenerator(new MultiClassLabelGenerator("Potential Verification Method", allowedLabels, "\n"), true);
        tfDatasetGenerator.init(source, featureVectorGenerator);

        System.out.println("Dataset initialization done.");
        System.out.println("Total amount of features: " + featureVectorGenerator.getFeatureCount());
        System.out.println("List of outcomes: " + tfDatasetGenerator.getLabels().keySet());

        for (final int c : cutoffValues) {
            System.out.println("generating dataset with cutoff: " + c);
            featureVectorGenerator.setCutoff(c);
            featureVectorGenerator.buildFeatureIndexMap();
            System.out.println("amount of features after cutoff: " +
                    featureVectorGenerator.getFeatureCount());
            try (OutputStream stream = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(String.format("tf-c%d-de-pvm-unique.gz", c))))) {
                tfDatasetGenerator.generateDataset(stream);
            }
        }
    }

}
