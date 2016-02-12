package de.jpwinkler.daf.dataprocessing;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPOutputStream;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.DatasetGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.FilteringLabelGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.SimpleTensorFlowDatasetGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.FeatureVectorGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.WordFeatureGenerator;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleFolderCSVSpliterator;

public class DatasetGenerationApp {

    private static final int CUTOFF = 10;
    private static final int MAX_N_GRAM_LENGTH = 1;
    private static final int MIN_WORD_LENGTH = 2;

    private static final String OUTPUT_FILENAME = "dataset.gz";

    private static final Logger LOGGER = Logger.getLogger(DatasetGenerationApp.class.getName());

    private static Iterator<DoorsObject> getIterator() {
        return StreamSupport.stream(new SimpleFolderCSVSpliterator(new File("C:\\WORK\\DOORS\\export\\body\\comp\\de"), true), false).iterator();
    }

    public static void main(final String[] args) throws IOException {

        LOGGER.info("Initializing dataset generator...");

        final List<String> allowedLabels = Arrays.asList("information", "requirement");

        final FeatureVectorGenerator<DoorsObject, String> featureVectorGenerator = new FeatureVectorGenerator<>(0);
        featureVectorGenerator.addFeatureGenerator(new WordFeatureGenerator(MAX_N_GRAM_LENGTH, MIN_WORD_LENGTH));

        final DatasetGenerator<DoorsObject, String> tfDatasetGenerator = new SimpleTensorFlowDatasetGenerator(new FilteringLabelGenerator("Object Type", allowedLabels), true);
        tfDatasetGenerator.init(getIterator(), featureVectorGenerator);

        LOGGER.info("Dataset initialization done.");
        LOGGER.info("Total amount of features: " + featureVectorGenerator.getFeatureCount());
        LOGGER.info("List of outcomes: " + tfDatasetGenerator.getLabels().keySet());

        LOGGER.info("generating dataset with cutoff: " + CUTOFF);
        featureVectorGenerator.setCutoff(CUTOFF);
        featureVectorGenerator.buildFeatureIndexMap();
        featureVectorGenerator.printFeatureStatistics();
        LOGGER.info("amount of features after cutoff: " + featureVectorGenerator.getFeatureCount());
        try (OutputStream stream = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(OUTPUT_FILENAME)))) {
            tfDatasetGenerator.generateDataset(getIterator(), stream);
        }
        // }
    }

}
