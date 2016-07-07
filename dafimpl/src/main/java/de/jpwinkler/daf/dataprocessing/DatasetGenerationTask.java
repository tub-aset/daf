package de.jpwinkler.daf.dataprocessing;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.ARFFDatasetGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.DatasetGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.ElementSink;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.LabelGenerator;
import de.jpwinkler.daf.dataprocessing.datasetgenerators.SimpleLabelGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.CharacterFeatureGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.CutoffFilter;
import de.jpwinkler.daf.dataprocessing.featuregeneration.FeatureVectorGenerator;
import de.jpwinkler.daf.dataprocessing.featuregeneration.ObjectTextFeatureGenerator;
import de.jpwinkler.daf.doorsdb.tasks.ModuleListSource;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class DatasetGenerationTask {
    private static final List<String> MODULES = Arrays.asList(
            "/Body/B2 - Components/ALC/ALC/ALC_Component Req");
    // "/Body/B2 - Components/AS/AS_213/AS_213_Component Req",
    // "/Body/B2 - Components/BTS/BTS213/BTS_213_Component Req",
    // "/Body/B2 - Components/DCDC/DCDC-48V222/DCDC-48V_Component Req",
    // "/Body/B2 - Components/SAM_R/BC_R222/BC_R222_component req");

    private static final int CUTOFF = 50;

    private static final String OUTPUT_FILENAME = "C:/WORK/alc-st.arff";

    private static final Logger LOGGER = Logger.getLogger(DatasetGenerationTask.class.getName());

    private static class Pass1 extends ObjectCSVPass {

        private ElementSink<DoorsObject> init;
        private final DatasetGenerator<DoorsObject, String> datasetGenerator;
        private final FeatureVectorGenerator<DoorsObject, String> featureVectorGenerator;

        public Pass1(final DatasetGenerator<DoorsObject, String> datasetGenerator, final FeatureVectorGenerator<DoorsObject, String> featureVectorGenerator) {
            this.datasetGenerator = datasetGenerator;
            this.featureVectorGenerator = featureVectorGenerator;
        }

        @Override
        public void preprocess() {
            try {
                init = datasetGenerator.init(featureVectorGenerator);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void processObject(final DoorsObject object) {
            init.add(object);
        }

        @Override
        public void postprocess() {
            try {
                init.finish();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            LOGGER.info("Dataset initialization done.");
            LOGGER.info("Total amount of features: " + featureVectorGenerator.getFeatureCount());
            LOGGER.info("List of outcomes: " + datasetGenerator.getLabels().keySet());

            featureVectorGenerator.printFeatureStatistics();
            LOGGER.info("amount of features after cutoff: " + featureVectorGenerator.getFeatureCount());
        }
    }

    private static class Pass2 extends ObjectCSVPass {

        private final DatasetGenerator<DoorsObject, String> datasetGenerator;
        private ElementSink<DoorsObject> generateDataset;
        private OutputStream stream;

        public Pass2(final DatasetGenerator<DoorsObject, String> datasetGenerator) {
            this.datasetGenerator = datasetGenerator;
        }

        @Override
        public void preprocess() {
            try {
                stream = new BufferedOutputStream(new FileOutputStream(OUTPUT_FILENAME));
                generateDataset = datasetGenerator.generateDataset(stream);
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        protected void processObject(final DoorsObject object) {
            generateDataset.add(object);
        }

        @Override
        public void postprocess() {
            try {
                generateDataset.finish();
                stream.close();
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(final String[] args) throws IOException {
        DatasetGenerator<DoorsObject, String> datasetGenerator;
        FeatureVectorGenerator<DoorsObject, String> featureVectorGenerator;

        featureVectorGenerator = new FeatureVectorGenerator<>();
        featureVectorGenerator.addFeatureFilter(new CutoffFilter<>(CUTOFF));
        featureVectorGenerator.addFeatureGenerator(new ObjectTextFeatureGenerator<>(new CharacterFeatureGenerator(3, 4)));

        final LabelGenerator<DoorsObject> labelGenerator = new SimpleLabelGenerator("__structural_type_ref");
        datasetGenerator = new ARFFDatasetGenerator<>(labelGenerator, true);

        new ModuleTaskBuilder()
        .withPass(new Pass1(datasetGenerator, featureVectorGenerator))
        .withPass(new Pass2(datasetGenerator))
        .withSource(new ModuleListSource(MODULES))
        .buildAndRun();

    }

}
