package de.jpwinkler.daf.documenttagging.algorithmrunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.documenttagging.ConfusionMatrix;
import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.DocumentTaggingAlgorithm;
import de.jpwinkler.daf.documenttagging.TaggedDocument;
import de.jpwinkler.daf.documenttagging.doors.DoorsDocumentAccessor;
import de.jpwinkler.daf.documenttagging.doors.maxent.DoorsMaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.GrowRateFunction;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.HyperMarkovChain;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.HyperMarkovChainBuilder;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.AbstractSmoothingTechnique;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.SmoothingTechnique;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.SmoothingTechniqueFactory;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntRecursiveViterbiAlgorithm;
import de.jpwinkler.daf.documenttagging.maxent.TrainingDataEventStream;
import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey2;
import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey4;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class AlgorithmRunner {

    private static final Logger LOGGER = Logger.getLogger(AlgorithmRunner.class.getName());

    private final List<String> trainingModuleFileNames = new ArrayList<>();
    private String testModuleFileName;

    private final List<Integer> gisCutOffValues = new ArrayList<>();
    private final List<Integer> gisIterationValues = new ArrayList<>();
    private final List<GrowRateFunction> growRateFunctionValues = new ArrayList<>();
    private final List<SmoothingConfiguration> smoothingConfigurations = new ArrayList<>();
    private File resultStorageFile;

    public AlgorithmResultStorage run() throws IOException, CSVParseException {

        // Prepare documents
        LOGGER.info("preparing documents");
        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag");
        final ModuleCSVParser csvParser = new ModuleCSVParser();
        final List<DoorsDocumentAccessor> trainingModuleAccessors = new ArrayList<>();
        for (final String trainingModuleFileName : trainingModuleFileNames) {
            LOGGER.info("preparing document " + trainingModuleFileName);
            trainingModuleAccessors.add(new DoorsDocumentAccessor(csvParser.parseCSV(new File(trainingModuleFileName))));
        }
        final DoorsDocumentAccessor testModuleAccessor = new DoorsDocumentAccessor(csvParser.parseCSV(new File(testModuleFileName)));

        // Prepare algorithm configurations
        LOGGER.info("generating algorithm configurations");
        final List<AlgorithmConfiguration> algorithmConfigurations = new ArrayList<>();

        for (final int cutoff : gisCutOffValues) {
            for (final int iterations : gisIterationValues) {
                for (final GrowRateFunction growRateFunction : growRateFunctionValues) {
                    for (final SmoothingConfiguration smoothingConfiguration : smoothingConfigurations) {
                        algorithmConfigurations.add(new AlgorithmConfiguration(testModuleAccessor.getDoorsModule().getName(), "rv maxent", smoothingConfiguration.getSmoothingTechnique(), smoothingConfiguration.getK(), smoothingConfiguration.getD(), cutoff, iterations, growRateFunction));
                    }
                }
            }
        }

        // prepare caches
        final Map<CompositeKey2<Integer, Integer>, GISModel> models = new HashMap<>();
        final Map<CompositeKey4<SmoothingTechnique, Double, Double, GrowRateFunction>, HyperMarkovChain<String>> chains = new HashMap<>();

        // prepare result
        final AlgorithmResultStorage resultStorage = new AlgorithmResultStorage();

        // load previous results
        LOGGER.info("loading previous results");
        final AlgorithmResultStorage resultStorageCache = new AlgorithmResultStorage();
        if (resultStorageFile != null && resultStorageFile.exists()) {
            resultStorageCache.loadFromFile(resultStorageFile);
        }
        boolean resultStorageCacheChanged = false;

        // run algorithms
        LOGGER.info("running algorithm");
        int i = 0;
        for (final AlgorithmConfiguration ac : algorithmConfigurations) {
            try {
                AlgorithmResult algorithmResult = resultStorageCache.get(ac);
                if (algorithmResult == null) {
                    LOGGER.info(ac.toString());
                    algorithmResult = new AlgorithmResult();
                    algorithmResult.setAlgorithmConfiguration(ac);
                    final AbstractSmoothingTechnique<String> smoothingTechnique = new SmoothingTechniqueFactory<String>(ac.getSmoothingTechnique(), ac.getSmoothingD(), ac.getSmoothingK()).getInstance();

                    GISModel model = models.get(new CompositeKey2<>(ac.getGisIterations(), ac.getGisCutoff()));
                    if (model == null) {
                        final List<DoorsTreeNode> trainingElements = new ArrayList<>();
                        for (final DocumentAccessor<DoorsTreeNode> documentAccessor : trainingModuleAccessors) {
                            documentAccessor.visit(documentAccessor.getDocumentRoot(), e -> trainingElements.add(e));
                        }
                        model = GIS.trainModel(new TrainingDataEventStream<>(generator, trainingElements), ac.getGisIterations(), ac.getGisCutoff());
                        models.put(new CompositeKey2<>(ac.getGisIterations(), ac.getGisCutoff()), model);
                    }
                    HyperMarkovChain<String> hmc = chains.get(new CompositeKey4<>(ac.getSmoothingTechnique(), ac.getSmoothingD(), ac.getSmoothingK(), ac.getGrowRateFunction()));
                    if (hmc == null) {
                        final HyperMarkovChainBuilder<String> hyperMarkovChainBuilder = new HyperMarkovChainBuilder<>(smoothingTechnique, ac.getGrowRateFunction());
                        for (final DocumentAccessor<DoorsTreeNode> documentAccessor : trainingModuleAccessors) {
                            hyperMarkovChainBuilder.addAll(documentAccessor, e -> generator.getOutcome(e), t -> !t.isEmpty());
                        }
                        hmc = hyperMarkovChainBuilder.build();
                        chains.put(new CompositeKey4<>(ac.getSmoothingTechnique(), ac.getSmoothingD(), ac.getSmoothingK(), ac.getGrowRateFunction()), hmc);
                    }

                    final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg = new MaxEntRecursiveViterbiAlgorithm<DoorsTreeNode>(generator, model, hmc);
                    final TaggedDocument<DoorsTreeNode, String> algresult = alg.tagDocument(testModuleAccessor);
                    algorithmResult.setConfusionMatrix(new ConfusionMatrix<>(algresult));
                    resultStorageCache.add(algorithmResult);
                    resultStorageCacheChanged = true;
                }
                resultStorage.add(algorithmResult);
            } catch (final Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
            i++;

            if (i % 100 == 0 && resultStorageFile != null && resultStorageCacheChanged) {
                LOGGER.info("saving results");
                resultStorageCache.saveToFile(resultStorageFile);
            }
        }

        if (resultStorageFile != null && resultStorageCacheChanged) {
            LOGGER.info("saving results");
            resultStorageCache.saveToFile(resultStorageFile);
        }

        return resultStorage;
    }

    public static void main(final String[] args) throws CSVParseException, IOException {

        // GIS.PRINT_MESSAGES = false;
        final String basePath = "C:/WORK/DOORS/export/pod/FO_functions_only_tagged";
        final List<String> trainingModuleFileNames = Arrays.asList(
                "ACAP_217_Content.csv",
                "BCSF_System_Content.csv",
                "HFA_system req.csv",
                "IHTM_Star3_System_Content.csv",
                "Schließung_System_Content.csv",
                "Anforderungen von AbC und AbP an Telematik.csv",
                "Anforderungen von AbC und AbP an Telematik2.csv",
                "Anforderungen von AbC und AbP an Telematik3.csv",
                "Funktionsbeschreibung AbP.csv",
                "Funktionsbeschreibung AbC.csv",
                "TLK_Star3_System_Content.csv").stream().map(s -> basePath + "/" + s).collect(Collectors.toList());
        final String testModuleFileName = trainingModuleFileNames.remove(1);

        final AlgorithmRunner runner = new AlgorithmRunner();
        LOGGER.info("Setting up algorithm runner configuration");
        runner.setResultStorageFile(new File("resultStorage.gz"));

        runner.getGisCutOffValues().add(0);
        // runner.getGisCutOffValues().add(1);
        // runner.getGisCutOffValues().add(10);
        // runner.getGisCutOffValues().add(50);
        // runner.getGisIterationValues().add(10);
        // runner.getGisIterationValues().add(50);
        // runner.getGisIterationValues().add(100);
        // runner.getGisIterationValues().add(500);
        // runner.getGisIterationValues().add(1000);
        runner.getGisIterationValues().add(3000);
        runner.getGrowRateFunctionValues().add(GrowRateFunction.LINEAR);
        // runner.getGrowRateFunctionValues().add(GrowRateFunction.CONSTANT_1);
        // runner.getGrowRateFunctionValues().add(GrowRateFunction.CONSTANT_5);
        // runner.getGrowRateFunctionValues().add(GrowRateFunction.LOG);
        // runner.getGrowRateFunctionValues().add(GrowRateFunction.ROOT_10);
        // runner.getGrowRateFunctionValues().add(GrowRateFunction.ROOT_2);
        // runner.getSmoothingConfigurations().add(new
        // SmoothingConfiguration(SmoothingTechnique.NO_SMOOTHING, 0, 0));
        // runner.getSmoothingConfigurations().add(new
        // SmoothingConfiguration(SmoothingTechnique.ADD_K_SMOOTHING, 0, 1));
        // runner.getSmoothingConfigurations().add(new
        // SmoothingConfiguration(SmoothingTechnique.ADD_K_SMOOTHING, 0, 10));
        // runner.getSmoothingConfigurations().add(new
        // SmoothingConfiguration(SmoothingTechnique.ADD_K_SMOOTHING, 0, 50));
        for (final double d : new double[] { 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9 }) {
            // runner.getSmoothingConfigurations().add(new
            // SmoothingConfiguration(SmoothingTechnique.ABSOLUTE_DISCOUNTING,
            // d, 0));
            runner.getSmoothingConfigurations().add(new SmoothingConfiguration(SmoothingTechnique.KATZ_BACKOFF, d, 0));
            runner.getSmoothingConfigurations().add(new SmoothingConfiguration(SmoothingTechnique.KATZ_BACKOFF, d, 1));
            runner.getSmoothingConfigurations().add(new SmoothingConfiguration(SmoothingTechnique.KATZ_BACKOFF, d, 10));
            runner.getSmoothingConfigurations().add(new SmoothingConfiguration(SmoothingTechnique.KATZ_BACKOFF, d, 30));
            runner.getSmoothingConfigurations().add(new SmoothingConfiguration(SmoothingTechnique.KATZ_BACKOFF, d, 100));
            runner.getSmoothingConfigurations().add(new SmoothingConfiguration(SmoothingTechnique.KATZ_BACKOFF, d, 300));
            runner.getSmoothingConfigurations().add(new SmoothingConfiguration(SmoothingTechnique.KATZ_BACKOFF, d,
                    1000));
            // runner.getSmoothingConfigurations().add(new
            // SmoothingConfiguration(SmoothingTechnique.KNESER_NEY_SMOOTHING,
            // d, 0));
        }

        runner.getTrainingModuleFileNames().addAll(trainingModuleFileNames);
        runner.setTestModuleFileName(testModuleFileName);

        LOGGER.info("Starting algorithm runner");
        final AlgorithmResultStorage results = runner.run();

        System.out.println(results.toString());
    }

    public String getTestModuleFileName() {
        return testModuleFileName;
    }

    public void setTestModuleFileName(final String testModuleFileName) {
        this.testModuleFileName = testModuleFileName;
    }

    public File getResultStorageFile() {
        return resultStorageFile;
    }

    public void setResultStorageFile(final File resultStorageFile) {
        this.resultStorageFile = resultStorageFile;
    }

    public List<String> getTrainingModuleFileNames() {
        return trainingModuleFileNames;
    }

    public List<Integer> getGisCutOffValues() {
        return gisCutOffValues;
    }

    public List<Integer> getGisIterationValues() {
        return gisIterationValues;
    }

    public List<GrowRateFunction> getGrowRateFunctionValues() {
        return growRateFunctionValues;
    }

    public List<SmoothingConfiguration> getSmoothingConfigurations() {
        return smoothingConfigurations;
    }
}
