package de.jpwinkler.daf.dataprocessing;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dataprocessing.datasetgenerators.DatasetGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.FilteringLabelGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.LabelGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.SimpleTensorFlowDatasetGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.FeatureVectorGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.ParseTreeFeatureGenerator;
import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleDoorsObject;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleFolderCSVSpliterator;
import de.jpwinkler.libs.stringprocessing.patternprogram.PatternProgram;

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
        // WordFeatureGenerator(4, 2));
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
        return StreamSupport.stream(new SimpleFolderCSVSpliterator(new File("C:\\WORK\\DOORS\\export\\body\\comp\\de-sort\\6.2.3"), true), false).filter(o -> !o.getAttribute("SourceID").startsWith("STLH")).iterator();
    }

    @Test
    public void test4() throws IOException {
        final List<String> allowedLabels = Arrays.asList("information", "requirement");
        final LabelGenerator<SimpleDoorsObject> labelGenerator = new FilteringLabelGenerator("Object Type", allowedLabels);
        final SimpleTensorFlowDatasetGenerator datasetGenerator = new SimpleTensorFlowDatasetGenerator(labelGenerator, true);

        datasetGenerator.init(getIterator(), featureVectorGenerator);

        featureVectorGenerator.setCutoff(50);
        featureVectorGenerator.buildFeatureIndexMap();
        featureVectorGenerator.printFeatureStatistics();
        try (OutputStream stream = new BufferedOutputStream(new FileOutputStream("dong.tf"))) {
            datasetGenerator.generateDataset(getIterator(), stream);
        }
    }

    // @Test
    public void test3() throws IOException {

        final ObjectTextPreprocessor objectTextPreprocessor = new ObjectTextPreprocessor(PatternProgram.compile(FileUtils.readFileToString(new File("preprocess.pp"))));

        // objectTextPreprocessor.getPreprocessingActions().add(new
        // PreprocessingAction(, new ConcatReplaceAction(, "")));

        final PrintWriter pr = new PrintWriter(new FileOutputStream("r.txt"));

        final Iterator<SimpleDoorsObject> stream = getIterator();

        final Set<Integer> objectHashCodes = new HashSet<>();

        while (stream.hasNext()) {
            final SimpleDoorsObject o = stream.next();

            final String preprocessedText = objectTextPreprocessor.preprocessTextToString(o.getObjectText());
            if (!preprocessedText.trim().isEmpty() && !objectHashCodes.contains(preprocessedText.hashCode())) {
                // pr.write("----- " + o.getObjectIdentifier() + " -----\n");
                // pr.write(o.getObjectText() + "\n>>>>>\n");
                pr.write(preprocessedText + "\n");
                objectHashCodes.add(preprocessedText.hashCode());
            }
        }

        pr.close();

    }

}
