package de.jpwinkler.daf.dataprocessing;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPOutputStream;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.DatasetGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.FilteringLabelGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.SimpleTensorFlowDatasetGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.CutoffFilter;
import de.jpwinkler.daf.dataprocessing.featuregeneration.FeatureVectorGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.NgramOrderFilter;
import de.jpwinkler.daf.dataprocessing.featuregeneration.WordFeatureGenerator;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleFolderCSVSpliterator;

public class DatasetGenerationApp {

    private static final int CUTOFF = 50;
    private static final int MIN_N_GRAM_LENGTH = 1;
    private static final int MAX_N_GRAM_LENGTH = 3;
    private static final int MIN_WORD_LENGTH = 2;

    private static final String OUTPUT_FILENAME = "C:/WORK/ml-tasks/reqinf-comp/datasets/dataset-123gram-c50.tf.gz";

    private static final Logger LOGGER = Logger.getLogger(DatasetGenerationApp.class.getName());

    private static Iterator<DoorsObject> getIterator() {
        return StreamSupport.stream(new SimpleFolderCSVSpliterator(new File("C:/WORK/ml-tasks/reqinf-comp"), false), false).iterator();
    }

    public static void main(final String[] args) throws IOException {

        LOGGER.info("Initializing dataset generator...");

        final FeatureVectorGenerator<DoorsObject, String> featureVectorGenerator = new FeatureVectorGenerator<>();
        featureVectorGenerator.addFeatureFilter(new CutoffFilter<>(CUTOFF));
        featureVectorGenerator.addFeatureFilter(new NgramOrderFilter());
        featureVectorGenerator.addFeatureGenerator(new WordFeatureGenerator(MIN_N_GRAM_LENGTH, MAX_N_GRAM_LENGTH, MIN_WORD_LENGTH));

        final FilteringLabelGenerator labelGenerator = new FilteringLabelGenerator("Object Type", Arrays.asList("information", "requirement"));
        final DatasetGenerator<DoorsObject, String> tfDatasetGenerator = new SimpleTensorFlowDatasetGenerator(labelGenerator, true);
        tfDatasetGenerator.init(getIterator(), featureVectorGenerator);

        LOGGER.info("Dataset initialization done.");
        LOGGER.info("Total amount of features: " + featureVectorGenerator.getFeatureCount());
        LOGGER.info("List of outcomes: " + tfDatasetGenerator.getLabels().keySet());

        featureVectorGenerator.printFeatureStatistics();
        LOGGER.info("amount of features after cutoff: " + featureVectorGenerator.getFeatureCount());
        try (OutputStream stream = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(OUTPUT_FILENAME)))) {
            tfDatasetGenerator.generateDataset(getIterator(), stream);
        }
        // }
    }

}
