package de.jpwinkler.daf.dataprocessing;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPOutputStream;

import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dataprocessing.datasetgenerators.ARFFDatasetGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.DatasetGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.FilteringLabelGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.LabelGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.SimpleLabelGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.SimpleTensorFlowDatasetGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.FeatureVectorGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.ParseTreeFeatureGenerator;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleDoorsObject;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleFolderCSVSpliterator;

public class TestMe {

    private FeatureVectorGenerator<SimpleDoorsObject, String> featureVectorGenerator;

    @Before
    public void init() throws IOException {
        // source = new DoorsModulePipelineBuilder()
        // .withSource(new
        // CSVFolderSource("C:\\WORK\\DOORS\\export\\body\\comp\\en", true))
        // .addDefaultPreprocessors()
        // .build();
        featureVectorGenerator = new FeatureVectorGenerator<>(0);
        // featureVectorGenerator.addFeatureGenerator(new
        // WordFeatureGenerator(1, 2));
        featureVectorGenerator.addFeatureGenerator(new ParseTreeFeatureGenerator());
    }

    // @Test
    public void test1() throws IOException {

        // final int[] cutoffValues = new int[] { 5000, 2000, 1000, 800, 500,
        // 200, 100, 80, 50 };

        System.out.println("Initializing dataset generator...");
        // final List<String> allowedLabels = Arrays.asList("Process audit",
        // "Eng. develop. test", "Production control", "Review",
        // "Simulation/Analysis", "Formal verification", "(n.a.)");
        final List<String> allowedLabels = Arrays.asList("information", "requirement");
        final DatasetGenerator<SimpleDoorsObject, String> tfDatasetGenerator = new SimpleTensorFlowDatasetGenerator(new FilteringLabelGenerator("Object Type", allowedLabels), true);
        tfDatasetGenerator.init(getIterator(), featureVectorGenerator);

        System.out.println("Dataset initialization done.");
        System.out.println("Total amount of features: " + featureVectorGenerator.getFeatureCount());
        System.out.println("List of outcomes: " + tfDatasetGenerator.getLabels().keySet());

        // for (final int c : cutoffValues) {
        final int c = 100;
        System.out.println("generating dataset with cutoff: " + c);
        featureVectorGenerator.setCutoff(c);
        featureVectorGenerator.buildFeatureIndexMap();
        featureVectorGenerator.printFeatureStatistics();
        System.out.println("amount of features after cutoff: " + featureVectorGenerator.getFeatureCount());
        try (OutputStream stream = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(String.format("dong.gz", c))))) {
            tfDatasetGenerator.generateDataset(getIterator(), stream);
        }
        // }
    }

    private Iterator<SimpleDoorsObject> getIterator() throws IOException {
        return StreamSupport.stream(new SimpleFolderCSVSpliterator(new File("C:\\WORK\\DOORS\\export\\body\\comp\\de-test\\reqinf"), true), false).iterator();
    }

    // @Test
    public void test4() throws IOException {
        final LabelGenerator<SimpleDoorsObject> labelGenerator = new SimpleLabelGenerator("Object Type");
        final DatasetGenerator<SimpleDoorsObject, String> datasetGenerator = new ARFFDatasetGenerator(labelGenerator, true);

        datasetGenerator.init(getIterator(), featureVectorGenerator);

        featureVectorGenerator.setCutoff(10);
        featureVectorGenerator.buildFeatureIndexMap();
        featureVectorGenerator.printFeatureStatistics();
        try (OutputStream stream = new BufferedOutputStream(new FileOutputStream("reqinf.arff"))) {
            datasetGenerator.generateDataset(getIterator(), stream);
        }
    }

    @Test
    public void test5() throws IOException {

        final ParseTreeFeatureGenerator featureGenerator = new ParseTreeFeatureGenerator();
        final SimpleDoorsObject object = new SimpleDoorsObject();
        object.setObjectText("Lieferungen oder Leistungen des Auftragnehmers dürfen FOSS nur nach Zustimmung durch den Auftraggeber und unter Einhaltung des in X beschriebenen Verfahrens enthalten.");

        for (final String feature : featureGenerator.getFeatures(object)) {
            System.out.println(feature);
        }
    }

}
