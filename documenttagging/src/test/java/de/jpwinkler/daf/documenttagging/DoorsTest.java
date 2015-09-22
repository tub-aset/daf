package de.jpwinkler.daf.documenttagging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.documenttagging.doors.DoorsDocumentAccessor;
import de.jpwinkler.daf.documenttagging.doors.maxent.DoorsMaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.doors.preprocessing.DoorsModulePreprocessor;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.GrowRateFunction;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.HyperMarkovChain;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.HyperMarkovChainBuilder;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.AbstractSmoothingTechnique;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.NoSmoothing;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.SmoothingTechnique;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.SmoothingTechniqueFactory;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntRecursiveViterbiAlgorithm;
import de.jpwinkler.daf.documenttagging.maxent.SimpleMaxEntAlgorithm;
import de.jpwinkler.daf.documenttagging.maxent.TrainingDataEventStream;
import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey2;
import de.jpwinkler.daf.documenttagging.maxent.util.CompositeKey4;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;

public class DoorsTest {

    private static final String[] TEST_MODULE_NAMES = new String[] { "maxent/slh-wwc.csv", "maxent/slh-wl.csv", "maxent/slh-wl2.csv" };

    private static final String PROD_BASE_PATH = "C:/WORK/DOORS/export/pod/FO_functions_only_tagged";

    private static final File RESULT_CACHE_FILE = new File("resultCache");

    private static final String[] PROD_MODULE_NAMES = new String[] {
            "ACAP_217_Content.csv",
            "BCSF_System_Content.csv",
            "HFA_system req.csv",
            "IHTM_Star3_System_Content.csv",
            "Schließung_System_Content.csv",
            "Anforderungen von AbC und AbP an Telematik.csv",
            "Anforderungen von AbC und AbP an Telematik2.csv",
            "Anforderungen von AbC und AbP an Telematik3.csv",
            "Funktionsbeschreibung AbC.csv",
            "Funktionsbeschreibung AbP.csv",
    };

    private Map<String, DoorsModule> testModules;
    private Map<String, DoorsModule> prodModules;

    @Before
    public void setupClass() throws IOException, CSVParseException {
        testModules = new LinkedHashMap<>();
        prodModules = new LinkedHashMap<>();

        final ModuleCSVParser moduleCSVParser = new ModuleCSVParser();
        final DoorsModulePreprocessor preprocessor = DoorsModulePreprocessor.getDefaultPreprocessor();

        for (final String testModuleName : TEST_MODULE_NAMES) {
            final DoorsModule module = moduleCSVParser.parseCSV(getClass().getResourceAsStream(testModuleName));
            preprocessor.preprocessModule(module);
            testModules.put(new File(testModuleName).getName(), module);
        }

        for (final String prodModuleName : PROD_MODULE_NAMES) {
            final DoorsModule module = moduleCSVParser.parseCSV(new File(PROD_BASE_PATH, prodModuleName));
            preprocessor.preprocessModule(module);
            prodModules.put(prodModuleName, module);
        }

        GIS.PRINT_MESSAGES = false;
    }

    // @Test
    public void test4() throws Exception {
        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag");
        final List<DocumentAccessor<DoorsTreeNode>> documentAccessors = prodModules.values().stream().map(m -> new DoorsDocumentAccessor(m)).collect(Collectors.toList());
        final DocumentAccessor<DoorsTreeNode> documentToTag = documentAccessors.get(7);

        final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg1 = new MaxEntRecursiveViterbiAlgorithm<DoorsTreeNode>(generator, documentAccessors, new NoSmoothing<>(), GrowRateFunction.LINEAR, 1000, 0);
        final TaggedDocument<DoorsTreeNode, String> algResult1 = alg1.tagDocument(documentToTag);
        final ConfusionMatrix<String> confusionMatrix1 = new ConfusionMatrix<>(algResult1);

        final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg2 = new SimpleMaxEntAlgorithm<DoorsTreeNode>(generator, documentAccessors, 1000, 0);
        final TaggedDocument<DoorsTreeNode, String> algResult2 = alg2.tagDocument(documentToTag);
        final ConfusionMatrix<String> confusionMatrix2 = new ConfusionMatrix<>(algResult2);

        System.out.println(confusionMatrix1.toStringEvaluationMetrics());
        System.out.println(confusionMatrix2.toStringEvaluationMetrics());
    }

    // @Test
    public void test3() throws Exception {
        // Prepare documents
        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag");
        final List<DocumentAccessor<DoorsTreeNode>> documentAccessors = prodModules.values().stream().map(m -> new DoorsDocumentAccessor(m)).collect(Collectors.toList());
        final DocumentAccessor<DoorsTreeNode> documentToTag = documentAccessors.remove(0);

        // Prepare algorithm configurations
        final int[] cutoffSettings = new int[] { 0, 1, 10, 50 };
        final int[] iterationsSettings = new int[] { 10, 100, 1000 };
        final GrowRateFunction[] growRateSettings = new GrowRateFunction[] { GrowRateFunction.LINEAR, GrowRateFunction.CONSTANT_1, GrowRateFunction.CONSTANT_5, GrowRateFunction.LOG, GrowRateFunction.ROOT_10, GrowRateFunction.ROOT_2 };

        final List<AlgorithmConfiguration> algorithmConfigurations = new ArrayList<>();

        for (final int cutoff : cutoffSettings) {
            for (final int iterations : iterationsSettings) {
                for (final GrowRateFunction growRateFunction : growRateSettings) {
                    for (double d = 0.9; d > 0.05; d -= 0.1) {
                        algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.KATZ_BACKOFF, 0, d, cutoff, iterations, growRateFunction));
                        algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.KATZ_BACKOFF, 10, d, cutoff, iterations, growRateFunction));
                        algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.KNESER_NEY_SMOOTHING, 0, d, cutoff, iterations, growRateFunction));
                        algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.VERY_STUPID_BACKOFF, 0, d, cutoff, iterations, growRateFunction));
                        algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.VERY_STUPID_BACKOFF, 10, d, cutoff, iterations, growRateFunction));
                    }
                    algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.NO_SMOOTHING, 0, 0, cutoff, iterations, growRateFunction));
                    algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.ABSOLUTE_DISCOUNTING, 0, 0.75, cutoff, iterations, growRateFunction));
                    algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.ABSOLUTE_DISCOUNTING, 0, 0.5, cutoff, iterations, growRateFunction));
                    algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.ABSOLUTE_DISCOUNTING, 0, 0.25, cutoff, iterations, growRateFunction));
                    algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.ADD_K_SMOOTHING, 1, 0, cutoff, iterations, growRateFunction));
                    algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.ADD_K_SMOOTHING, 2, 0, cutoff, iterations, growRateFunction));
                    algorithmConfigurations.add(new AlgorithmConfiguration(SmoothingTechnique.ADD_K_SMOOTHING, 10, 0, cutoff, iterations, growRateFunction));
                }
            }
        }

        // prepare caches
        final Map<CompositeKey2<Integer, Integer>, GISModel> models = Collections.synchronizedMap(new HashMap<>());
        final Map<CompositeKey4<SmoothingTechnique, Double, Double, GrowRateFunction>, HyperMarkovChain<String>> chains = Collections.synchronizedMap(new HashMap<>());

        // load previous results
        final Map<AlgorithmConfiguration, ConfusionMatrix<String>> resultCache;
        if (RESULT_CACHE_FILE.exists()) {
            resultCache = (Map<AlgorithmConfiguration, ConfusionMatrix<String>>) new ObjectInputStream(new FileInputStream(RESULT_CACHE_FILE)).readObject();
        } else {
            resultCache = new HashMap<>();
        }

        // run algorithms
        final List<String> results = Collections.synchronizedList(new ArrayList<>());
        algorithmConfigurations.stream().forEach(ac -> {
            try {
                final ConfusionMatrix<String> confusionMatrix;
                if (resultCache.containsKey(ac)) {
                    confusionMatrix = resultCache.get(ac);
                } else {

                    System.out.println(ac);
                    final AbstractSmoothingTechnique<String> smoothingTechnique = new SmoothingTechniqueFactory<String>(ac.getSmoothingTechnique(), ac.getSmoothingD(), ac.getSmoothingK()).getInstance();

                    GISModel model = models.get(new CompositeKey2<>(ac.getGisIterations(), ac.getGisCutoff()));
                    if (model == null) {
                        final List<DoorsTreeNode> trainingElements = new ArrayList<>();
                        for (final DocumentAccessor<DoorsTreeNode> documentAccessor : documentAccessors) {
                            documentAccessor.visit(documentAccessor.getDocumentRoot(), e -> trainingElements.add(e));
                        }
                        model = GIS.trainModel(new TrainingDataEventStream<>(generator, trainingElements), ac.getGisIterations(), ac.getGisCutoff());
                        models.put(new CompositeKey2<>(ac.getGisIterations(), ac.getGisCutoff()), model);
                    }
                    HyperMarkovChain<String> hmc = chains.get(new CompositeKey4<>(ac.getSmoothingTechnique(), ac.getSmoothingD(), ac.getSmoothingK(), ac.getGrowRateFunction()));
                    if (hmc == null) {
                        final HyperMarkovChainBuilder<String> hyperMarkovChainBuilder = new HyperMarkovChainBuilder<>(smoothingTechnique, ac.getGrowRateFunction());
                        for (final DocumentAccessor<DoorsTreeNode> documentAccessor : documentAccessors) {
                            hyperMarkovChainBuilder.addAll(documentAccessor, e -> generator.getOutcome(e), t -> !t.isEmpty());
                        }
                        hmc = hyperMarkovChainBuilder.build();
                        chains.put(new CompositeKey4<>(ac.getSmoothingTechnique(), ac.getSmoothingD(), ac.getSmoothingK(), ac.getGrowRateFunction()), hmc);
                    }

                    final DocumentTaggingAlgorithm<DoorsTreeNode, String> alg = new MaxEntRecursiveViterbiAlgorithm<DoorsTreeNode>(generator, model, hmc);
                    final TaggedDocument<DoorsTreeNode, String> algresult = alg.tagDocument(documentToTag);
                    confusionMatrix = new ConfusionMatrix<>(algresult);
                    resultCache.put(ac, confusionMatrix);

                }

                results.add("rv maxent;" + ac.getGisIterations() + ";" + ac.getGisCutoff() + ";" + ac.getSmoothingTechnique() + ";" + ac.getSmoothingD() + ";" + ac.getSmoothingK() + ";" + ac.getGrowRateFunction() + ";" + confusionMatrix.getMacroF1Score() + ";" + confusionMatrix.getMicroF1Score());
            } catch (final Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(RESULT_CACHE_FILE));
        objectOutputStream.writeObject(resultCache);
        objectOutputStream.close();

        for (final String s : results) {
            System.out.println(s);
        }

    }

    @Test
    public void testMarkovChainBuilder() {
        final MaxEntPredicateGenerator<DoorsTreeNode> generator = DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag");
        final HyperMarkovChainBuilder<String> hyperMarkovChainBuilder = new HyperMarkovChainBuilder<>(new NoSmoothing<>(), GrowRateFunction.LINEAR);

        for (final DoorsModule module : prodModules.values()) {

            hyperMarkovChainBuilder.addAll(new DoorsDocumentAccessor(module), o -> generator.getOutcome(o), t -> !t.isEmpty());
        }

        System.out.println(hyperMarkovChainBuilder.build().getDOT(0.0));
    }

    private void printEvaluationMetrics(final ConfusionMatrix<String> simpleMaxEntConfusionMatrix, final ConfusionMatrix<String> maxEntRecursiveViterbiConfusionMatrix) {
        System.out.println(String.format("simple macro p/r/f1: %f  %f  %f", simpleMaxEntConfusionMatrix.getMacroPrecision(), simpleMaxEntConfusionMatrix.getMacroRecall(), simpleMaxEntConfusionMatrix.getMacroF1Score()));
        System.out.println(String.format("    rv macro p/r/f1: %f  %f  %f", maxEntRecursiveViterbiConfusionMatrix.getMacroPrecision(), maxEntRecursiveViterbiConfusionMatrix.getMacroRecall(), maxEntRecursiveViterbiConfusionMatrix.getMacroF1Score()));
        System.out.println(String.format("simple micro p/r/f1: %f  %f  %f", simpleMaxEntConfusionMatrix.getMicroPrecision(), simpleMaxEntConfusionMatrix.getMicroRecall(), simpleMaxEntConfusionMatrix.getMicroF1Score()));
        System.out.println(String.format("    rv micro p/r/f1: %f  %f  %f", maxEntRecursiveViterbiConfusionMatrix.getMicroPrecision(), maxEntRecursiveViterbiConfusionMatrix.getMicroRecall(), maxEntRecursiveViterbiConfusionMatrix.getMicroF1Score()));

        System.out.println(simpleMaxEntConfusionMatrix.toString());
        System.out.println(maxEntRecursiveViterbiConfusionMatrix.toString());
    }

}
